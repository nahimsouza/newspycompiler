/*
 * ContinueStmt: apenas por causa do genC.
 */
package AST;

public class ContinueStmt extends FlowStmt {

    public void genC(PW pw) {
        pw.print("continue;");
    }
}
