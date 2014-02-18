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

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        ortest.genC(tab + 1);
//        if (ifortest != null) {
//            ifortest.genC(tab + 1);
//            elsetest.genC(tab + 1);
//        }
    }

    private OrTest ortest;
    private OrTest ifortest;
    private Test elsetest;
}
