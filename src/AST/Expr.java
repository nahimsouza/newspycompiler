/*
 * Expr: possui apenas uma lista de XorExpr
 */
package AST;

import java.util.LinkedList;
import java.util.List;

public class Expr {

    public Expr() {
        this.xorexprs = new LinkedList<XorExpr>();
    }
    
    public void addXorExpr(XorExpr e) {
        this.xorexprs.add(e);
    }

    public List<XorExpr> getXorexprs() {
        return xorexprs;
    }
    
    public void genC(PW pw) {

//        pw.print("(");
        this.xorexprs.get(0).genC(pw);
        for(int i = 1; i < this.xorexprs.size(); i++){
            pw.print(" | ");    
            this.xorexprs.get(i).genC(pw);
        }
//        pw.print(")");

    }

    private List<XorExpr> xorexprs;
}
