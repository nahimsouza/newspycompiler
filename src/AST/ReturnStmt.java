/*
 * ReturnStmt: apenas um Test, e um genC.
 */
package AST;

public class ReturnStmt extends FlowStmt {

    public ReturnStmt(Test test) {
        this.test = test;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
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
    }

    private Test test;
}
