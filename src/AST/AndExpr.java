/*
 * AndExpr: uma lista de ArithExpr
 */
package AST;

import java.util.ArrayList;
import java.util.List;

public class AndExpr {

    public AndExpr() {
        this.arithexprs = new ArrayList<ArithExpr>();
    }

    public void addArithExpr(ArithExpr a) {
        this.arithexprs.add(a);
    }

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        for (ArithExpr a : arithexprs) {
//            a.genC(tab + 1);
//        }
    }

    private List<ArithExpr> arithexprs;
}
