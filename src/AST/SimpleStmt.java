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

    public void genC(PW pw) {

        for (SmallStmt smallStmt : smallStmts) {
            smallStmt.genC(pw);
        }
        
    }

    private List<SmallStmt> smallStmts;
}
