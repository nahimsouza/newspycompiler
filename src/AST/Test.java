/*
 * Test: possui dois OrExpr e um test. Um dos OrExpr eh obrigatorio, 
 * poderia (deveria?) estar no construtor da classe.
 */
package AST;

public class Test {

    public OrTest getOrtest() {
        return ortest;
    }

    public void setOrtest(OrTest ortest) {
        this.ortest = ortest;
    }

    public OrTest getIfortest() {
        return ifortest;
    }

    public void setIfortest(OrTest ifortest) {
        this.ifortest = ifortest;
    }

    public Test getElsetest() {
        return elsetest;
    }

    public void setElsetest(Test elsetest) {
        this.elsetest = elsetest;
    }

    // verifiyType() based on Angiolucci & Marucci
    // percorre os objetos para definir o tipo do objeto Test
    public void verifyType() {
//        if (this.ortest != null) {
//            this.tipo = "bool";
//        } else {
            if (ortest.getAndtest().size() > 1) {
                this.tipo = "bool";
            } else {
                AndTest andtest = ortest.getAndtest().get(0);
                if (andtest.getNottest().size() > 1) {
                    this.tipo = "bool";
                } else {
                    NotTest nottest = andtest.getNottest().get(0);
                    if (nottest.isNotTest()) {
                        this.tipo = "bool";
                    } else {
                        Comparison comparison = nottest.getComparison();
                        if (comparison.getExprs().size() > 1) {
                            this.tipo = "bool";
                        } else {
                            Expr expr = comparison.getExpr();
                            if (expr.getXorexprs().size() > 1) {
                                this.tipo = "bool";
                            } else {
                                XorExpr xorexpr = expr.getXorexprs().get(0);
                                if (xorexpr.getAndexprs().size() > 1) {
                                    this.tipo = "bool";
                                } else {
                                    AndExpr andexpr = xorexpr.getAndexprs().get(0);
                                    if (andexpr.getArithexprs().size() > 1) {
                                        this.tipo = "number";
                                    } else {
                                        ArithExpr arith_expr = andexpr.getArithexprs().get(0);
                                        if (arith_expr.getTerms().size() > 1) {
                                            this.tipo = "number";
                                        } else {
                                            Term term = arith_expr.getTerm();
                                            if (term.getFactors().size() > 1) {
                                                this.tipo = "number";
                                            } else {
                                                Factor factor = term.getFactor();
                                                if (factor.isFactor()) {
                                                    this.tipo = "number";
                                                } else {
                                                    Atom atom = factor.getAtom();
                                                    if (atom.getTipo().equalsIgnoreCase("number")) {
                                                        PyNumber number = atom.getNumber();
                                                        if (number.getNumberType().equalsIgnoreCase("int")) {
                                                            this.tipo = "int";
                                                        } else {
                                                            this.tipo = "number";
                                                        }
                                                    } else {
                                                        this.tipo = atom.getTipo();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
     //   }
    }

    public String getTipo() {
        return tipo;
    }

    public void genC(PW pw) {
        this.ortest.genC(pw);

        // considerei como operador ternario:
        // exemplo: int a = 1 > 3 ? 0 : 22;
//        if (ifortest != null) {
//            ifortest.genC(pw);
//            pw.print(" ? ");
//            elsetest.genC(pw);
//            pw.print(";");
//        }
        // Deixei comentado pq acho q esta errado a gramatica
    }

    private OrTest ortest;
    private OrTest ifortest;
    private Test elsetest;
    private String tipo;
}
