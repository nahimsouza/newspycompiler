/**
 * Lab de Compiladores - BCC, UFSCar, Sorocaba - 2013
 *
 * SPy Compiler 
 * Fernando Villas Boas Alves 
 * Nahim Alves de Souza
 *
 * ====================================================
 *
 * Class Compiler:
 * Contem todos os metodos usados para fazer a analise sintatica
 * e a analise semantica do compilador.
 *
 */
package CompilerSPy;

import AST.*;
import Lexer.*;
import java.io.PrintWriter;
import java.util.Vector;

public class Compiler {

    // compile must receive an input with an character less than 
    // p_input.lenght  
    public Program compile(char[] input, PrintWriter outError) {

        symbolTable = new SymbolTable();
        error = new CompilerError(lexer, new PrintWriter(outError));
        lexer = new Lexer(input, error);
        error.setLexer(lexer);

        Program p = null;
        try {
            lexer.nextToken();
            //System.out.print(" " + lexer.token + " ");
            if (lexer.token == Symbol.EOF) {
                error.show("Unexpected EOF");
            }
            p = program();
            if (lexer.token != Symbol.EOF) {
                p = null;
                error.show("EOF expected.");
            }
        } catch (Exception e) {
            // the below statement prints the stack of called methods.
            // of course, it should be removed if the compiler were 
            // a production compiler.
            e.printStackTrace();
            p = null;
        }

        return p;
    }

    private Program program() {
        Vector<Stmt> stmtList = new Vector<Stmt>();

        while (!matchTokens(Symbol.EOF)) {
            stmtList.add(stmt());
        }

        return new Program(stmtList);
    }

    private Stmt stmt() {
        /*
         * stmt: simple_stmt | compound_stmt
         */

        Stmt stmt = null;

        if (matchTokens(Symbol.EOF)) {
            return null;
        }

        if (matchTokens(Symbol.NAME, Symbol.PRINT, Symbol.BREAK, Symbol.CONTINUE, Symbol.RETURN)) {
            stmt = simple_stmt();
        } else if (matchTokens(Symbol.IF, Symbol.WHILE, Symbol.FOR, Symbol.DEF, Symbol.CLASS)) {
            stmt = compound_stmt();
        } else {
            error.show("Simple or Compound Statement expected.");
        }

        return stmt;
    }

    private SimpleStmt simple_stmt() {
        /*
         * simple_stmt: small_stmt (';' small_stmt)* NEWLINE
         */

        SimpleStmt stmt = new SimpleStmt();

        stmt.addStmt(small_stmt());

        while (matchTokens(Symbol.SEMICOLON)) {
            lexer.nextToken();
            stmt.addStmt(small_stmt());
        }

        // se chegou ao fim do arquivo
        if (matchTokens(Symbol.EOF)) {
            return stmt;
        }

        if (!matchTokens(Symbol.NEWLINE)) {
            error.show("NEWLINE expected.");
        }

        lexer.nextToken();

        return stmt;
    }

    private SmallStmt small_stmt() {
        /*
         * small_stmt: (expr_stmt | print_stmt | flow_stmt)
         */

        SmallStmt stmt = null;

        if (matchTokens(Symbol.NAME)) {
            stmt = expr_stmt();
        } else if (matchTokens(Symbol.PRINT)) {
            stmt = print_stmt();
        } else if (matchTokens(Symbol.BREAK, Symbol.CONTINUE, Symbol.RETURN)) {
            stmt = flow_stmt();
        } else {
            error.show("Unexpected error in Small Statement");
        }

        return stmt;

    }

    private ExprStmt expr_stmt() {
        /*
         * expr_stmt: targetlist augassign listmaker
         */

        ExprStmt es = new ExprStmt();

        es.setTargetlist(targetlist());
        es.setAugassign(augassign());
        es.setListmaker(listmaker());

        return es;
    }

    private Augassign augassign() {
        /*
         * augassign: ('=' | '+=' | '-=' | '*=' | '/=' | '%=' | '&=' | '|=' | '^=')
         */

        String op = "";
        if (matchTokens(Symbol.ASSIGN, Symbol.PLUSASSIGN, Symbol.MINUSASSIGN,
                Symbol.MULTIASSIGN, Symbol.DIVASSIGN, Symbol.MODASSIGN, Symbol.ANDASSIGN,
                Symbol.ORASSIGN, Symbol.XORASSIGN)) {

            if (matchTokens(Symbol.ASSIGN)) {
                op = "=";
            } else if (matchTokens(Symbol.PLUSASSIGN)) {
                op = "+=";
            } else if (matchTokens(Symbol.MINUSASSIGN)) {
                op = "-=";
            } else if (matchTokens(Symbol.MULTIASSIGN)) {
                op = "*=";
            } else if (matchTokens(Symbol.DIVASSIGN)) {
                op = "/=";
            } else if (matchTokens(Symbol.MODASSIGN)) {
                op = "%=";
            } else if (matchTokens(Symbol.ANDASSIGN)) {
                op = "&=";
            } else if (matchTokens(Symbol.ORASSIGN)) {
                op = "|=";
            } else {
                op = "^=";
            }

            Augassign a = new Augassign(op);
            lexer.nextToken();
            return a;

        } else {
            error.show("ASSIGN expected.");
            lexer.nextToken();
            return null;
        }
    }

    private Targetlist targetlist() {
        /*
         * targetlist = target ("," target)*
         */
        Targetlist targetList = new Targetlist();

        targetList.addTarget(target());

        while (matchTokens(Symbol.COMMA)) {
            lexer.nextToken();
            targetList.addTarget(target());
        }

        return targetList;
    }

    private Target target() {
        /*
         * target = NAME
         */

        Target target = new Target();

        if (matchTokens(Symbol.NAME)) {
            Name name = new Name(lexer.getStringValue());
            target.setName(name);
            lexer.nextToken();
        } else {
            error.show("NAME expected in TARGET (on targetlist from Expression Statement).");
            lexer.nextToken();
            return null;
        }

        return target;
    }

    private PrintStmt print_stmt() {
        /*
         * print_stmt: 'print' ( test (',' test)* )
         */

        PrintStmt print = new PrintStmt();

        // a verificacao se eh um 'PRINT' eh feita antes de entrar no metodo
        lexer.nextToken();
        print.addTest(test());
        while (matchTokens(Symbol.COMMA)) {
            lexer.nextToken();
            print.addTest(test());
        }

        return print;
    }

    private FlowStmt flow_stmt() {
        /*
         * flow_stmt: break_stmt | continue_stmt | return_stmt
         *
         * Estes dá para colocar nesta mesma função:
         *
         * break_stmt: 'break' 
         * continue_stmt: 'continue' 
         * return_stmt: 'return' test
         *
         */

        FlowStmt flowStmt = null;

        if (matchTokens(Symbol.BREAK)) {
            lexer.nextToken();
            flowStmt = new BreakStmt();
        } else if (matchTokens(Symbol.CONTINUE)) {
            lexer.nextToken();
            flowStmt = new ContinueStmt();
        } else if (matchTokens(Symbol.RETURN)) {
            lexer.nextToken();
            flowStmt = new ReturnStmt(test());
        } else {
            error.show("Unexpected error in Flow Statement.");
            lexer.nextToken();
        }

        return flowStmt;
    }

    private Stmt compound_stmt() {
        /*
         * compound_stmt: if_stmt | while_stmt | for_stmt | funcdef | classdef
         */

        if (matchTokens(Symbol.IF)) {
            return if_stmt();
        } else if (matchTokens(Symbol.WHILE)) {
            return while_stmt();
        } else if (matchTokens(Symbol.FOR)) {
            return for_stmt();
        } else if (matchTokens(Symbol.DEF)) {
            return funcdef();
        } else if (matchTokens(Symbol.CLASS)) {
            return classdef();
        } else {
            error.show("Unexpected error in Compound Statement.");
            lexer.nextToken();
            return null;
        }
    }

    private IfStmt if_stmt() {
        /*
         * if_stmt: 'if' test ':' suite ('elif' test ':' suite)* ['else' ':' suite]
         */

        IfStmt ifStmt = new IfStmt();

        // a verficacao se token == IF eh feita antes de entrar no metodo
        lexer.nextToken();
        ifStmt.setTif(test());

        if (matchTokens(Symbol.COLON)) {
            lexer.nextToken();
            ifStmt.setSif(suite());
        } else {
            error.show("':' expected after IF test.");
            lexer.nextToken();
            return null;
        }

        while (matchTokens(Symbol.ELIF)) {
            lexer.nextToken();
            ifStmt.addTelif(test());

            if (matchTokens(Symbol.COLON)) {
                lexer.nextToken();
                ifStmt.addSelif(suite());
            } else {
                error.show("':' expected after ELIF test.");
                lexer.nextToken();
                return null;
            }
        }

        if (matchTokens(Symbol.ELSE)) {
            lexer.nextToken();
            if (matchTokens(Symbol.COLON)) {
                lexer.nextToken();
                ifStmt.setSelse(suite());
            } else {
                error.show("':' expected after ELSE.");
                lexer.nextToken();
                return null;
            }
        }

        return ifStmt;
    }

    private WhileStmt while_stmt() {
        /*
         * while_stmt: 'while' test ':' suite ['else' ':' suite]
         */

        WhileStmt whileStmt = new WhileStmt();

        // a verificacao se token == WHILE eh feita antes de entrar no metodo
        lexer.nextToken();
        whileStmt.setTest(test());

        if (matchTokens(Symbol.COLON)) {
            lexer.nextToken();
            whileStmt.setSuite(suite());
        } else {
            error.show("':' expected after WHILE test.");
            lexer.nextToken();
            return null;
        }

        if (matchTokens(Symbol.ELSE)) {
            lexer.nextToken();

            if (matchTokens(Symbol.COLON)) {
                lexer.nextToken();
                whileStmt.setEsuite(suite());
            } else {
                error.show("':' expected after WHILE else.");
                lexer.nextToken();
                return null;
            }
        }

        return whileStmt;
    }

    private ForStmt for_stmt() {
        /*
         * for_stmt: 
         * 'for' NAME 'in' atom ':' suite ['else' ':' suite] |
         * 'for' NAME 'in' 'range' '(' NUMBER ',' NUMBER ')' ':' suite ['else' ':' suite]
         */

        ForStmt forStmt = new ForStmt();

        // a verificacao se token == FOR eh feita antes de entrar no metodo
        lexer.nextToken();
        if (matchTokens(Symbol.NAME)) {
            Name name = new Name(lexer.getStringValue());
            lexer.nextToken();
            forStmt.setName(name);
        } else {
            error.show("NAME expected in For Statement.");
        }

        if (matchTokens(Symbol.IN)) {
            lexer.nextToken();

            if (matchTokens(Symbol.RANGE)) {
                lexer.nextToken();

                if (matchTokens(Symbol.LEFTPAR)) {
                    lexer.nextToken();
                    if (matchTokens(Symbol.NUM)) {
                        int num1 = lexer.getNumberValue();
                        forStmt.setNumber1(num1);
                        lexer.nextToken();
                        if (matchTokens(Symbol.COMMA)) {
                            lexer.nextToken();
                            if (matchTokens(Symbol.NUM)) {
                                int num2 = lexer.getNumberValue();
                                forStmt.setNumber2(num2);
                                lexer.nextToken();
                                if (matchTokens(Symbol.RIGHTPAR)) {
                                    lexer.nextToken();
                                } else {
                                    error.show("')' expected after 'NUMBER' in For Statement.");
                                    lexer.nextToken();
                                    return null;
                                }
                            } else {
                                error.show("NUMBER expected after ',' in For Statement.");
                                lexer.nextToken();
                                return null;
                            }
                        } else {
                            error.show("',' expected after NUMBER in For Statement.");
                            lexer.nextToken();
                            return null;
                        }
                    } else {
                        error.show("NUMBER expected after '(' in For Statement.");
                        lexer.nextToken();
                        return null;
                    }
                } else {
                    error.show("'(' expected after 'range'.");
                    lexer.nextToken();
                    return null;
                }

            } else if (matchTokens(Symbol.LEFTCURBRACKET, Symbol.NAME, Symbol.NUM, Symbol.STRING)) {
                forStmt.setAtom(atom());
            } else {
                error.show("ATOM or 'range' expected after 'in' in For Statement.");
                lexer.nextToken();
                return null;
            }

            // deve entrar aqui quando usar em ambos os casos do ForStmt
            if (matchTokens(Symbol.COLON)) {
                lexer.nextToken();
                forStmt.setSuite(suite());

                if (matchTokens(Symbol.ELSE)) {
                    lexer.nextToken();

                    if (matchTokens(Symbol.COLON)) {
                        lexer.nextToken();
                        forStmt.setElseSuite(suite());
                    } else {
                        error.show(": expected after ELSE in For Statement.");
                        lexer.nextToken();
                        return null;
                    }
                }
            } else {
                error.show("':' expected after ATOM in For Statement.");
                lexer.nextToken();
                return null;
            }

        } else {
            error.show("'in' expected after NAME in For Statement.");
            lexer.nextToken();
            return null;
        }

        return forStmt;
    }

    private Suite suite() {
        /*
         * suite: simple_stmt | NEWLINE INDENT stmt+ DEDENT
         */

        Suite suite = new Suite();

        if (matchTokens(Symbol.NEWLINE)) {
            lexer.nextToken();

            if (matchTokens(Symbol.INDENT)) {
                lexer.nextToken();

                suite.setSimpleStmt(false);
                suite.addStmt(stmt());

                while (!matchTokens(Symbol.DEDENT)) {
                    suite.addStmt(stmt());
                }
            } else {
                error.show("INDENT expected after NEWLINE in Suite.");
                lexer.nextToken();
                return null;
            }
        } else if (matchTokens(Symbol.NAME, Symbol.PRINT, Symbol.BREAK, Symbol.CONTINUE, Symbol.RETURN)) {
            suite.setSimpleStmt(true);
            suite.setSimplestmt(simple_stmt());
        } else {
            error.show("NEWLINE or Simple Statement expected in Suite.");
            lexer.nextToken();
            return null;
        }

        return suite;
    }

    private Funcdef funcdef() {
        /*
         * funcdef: 'def' NAME parameters ':' suite
         */

        Funcdef func = new Funcdef();

        // Ao entrar na funcao, ja deve ter verificado se encontrou a palavra 'def'
        lexer.nextToken();

        if (matchTokens(Symbol.NAME)) {
            Name name = new Name(lexer.getStringValue());
            func.setName(name);
            lexer.nextToken();

            func.setParameters(parameters());

            if (matchTokens(Symbol.COLON)) {
                lexer.nextToken();
                func.setSuite(suite());
            } else {
                error.show("':' expected after parameters on function.");
                lexer.nextToken();
                return null;
            }
        } else {
            error.show("NAME expected after 'def' on function.");
            lexer.nextToken();
            return null;
        }

        return func;
    }

    private Parameters parameters() {
        /*
         * parameters: '(' [varargslist] ')'
         */

        Parameters param = new Parameters();

        if (matchTokens(Symbol.LEFTPAR)) {
            lexer.nextToken();
            // varargslist eh opcional, entao precisa verificar se tem ou nao
            if (matchTokens(Symbol.NAME, Symbol.SELF, Symbol.LEFTPAR)) {
                param.setVarargslist(varargslist());
            }

            if (matchTokens(Symbol.RIGHTPAR)) {
                lexer.nextToken();
            } else {
                error.show("')' expected after parameters.");
                lexer.nextToken();
                return null;
            }
        } else {
            error.show("'(' expected before parameters.");
            lexer.nextToken();
            return null;
        }

        return param;
    }

    private Varargslist varargslist() {
        /*
         * varargslist: fpdef ['=' test] (',' fpdef ['=' test])* 
         */

        Varargslist var = new Varargslist();

        var.addFpdef(fpdef());
        if (matchTokens(Symbol.ASSIGN)) {
            lexer.nextToken();
            var.addTest(test());
        }

        while (matchTokens(Symbol.COMMA)) {
            lexer.nextToken();
            var.addFpdef(fpdef());

            if (matchTokens(Symbol.ASSIGN)) {
                lexer.nextToken();
                var.addTest(test());
            }
        }

        return var;

    }

    private Fpdef fpdef() {
        /*
         * fpdef: NAME | SELF | '(' fplist ')'
         */

        Fpdef f = new Fpdef();

        if (matchTokens(Symbol.NAME, Symbol.SELF)) {
            Name name = new Name(lexer.getStringValue());
            f.setName(name);
            lexer.nextToken();
        } else if (matchTokens(Symbol.LEFTPAR)) {
            lexer.nextToken();
            f.setFplist(fplist());
            if (matchTokens(Symbol.RIGHTPAR)) {
                lexer.nextToken();
            } else {
                error.show("')' expected after fplist.");
                lexer.nextToken();
                return null;
            }
        } else {
            error.show("NAME, 'self' or '(' expected in fpdef.");
            lexer.nextToken();
            return null;
        }

        return f;
    }

    private Fplist fplist() {
        /*
         * fplist: fpdef (',' fpdef)*
         */

        Fplist f = new Fplist();

        f.addFpdef(fpdef());
        while (matchTokens(Symbol.COMMA)) {
            lexer.nextToken();
            f.addFpdef(fpdef());
        }

        return f;
    }

    private Classdef classdef() {
        /*
         * classdef: 'class' NAME [ '(' [atom [',' atom]* ] ')' ] ':' suite
         */

        Classdef classDef = new Classdef();

        // A verificacao para saber se tem a palavra 'class' eh feita antes de entrar no metodo
        lexer.nextToken();

        if (matchTokens(Symbol.NAME)) {
            // Guarda o nome da classe como uma variavel global
            Name name = new Name(lexer.getStringValue());
            classDef.setName(name);
            lexer.nextToken();
            
            if (matchTokens(Symbol.LEFTPAR)) {
                lexer.nextToken();
                
                // ver se tem algum atom
                if (matchTokens(Symbol.LEFTCURBRACKET, Symbol.NAME, Symbol.NUM, Symbol.STRING)) {
                    classDef.addAtom(atom());
                
                    while (matchTokens(Symbol.COMMA)) {
                        lexer.nextToken();
                        classDef.addAtom(atom());
                    }
                }

                if (matchTokens(Symbol.RIGHTPAR)) {
                    lexer.nextToken();
                } else {
                    error.show("')' expected before ':' in class definition.");
                    lexer.nextToken();
                    return null;
                }
            }

            if (matchTokens(Symbol.COLON)) {
                lexer.nextToken();
                classDef.setSuite(suite());
            } else {
                error.show("':' expected before suite in class definition.");
                lexer.nextToken();
            }
        } else {
            error.show("NAME expected in class definition.");
            lexer.nextToken();
            return null;
        }

        return classDef;
    }

    private Test test() {
        /*
         * test: or_test ['if' or_test 'else' test]
         */

        Test test = new Test();

        test.setOrtest(or_test());

        if (matchTokens(Symbol.IF)) {
            lexer.nextToken();
            test.setIfortest(or_test());

            if (matchTokens(Symbol.ELSE)) {
                lexer.nextToken();
                test.setElsetest(test());
            } else {
                error.show("'else' expected after or_test in test.");
                lexer.nextToken();
                return null;
            }
        }

        return test;
    }

    private OrTest or_test() {
        /*
         * or_test: and_test ('or' and_test)*
         */

        OrTest orTest = new OrTest();

        orTest.addAndTest(and_test());

        while (matchTokens(Symbol.OR)) {
            lexer.nextToken();
            orTest.addAndTest(and_test());
        }
        
        return orTest;
    }

    private AndTest and_test() {
        /*
         * and_test: not_test ('and' not_test)*
         */

        AndTest andTest = new AndTest();

        andTest.addNottest(not_test());

        while (matchTokens(Symbol.AND)) {
            lexer.nextToken();
            andTest.addNottest(not_test());
        }

        return andTest;
    }

    private NotTest not_test() {
        /*
         * not_test: 'not' not_test | comparison
         */

        NotTest notTest = new NotTest();

        if (matchTokens(Symbol.NOT)) {
            lexer.nextToken();
            notTest.setNotTest(not_test());
            notTest.setIsNotTest(true);
        } else {
            notTest.setComparison(comparison());
            notTest.setIsNotTest(false);
        }
        
        return notTest;
    }

    private Comparison comparison() {
        /*
         * comparison: expr (comp_op expr)*
         */

        Comparison comparison = new Comparison();

        comparison.setExpr(expr());

        while (matchTokens(Symbol.LT, Symbol.GT, Symbol.EQ, Symbol.GE, Symbol.LE,
                Symbol.NEQ, Symbol.NEQC, Symbol.IN, Symbol.IS, Symbol.NOT)) {
            comparison.addCompOp(compOp());
            comparison.addExprs(expr());
        }
        
        return comparison;
    }
    
    private CompOp compOp() {
        /*
         * comp_op: '<'|'>'|'=='|'>='|'<='|'<>'|'!='|'in'|'not' 'in'|'is'|'is' 'not'
         */
        
        if (matchTokens(Symbol.LT)) {
            lexer.nextToken();
            return new CompOp("<");
        } else if (matchTokens(Symbol.GT)) {
            lexer.nextToken();
            return new CompOp(">");
        } else if (matchTokens(Symbol.EQ)) {
            lexer.nextToken();
            return new CompOp("==");
        } else if (matchTokens(Symbol.GE)) {
            lexer.nextToken();
            return new CompOp(">=");
        } else if (matchTokens(Symbol.LE)) {
            lexer.nextToken();
            return new CompOp("<=");
        } else if (matchTokens(Symbol.NEQ)) {
            lexer.nextToken();
            return new CompOp("<>");
        } else if (matchTokens(Symbol.NEQC)) {
            lexer.nextToken();
            return new CompOp("!=");
        } else if (matchTokens(Symbol.IN)) {
            lexer.nextToken();
            return new CompOp("in");
        } else if (matchTokens(Symbol.IS)) {
            lexer.nextToken();
            if (matchTokens(Symbol.NOT)){
                lexer.nextToken();
                return new CompOp("is not");
            } else {
                return new CompOp("is");
            }
        } else if (matchTokens(Symbol.NOT)){
            lexer.nextToken();
            if (matchTokens(Symbol.IN)){
                lexer.nextToken();
                return new CompOp("not in");
            } else {
                return new CompOp("not");
            }
        } else {
            error.show("Unexpected error on compOp.");
            lexer.nextToken();
            return null;
        }        
    }

    private Expr expr() {
        /*
         * expr: xor_expr ('|' xor_expr)*
         */

        Expr expr = new Expr();

        expr.addXorExpr(xor_expr());
        while (matchTokens(Symbol.ORBAR)) {
            lexer.nextToken();
            expr.addXorExpr(xor_expr());
        }
        
        return expr;
    }

    private XorExpr xor_expr() {
        /*
         * xor_expr: and_expr ('^' and_expr)*
         */

        XorExpr xor = new XorExpr();

        xor.addAndExpr(and_expr());

        while (matchTokens(Symbol.XOR)) {
            lexer.nextToken();
            xor.addAndExpr(and_expr());
        }
        
        return xor;
    }
    
    private AndExpr and_expr() {
        /*
         * and_expr: arith_expr ('&' arith_expr)*
         */

        AndExpr and = new AndExpr();

        and.addArithExpr(arith_expr());
        while (matchTokens(Symbol.EC)){
            lexer.nextToken();
            and.addArithExpr(arith_expr());
        }
        
        return and;
    }

    private ArithExpr arith_expr() {
        /*
         * arith_expr: term (('+'|'-') term)*
         */

        ArithExpr arith = new ArithExpr();

        arith.setTerm(term());

        while (matchTokens(Symbol.PLUS) || matchTokens(Symbol.MINUS)) {
            if (matchTokens(Symbol.PLUS)) {
                arith.addOp("+");
            } else {
                arith.addOp("-");
            }
            lexer.nextToken();
            arith.addTerm(term());
        }
        
        return arith;
    }

    private Term term() {
        /*
         * term: factor (('*'|'/'|'%'|'//') factor)*
         */

        Term term = new Term();

        String op;
        term.setFactor(factor());

        while (matchTokens(Symbol.MULT) || matchTokens(Symbol.DIV) || matchTokens(Symbol.MOD) || matchTokens(Symbol.FLOORDIV)) {

            if (matchTokens(Symbol.MULT)) {
                op = "*";
            } else if (matchTokens(Symbol.DIV)) {
                op = "/";
            } else if (matchTokens(Symbol.MOD)) {
                op = "%";
            } else {
                op = "//";
            }

            term.addOp(op);
            lexer.nextToken();
            term.addFactor(factor());
        }
        
        return term;
    }

    private Factor factor() {
        /*
         * factor: ('+'|'-'|'~') factor | atom
         */

        Factor factor = new Factor();
        String op = "";
        
        if (matchTokens(Symbol.PLUS) || matchTokens(Symbol.MINUS) || matchTokens(Symbol.INVERTION)) {
            
            if (lexer.token == Symbol.PLUS){
                op = "+";
            } else if (lexer.token == Symbol.MINUS){
                op = "-";
            } else if (lexer.token == Symbol.INVERTION){
                op = "~";
            }  
            
            factor.setOp(op);
            lexer.nextToken();
            factor.setFactor(factor());
            factor.setIsFactor(true);
            
        } else {
            factor.setAtom(atom());
            factor.setIsFactor(false);
        }

        return factor;
    }

    private Atom atom() {
        /*
         * atom: '[' [listmaker] ']' | NAME | NUMBER | STRING+
         */

        Atom atom = new Atom();

        if (matchTokens(Symbol.LEFTCURBRACKET)) {
            lexer.nextToken();
            if (!matchTokens(Symbol.RIGHTCURBRACKET)) {
                atom.setToListmaker(); // identifica que o atom eh um listmaker
                atom.setListmaker(listmaker());
                if (!matchTokens(Symbol.RIGHTCURBRACKET)){
                    error.show("']' expected after listmaker in atom.");
                }
            } else {
                lexer.nextToken();
            }
        } else if (matchTokens(Symbol.NAME)) {
            atom.setToName(); // identifica que o atom eh um NAME
            Name name = new Name(lexer.getStringValue());
            atom.setName(name);
            lexer.nextToken();
        } else if (matchTokens(Symbol.NUM)) {
            PyNumber number = new PyNumber(lexer.getNumberValue());
            atom.setToNumber(); // identifica que eh um NUMBER
            atom.setNumber(number);
            lexer.nextToken();
        } else if (matchTokens(Symbol.STRING)) {
            atom.setToString(); // identifica que eh um STRING+
            while (matchTokens(Symbol.STRING)) {
                PyString str = new PyString(lexer.getStringValue());
                atom.addString(str);
                lexer.nextToken();
            }
        } else {
            error.show("Atom expected but not found.");
            lexer.nextToken();
            return null;
        }

        return atom;
    }

    public Listmaker listmaker() {
        /*
         * listmaker: test (',' test)*
         */

        Listmaker listmaker = new Listmaker();

        listmaker.addTest(test());
        while (matchTokens(Symbol.COMMA)) {
            lexer.nextToken();
            listmaker.addTest(test());
        }

        return listmaker;
    }

    public boolean matchTokens(int... a) {
        for (int s : a) {
            if (lexer.token == s) {
                return true;
            }
        }
        return false;
    }

    private SymbolTable symbolTable;
    private Lexer lexer;
    private CompilerError error;

}
