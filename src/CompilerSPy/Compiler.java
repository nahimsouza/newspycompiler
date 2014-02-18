package CompilerSPy;

import AST.*;
import Lexer.*;
import java.io.PrintWriter;

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
            System.out.print(" " + lexer.token + " ");
            if (lexer.token == Symbol.EOF) {
                error.show("Unexpected EOF");
            }
            p = program();
            if (lexer.token != Symbol.EOF) {
                p = null;
                error.show("EOF expected");
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
        Program program = new Program();
        Stmt stmt = stmt();
        program.setStmt(stmt);
        return program;
    }

    private Stmt stmt() {
        /*
         * stmt: simple_stmt | compound_stmt
         */

        Stmt stmt = null;

        // considerando que NAME � um identificador Symbol.ID
//        if (matchTokens(Symbol.ID, Symbol.PRINT, Symbol.BREAK, Symbol.CONTINUE, Symbol.RETURN)) {
//            stmt = simple_stmt();
//        } else {
//            stmt = compound_stmt();
//        }
        return stmt;
    }

    private SimpleStmt simple_stmt() {
        /*
         * simple_stmt: small_stmt (';' small_stmt)* NEWLINE
         */

        SimpleStmt stmt = new SimpleStmt();
//
//        stmt.add(small_stmt());
//
////        lexer.nextToken();
//        
//        while (matchTokens(Symbol.SEMICOLON)) {
//            stmt.add(small_stmt());
//
//            lexer.nextToken();
//        }
//
//        if (matchTokens(Symbol.NEWLINE, Symbol.EOF)) {
//            lexer.nextToken();
//        } else {
//            
//            error.show("NEWLINE expected");
//            lexer.nextToken();
//        }

        return stmt;
    }

    private SmallStmt small_stmt() {
        /*
         * small_stmt: (expr_stmt | print_stmt | flow_stmt)
         */

        SmallStmt stmt = null;

//        // considerando NAME = Symbol.ID
//        if (matchTokens(Symbol.ID)) {
//            stmt = expr_stmt();
//        } else if (matchTokens(Symbol.PRINT)) {
//            stmt = print_stmt();
//        } else {
//            stmt = flow_stmt();
//        }
        return stmt;

    }

    private ExprStmt expr_stmt() {
        /*
         * expr_stmt: targetlist augassign listmaker
         */

        ExprStmt es = new ExprStmt();

//        es.setTargetList(targetlist());
//        es.setAugassign(augassign());
//        es.setListmaker(listmaker());
//  
        return es;
    }

    private Augassign augassign() {
        /*
         * expr_stmt: targetlist augassign listmaker
         *
         * augassign: ('=' | '+=' | '-=' | '*=' | '/=' | '%=' | '&=' | '|=' |
         * '^=')
         */

        String op = null;
        Augassign a = new Augassign(op);


//
//        if (matchTokens(Symbol.ASSIGN, Symbol.PLUSASSIGN, Symbol.MINUSASSIGN,
//                Symbol.MULTIASSIGN, Symbol.DIVASSIGN, Symbol.MODASSIGN, Symbol.ANDASSIGN,
//                Symbol.ORASSIGN, Symbol.XORASSIGN)) {
//
//            if (matchTokens(Symbol.ASSIGN)) {
//                op = "=";
//            } else if (matchTokens(Symbol.PLUSASSIGN)) {
//                op = "+=";
//            } else if (matchTokens(Symbol.MINUSASSIGN)) {
//                op = "-=";
//            } else if (matchTokens(Symbol.MULTIASSIGN)) {
//                op = "*=";
//            } else if (matchTokens(Symbol.DIVASSIGN)) {
//                op = "/=";
//            } else if (matchTokens(Symbol.MODASSIGN)) {
//                op = "%=";
//            } else if (matchTokens(Symbol.ANDASSIGN)) {
//                op = "&=";
//            } else if (matchTokens(Symbol.ORASSIGN)) {
//                op = "|=";
//            } else {
//                op = "^=";
//            }
//
//      
//            lexer.nextToken();
//          } else {
//            error.show("ASSIGN expected");
//            lexer.nextToken();
//          }
        return a;

    }

    private Targetlist targetlist() {
        /*
         * targetlist = target ("," target)*
         */
        Targetlist targetList = new Targetlist();

//        targetList.addTarget(target());
//
//        while (matchTokens(Symbol.COMMA)) {
//            lexer.nextToken();
//            targetList.addTarget(target());
//        }
        return targetList;
    }

    private Target target() {
        /*
         * target = NAME
         */

        Target target = new Target();

//        if (matchTokens(Symbol.ID)) {
//            target.setName(lexer.getStringValue());
//
//            // adiciona na tabela local
//            String nome = lexer.getStringValue();
//            symbolTable.putInLocal(nome, target);
//
//            lexer.nextToken();
//        } else {
//            error.show("NAME expected");
//            lexer.nextToken();
//        }
        return target;
    }

    private PrintStmt print_stmt() {
        /*
         * print_stmt: 'print' ( test (',' test)* )
         */

        PrintStmt print = new PrintStmt();

//        if (matchTokens(Symbol.PRINT)) {
//            lexer.nextToken();
//            print.setPrint(true);
//            print.addTest(test());
//
//            while (matchTokens(Symbol.COMMA)) {
//                lexer.nextToken();
//                print.addTest(test());
//            }
//
//        }
        return print;
    }

    private FlowStmt flow_stmt() {
        /*
         * flow_stmt: break_stmt | continue_stmt | return_stmt
         *
         * Estes dá para colocar nesta mesma função:
         *
         * break_stmt: 'break' continue_stmt: 'continue' return_stmt: 'return'
         * test
         *
         */

        FlowStmt flow = null;

//        if (matchTokens(Symbol.BREAK)) {
//            lexer.nextToken();
//            flow.setTipoBreak();
//
//        } else if (matchTokens(Symbol.CONTINUE)) {
//            lexer.nextToken();
//            flow.setTipoContinue();
//
//        } else if (matchTokens(Symbol.RETURN)) {
//            lexer.nextToken();
//            flow.setTipoReturn();
//            flow.setTest(test());
//
//        } else {
//            error.show("FLOW STATEMENT expected");
//            lexer.nextToken();
//        }
        return flow;
    }

    private Stmt compound_stmt() {
        /*
         * compound_stmt: if_stmt | while_stmt | for_stmt | funcdef | classdef
         */

        Stmt stmt = null;

//        if (matchTokens(Symbol.IF)) {
//            stmt.setIfStmt(if_stmt());
//        } else if (matchTokens(Symbol.WHILE)) {
//            stmt.setWhileStmt(while_stmt());
//        } else if (matchTokens(Symbol.FOR)) {
//            stmt.setForStmt(for_stmt());
//        } else if (matchTokens(Symbol.DEF)) {
//            stmt.setFuncdef(funcdef());
//        } else if (matchTokens(Symbol.CLASS)) {
//            stmt.setClassDef(classdef());
//        } else {
//            error.show("STATEMENT expected");
//            lexer.nextToken();
//        }
        return stmt;
    }

    private IfStmt if_stmt() {
        /*
         * if_stmt: 'if' test ':' suite ('elif' test ':' suite)* ['else' ':'
         * suite]
         */

        IfStmt ifStmt = new IfStmt();

//        if (matchTokens(Symbol.IF)) {
//            lexer.nextToken();
//            ifStmt.setTif(test());
//
//            if (matchTokens(Symbol.COLON)) {
//                lexer.nextToken();
//                ifStmt.setSif(suite());
//            } else {
//                error.show("SUITE expected");
//                lexer.nextToken();
//            }
//
//            while (matchTokens(Symbol.ELIF)) {
//                lexer.nextToken();
//                ifStmt.addTelif(test());
//
//                if (matchTokens(Symbol.COLON)) {
//                    lexer.nextToken();
//                    ifStmt.addSelif(suite());
//
//                } else {
//                    error.show("SUITE expected");
//                    lexer.nextToken();
//                }
//            }
//
//            if (matchTokens(Symbol.ELSE)) {
//                lexer.nextToken();
//
//                if (matchTokens(Symbol.COLON)) {
//                    lexer.nextToken();
//                    ifStmt.setSelse(suite());
//
//                } else {
//                    error.show("SUITE expected");
//                    lexer.nextToken();
//                }
//            }
//        }
        return ifStmt;
    }

    private WhileStmt while_stmt() {
        /*
         * while_stmt: 'while' test ':' suite ['else' ':' suite]
         */

        WhileStmt whileStmt = new WhileStmt();

//        if (matchTokens(Symbol.WHILE)) {
//            lexer.nextToken();
//            whileStmt.setTest(test());
//
//            if (matchTokens(Symbol.COLON)) {
//                lexer.nextToken();
//                whileStmt.setSuite(suite());
//
//            } else {
//                error.show("':' expected");
//                lexer.nextToken();
//            }
//
//            if (matchTokens(Symbol.ELSE)) {
//                lexer.nextToken();
//
//                if (matchTokens(Symbol.COLON)) {
//                    lexer.nextToken();
//                    whileStmt.setEsuite(suite());
//
//                } else {
//                    error.show("SUITE expected");
//                    lexer.nextToken();
//                }
//            }
//        }
        return whileStmt;
    }

    private ForStmt for_stmt() {
        /*
         * for_stmt: 'for' NAME 'in' atom ':' suite ['else' ':' suite] |
         * 'for' NAME 'in' 'range' '(' NUMBER ',' NUMBER ')' ':' suite ['else' ':' suite]
         */

        ForStmt forStmt = new ForStmt();

//        Exprlist exprList = new Exprlist();
//
//        if (matchTokens(Symbol.FOR)) {
//            lexer.nextToken();
//            // exprlist
//            while (matchTokens(Symbol.PLUS, Symbol.MINUS, Symbol.INVERTION)) {
//                exprList.addExpr(expr());
//            }
//            forStmt.setExprlist(exprList);
//
//            if (matchTokens(Symbol.IN)) {
//                lexer.nextToken();
//
//                if (matchTokens(Symbol.RANGE)) {
//                    lexer.nextToken();
//
//                    if (matchTokens(Symbol.LEFTPAR)) {
//                        lexer.nextToken();
//
//                        if (matchTokens(Symbol.NUM)) {
//                            forStmt.setNumber1(lexer.getNumberValue());
//                            lexer.nextToken();
//
//                            if (matchTokens(Symbol.COMMA)) {
//                                lexer.nextToken();
//
//                                if (matchTokens(Symbol.NUM)) {
//                                    forStmt.setNumber2(lexer.getNumberValue());
//                                    lexer.nextToken();
//
//                                    if (matchTokens(Symbol.RIGHTPAR)) {
//                                        lexer.nextToken();
//
//                                        if (matchTokens(Symbol.COLON)) {
//                                            lexer.nextToken();
//                                            forStmt.setSuite(suite());
//
//                                            if (matchTokens(Symbol.ELSE)) {
//                                                lexer.nextToken();
//
//                                                if (matchTokens(Symbol.COLON)) {
//                                                    lexer.nextToken();
//                                                    forStmt.setElseSuite(suite());
//                                                } else {
//                                                    error.show(": expected");
//                                                    lexer.nextToken();
//                                                }
//                                            }
//                                        } else {
//                                            error.show("':' expected");
//                                        }
//                                    } else {
//                                        error.show("')' expected");
//                                        lexer.nextToken();
//                                    }
//                                } else {
//                                    error.show("NUMBER expected");
//                                }
//                            } else {
//                                error.show("',' expected");
//                            }
//                        } else {
//                            error.show("NUMBER expected");
//                        }
//                    } else {
//                        error.show("'(' expected");
//                    }
//
//                } else {
//                    forStmt.setAtom(atom());
//
//                    if (matchTokens(Symbol.COLON)) {
//                        lexer.nextToken();
//                        forStmt.setSuite(suite());
//
//                        if (matchTokens(Symbol.ELSE)) {
//                            lexer.nextToken();
//
//                            if (matchTokens(Symbol.COLON)) {
//                                lexer.nextToken();
//                                forStmt.setElseSuite(suite());
//                            } else {
//                                error.show(": expected");
//                                lexer.nextToken();
//                            }
//                        }
//                    } else {
//                        error.show("':' expected");
//                    }
//                }
//            } else {
//                error.show("IN expected");
//            }
//        } else {
//            error.show("FOR expected");
//            lexer.nextToken();
//        }
        return forStmt;
    }

    private Suite suite() {
        /*
         * suite: simple_stmt | NEWLINE INDENT stmt+ DEDENT
         */

        Suite suite = new Suite();

//        if (matchTokens(Symbol.NEWLINE)) {
//            lexer.nextToken();
//            
//            if (matchTokens(Symbol.INDENT)) {
//                lexer.nextToken();
//                
//                suite.setSimpleStmt(false);
//                suite.addStmt(stmt());
//
//                while (!matchTokens(Symbol.DEDENT, Symbol.EOF)) {
//                    suite.addStmt(stmt());
//                } 
//                
//                if (!matchTokens(Symbol.DEDENT, Symbol.EOF)) {
//                    error.show("DEDENT expected");
//                    lexer.nextToken();
//                }
//            } else {
//                error.show("INDENT expected");
//                lexer.nextToken();
//            }
//        } else {
//            suite.setSimpleStmt(true);
//            suite.setSimplestmt(simple_stmt());
//        }
        return suite;
    }

    private Funcdef funcdef() {
        /*
         * funcdef: 'def' NAME parameters ':' suite
         */

        Funcdef func = new Funcdef();

//        if (matchTokens(Symbol.DEF)) {
//            lexer.nextToken();
//
//            if (matchTokens(Symbol.ID)) {
//                func.setName(lexer.getStringValue());
//                lexer.nextToken();
//
//                String nome = lexer.getStringValue();
//                symbolTable.putInGlobal(nome, func);
//                
//                func.setParameters(parameters());
//
//
//                if (matchTokens(Symbol.COLON)) {
//                    lexer.nextToken();
//                    func.setSuite(suite());
//
//                } else {
//                    error.show("COLON expected");
//                    lexer.nextToken();
//                }
//            } else {
//                error.show("NAME expected");
//                lexer.nextToken();
//            }
//        } else {
//            error.show("DEF expected");
//            lexer.nextToken();
//        }
        return func;
    }

    private Parameters parameters() {
        /*
         * parameters: '(' [varargslist] ')'
         */

        Parameters param = new Parameters();

//        if (matchTokens(Symbol.LEFTPAR)) {
//            lexer.nextToken();
//            param.setVarargslist(varargslist());
//
//            if (matchTokens(Symbol.RIGHTPAR)) {
//                lexer.nextToken();
//            } else {
//                error.show(") expected");
//                lexer.nextToken();
//            }
//        } else {
//            error.show("( expected");
//            lexer.nextToken();
//        }
        return param;
    }

    private Varargslist varargslist() {
        /*
         * varargslist: ([fpdef ['=' test] (',' fpdef ['=' test])* ] )
         */

        Varargslist var = new Varargslist();

//        var.addFpdef(fpdef());
//
//        if (matchTokens(Symbol.ASSIGN)) {
//            lexer.nextToken();
//            var.addTest(test());
//        }
//
//        while (matchTokens(Symbol.COMMA)) {
//            lexer.nextToken();
//            var.addFpdef(fpdef());
//
//            if (matchTokens(Symbol.ASSIGN)) {
//                lexer.nextToken();
//                var.addTest(test());
//            }
//        }
        return var;
    }

    private Fpdef fpdef() {
        /*
         * fpdef: NAME | SELF | '(' fplist ')'
         */

        Fpdef f = new Fpdef();

//        if (matchTokens(Symbol.ID, Symbol.SELF)) {
//            f.setName(true);
//            f.setName(lexer.getStringValue());
//
//            // adicionar na tabela global
//            String nome = lexer.getStringValue();
//            symbolTable.putInGlobal(nome, f);
//
//            lexer.nextToken();
//
//        } else if (matchTokens(Symbol.LEFTPAR)) {
//            lexer.nextToken();
//            f.setFplist(fplist());
//
//            if (matchTokens(Symbol.RIGHTPAR)) {
//                lexer.nextToken();
//            } else {
//                error.show(") expected");
//                lexer.nextToken();
//            }
//        } else {
//            error.show("NAME or ( expected");
//            lexer.nextToken();
//        }
        return f;
    }

    private Fplist fplist() {
        /*
         * fplist: fpdef (',' fpdef)*
         */

        Fplist f = new Fplist();

//        f.addFpdef(fpdef());
//
//        while (matchTokens(Symbol.COMMA)) {
//            lexer.nextToken();
//            f.addFpdef(fpdef());
//        }
        return f;
    }

    private ClassDef classdef() {
        /*
         * classdef: 'class' NAME ['(' [atom [',' atom]* ] ')'] ':' suite
         */

        ClassDef classDef = new ClassDef();

//        if (matchTokens(Symbol.CLASS)) {
//            lexer.nextToken();
//
//            if (matchTokens(Symbol.ID)) {
//                // Guarda o nome da classe como uma variavel global
//                String c = lexer.getStringValue();
//                
//                classDef.setName(c);
//                symbolTable.putInGlobal(c, new ClassDef()); 
//                lexer.nextToken();
//
//                if (matchTokens(Symbol.LEFTPAR)) {
//                    lexer.nextToken();
//                    classDef.addAtomList(atom());
//
//                    while (matchTokens(Symbol.COMMA)) {
//                        lexer.nextToken();
//                        classDef.addAtomList(atom());
//                    }
//
//                    if (matchTokens(Symbol.RIGHTPAR)) {
//                        lexer.nextToken();
//                    } else {
//                        error.show(") expected");
//                        lexer.nextToken();
//                    }
//                }
//
//                if (matchTokens(Symbol.COLON)) {
//                    lexer.nextToken();
//                    classDef.setSuite(suite());
//                } else {
//                    error.show(": expected");
//                    lexer.nextToken();
//                }
//            } else {
//                error.show("Class Name expected");
//                lexer.nextToken();
//            }
//        } else {
//            error.show("class expected");
//            lexer.nextToken();
//        }
        return classDef;
    }

    private Test test() {
        /*
         * test: or_test ['if' or_test 'else' test]
         */

        Test test = new Test();

//        test.setOrtest(or_test());
//
//        if (matchTokens(Symbol.IF)) {
//            lexer.nextToken();
//            test.setIfortest(or_test());
//
//            if (matchTokens(Symbol.ELSE)) {
//                lexer.nextToken();
//            }
//
//            test.setElsetest(test());
//        }
        return test;
    }

    private OrTest or_test() {
        /*
         * or_test: and_test ('or' and_test)*
         */

        OrTest orTest = new OrTest();

//        orTest.addAndTest(and_test());
//
//        if (matchTokens(Symbol.OR)) {
//            lexer.nextToken();
//            orTest.addAndTest(and_test());
//        }
        return orTest;
    }

    private AndTest and_test() {
        /*
         * and_test: not_test ('and' not_test)*
         */

        AndTest andTest = new AndTest();

//        andTest.addNotTest(not_test());
//
//        if (matchTokens(Symbol.AND)) {
//            lexer.nextToken();
//            andTest.addNotTest(not_test());
//        }
        return andTest;
    }

    private NotTest not_test() {
        /*
         * not_test: 'not' not_test | comparison
         */

        NotTest notTest = new NotTest();

//        if (matchTokens(Symbol.NOT)) {
//            lexer.nextToken();
//            notTest.setNotTest(not_test());
//        } else {
//            notTest.setComparison(comparison());
//        }
        return notTest;
    }

    private Comparison comparison() {
        /*
         * comparison: expr (comp_op expr)*
         *
         * comp_op: '<'|'>'|'=='|'>='|'<='|'<>'|'!='|'in'|'not' 'in'|'is'|'is' 'not'
         */

        Comparison comparison = new Comparison();

//        comparison.setExpr(expr());
//
//        String op;
//
//        if (matchTokens(Symbol.LT, Symbol.GT, Symbol.EQ, Symbol.GE, Symbol.LE,
//                Symbol.NEQ, Symbol.NEQC, Symbol.IN, Symbol.IS, Symbol.NOT)) {
//
//            if (matchTokens(Symbol.LT)) {
//                op = "<";
//            } else if (matchTokens(Symbol.GT)) {
//                op = ">";
//            } else if (matchTokens(Symbol.EQ)) {
//                op = "==";
//            } else if (matchTokens(Symbol.GE)) {
//                op = ">=";
//            } else if (matchTokens(Symbol.LE)) {
//                op = "<=";
//            } else if (matchTokens(Symbol.NEQ)) {
//                op = "<>";
//            } else if (matchTokens(Symbol.NEQC)) {
//                op = "!=";
//            } else if (matchTokens(Symbol.IN)) {
//                op = "in";
//            } else if (matchTokens(Symbol.IS)) {
//                op = "is";
//            } else {
//                op = "not";
//            }
//            CompOp c = new CompOp(op);
//
//            comparison.addCompOp(c);
//
//            lexer.nextToken();
//
//            comparison.addExprs(expr());
//        }
////        } else {
////            error.show("compOp expected");
////            lexer.nextToken();
////        }
        return comparison;
    }

    private Expr expr() {
        /*
         * expr: xor_expr ('|' xor_expr)*
         */

        Expr expr = new Expr();

//        expr.addXorExpr(xor_expr());
//
//        while (matchTokens(Symbol.ORBAR)) {
//            lexer.nextToken();
//            expr.addXorExpr(xor_expr());
//        }
        return expr;
    }

    private XorExpr xor_expr() {
        /*
         * and_expr: arith_expr ('&' arith_expr)*
         */

        XorExpr xor = new XorExpr();

//        ArithExpr aexpr = new ArithExpr();
//        aexpr = arith_expr();
//
//        xor.addArithExpr(aexpr);
//
//        while (matchTokens(Symbol.EC)) {
//            lexer.nextToken();
//            xor.addArithExpr(arith_expr());
//        }
        return xor;
    }

    private ArithExpr arith_expr() {
        /*
         * arith_expr: term (('+'|'-') term)*
         */

        ArithExpr arith = new ArithExpr();

//        arith.setTerm(term());
//
//        while (matchTokens(Symbol.PLUS) || matchTokens(Symbol.MINUS)) {
//            if (matchTokens(Symbol.PLUS)) {
//                arith.addOp("+");
//            } else {
//                arith.addOp("-");
//            }
//            lexer.nextToken();
//            arith.addTerm(term());
//        }
        return arith;
    }

    private Term term() {
        /*
         * ORIGINAL -> term: factor (('*'|'/'|'%'|'//') factor)*
         * MUDADO Pq?? -> term: factor (('*'|'/'|'%'|'//') (('int'|'float') '(' arith_expr ')' | factor))*
         * 
         */

        Term term = new Term();

//        String op;
//        term.setFactor(factor());
//
//        while (matchTokens(Symbol.MULT) || matchTokens(Symbol.DIV) || matchTokens(Symbol.MOD) || matchTokens(Symbol.FLOORDIV)) {
//
//            if (matchTokens(Symbol.MULT)) {
//                op = "*";
//            } else if (matchTokens(Symbol.DIV)) {
//                op = "/";
//            } else if (matchTokens(Symbol.MOD)) {
//                op = "%";
//            } else {
//                op = "//";
//            }
//
//            term.addOp(op);
//            lexer.nextToken();
//            
//            if(matchTokens(Symbol.ID) && (lexer.getStringValue().contentEquals("int")) || lexer.getStringValue().contentEquals("float")){
//                lexer.nextToken();
//                
//                if(matchTokens(Symbol.LEFTPAR)){
//                    lexer.nextToken();
//                    
//                    arith_expr();
//                    if(matchTokens(Symbol.RIGHTPAR)){
//                        lexer.nextToken();
//                    }
//                }
//            }else
//                term.addFactor(factor());
//        }
        return term;
    }

    private Factor factor() {
        /*
         * factor: ('+'|'-'|'~') factor | atom
         */

        Factor factor = new Factor();

//        if (matchTokens(Symbol.PLUS) || matchTokens(Symbol.MINUS) || matchTokens(Symbol.INVERTION)) {
//            factor.setOp(lexer.token);
//            lexer.nextToken();
//            factor.setFactor(factor());
//            factor.setFactor(true);
//        } else {
//            factor.setAtom(atom());
//            factor.setFactor(false);
//        }
        return factor;
    }

    private Atom atom() {
        /*
         * OLD -> atom: '[' [listmaker] ']' | NAME | NUMBER | STRING+
         * NEW -> atom: [ '['listmaker']' ] | SELF '.' NAME | NAME | NUMBER | STRING+
         */

        Atom atom = new Atom();

//        if (matchTokens(Symbol.LEFTCURBRACKET)) {
//            lexer.nextToken();
//            atom.setToTestlist();
//            atom.setTestlist(listmaker());
//
//            if (!matchTokens(Symbol.RIGHTCURBRACKET)) {
//                error.show("] expected");
//                lexer.nextToken();
//            }
//        } else if (matchTokens(Symbol.ID)) {
//            atom.setToName();
//            atom.setName(lexer.getStringValue());
//
//            lexer.nextToken();
//        } else if (matchTokens(Symbol.NUM)) {
//            atom.setToNumber();
//            atom.setNumber(lexer.getNumberValue());
//            lexer.nextToken();
//        } else if (matchTokens(Symbol.STRING)) {
//            atom.setToString();
//            while (matchTokens(Symbol.STRING)) {
//                atom.addString(lexer.getStringValue());
//
//                String nome = lexer.getStringValue();
//                symbolTable.putInLocal(nome, atom);
//
//                lexer.nextToken();
//            }
//        } else if (matchTokens(Symbol.SELF)) {
//            atom.setToSelf();
//            atom.setName(lexer.getStringValue());
//            lexer.nextToken();
//            
//            if (matchTokens(Symbol.DOT)){
//                lexer.nextToken();
//                if (matchTokens(Symbol.ID)){
//                    lexer.nextToken();
//                } else {
//                    error.show("ID expected");
//                    lexer.nextToken();
//                }
//            } else {
//                error.show("'.' expected");
//                lexer.nextToken();
//            }
//            
//        } else {
//            error.show("Atom Error");
//            lexer.nextToken();
//        }
        return atom;
    }

    public Listmaker listmaker() {
        /*
         * listmaker: test (',' test)*
         */

        Listmaker listmaker = new Listmaker();

//        listmaker.addTest(test());
//
//        while (matchTokens(Symbol.COMMA)) {
//            lexer.nextToken();
//            listmaker.addTest(test());
//        }
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
