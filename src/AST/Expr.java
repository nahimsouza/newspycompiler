/*
 * Expr: possui apenas uma lista de XorExpr
 */
package AST;

import java.util.ArrayList;
import java.util.List;

public class Expr {

    public Expr() {
        this.xorexprs = new ArrayList<XorExpr>();
    }
    
    public void addXorExpr(XorExpr e) {
        this.xorexprs.add(e);
    }

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        for (XorExpr e : xorexprs) {
//            e.genC(tab + 1);
//        }
    }

    private List<XorExpr> xorexprs;
}
