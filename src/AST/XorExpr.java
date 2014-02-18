/*
 * XorExpr: apenas uma lista de AndExpr
 */
package AST;

import java.util.ArrayList;
import java.util.List;

public class XorExpr {

    public XorExpr() {
        this.andexprs = new ArrayList<AndExpr>();
    }

    public void addAndExpr(AndExpr a) {
        this.andexprs.add(a);
    }

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        for (AndExpr a : andexprs) {
//            a.genC(tab + 1);
//        }
    }

    private List<AndExpr> andexprs;
}
