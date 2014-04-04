/*
 * BreakStmt: apenas por causa do genC.
 */
package AST;

public class BreakStmt extends FlowStmt {

    public void genC(PW pw) {
        pw.println("break");
    }

}
