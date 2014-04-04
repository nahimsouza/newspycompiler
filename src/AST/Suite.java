/*
 * Suite: guarda um SimpleStmt ou uma lista de Stmt, e um boolean pra saber se
 * eh ou nao um SimpleStmt.
 */
package AST;

import java.util.ArrayList;
import java.util.List;

public class Suite {

    public Suite() {
        stmts = new ArrayList<Stmt>();
    }

    public void addStmt(Stmt s) {
        stmts.add(s);
    }

    public boolean isSimpleStmt() {
        return isSimpleStmt;
    }

    public void setSimpleStmt(boolean isSimpleStmt) {
        this.isSimpleStmt = isSimpleStmt;
    }

    public SimpleStmt getSimplestmt() {
        return simplestmt;
    }

    public void setSimplestmt(SimpleStmt simplestmt) {
        this.simplestmt = simplestmt;
    }

    public List<Stmt> getStmts() {
        return stmts;
    }

    public void genC(PW pw) {

        if (isSimpleStmt) {
            this.simplestmt.genC(pw);
        } else {
            pw.println("{\n");
            for (Stmt stmt : stmts) {

                pw.add();
                stmt.genC(pw);
                pw.sub();

            }
            pw.println("}");
        }
    }

    private List<Stmt> stmts;
    private boolean isSimpleStmt; //se false eh stmt+
    private SimpleStmt simplestmt;
}
