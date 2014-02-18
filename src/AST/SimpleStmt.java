/*
 * Classe SimpleStmt
 */
package AST;

import java.util.ArrayList;
import java.util.List;

public class SimpleStmt extends Stmt {

    public SimpleStmt() {
        this.smallStmts = new ArrayList<SmallStmt>();
    }

    public void addStmt(SmallStmt stmt) {
        this.smallStmts.add(stmt);
    }

    public void genC(int tabs) {

// Deixei comentado o codigo do Tomazella, precisa conferir

//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        for (SmallStmt s : smallStmts) {
//            s.genC(tab + 1);
//        }
    }

    
    private List<SmallStmt> smallStmts;
}
