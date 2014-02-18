/*
 * WhileStmt: contem um Test e dois Suites (um a mais para o ELSE)
 */
package AST;

public class WhileStmt extends CompoundStmt {

    public WhileStmt(Test test, Suite suite) {
        this.test = test;
        this.suite = suite;
    }

    public WhileStmt() {

    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Suite getSuite() {
        return suite;
    }

    public void setSuite(Suite suite) {
        this.suite = suite;
    }

    public Suite getEsuite() {
        return esuite;
    }

    public void setEsuite(Suite esuite) {
        this.esuite = esuite;
    }

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        test.genC(tab + 1);
//        suite.genC(tab + 1);
//        if (esuite != null) {
//            esuite.genC(tab + 1);
//        }
    }

    private Test test;
    private Suite suite;
    private Suite esuite;
}
