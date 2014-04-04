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

    public void genC(PW pw) {
        pw.print("return ");
        this.test.genC(pw);
        pw.print(";");
    }

    private Test test;
}
