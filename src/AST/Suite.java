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

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        if (isSimpleStmt) {
//            simplestmt.genC(tab + 1);
//        } else {
//            for (Stmt s : stmts) {
//                s.genC(tab + 1);
//            }
//        }
    }

    private List<Stmt> stmts;
    private boolean isSimpleStmt; //se false eh stmt+
    private SimpleStmt simplestmt;
}
