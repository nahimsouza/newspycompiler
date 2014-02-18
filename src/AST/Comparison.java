/*
 * Comparision: guarda um Expr, e uma lista CompOps e Exprs.
 */
package AST;

import java.util.ArrayList;
import java.util.List;

public class Comparison {

    public Comparison() {
        this.compOps = new ArrayList<CompOp>();
        this.exprs = new ArrayList<Expr>();
    }

    public void addCompOp(CompOp c) {
        this.compOps.add(c);
    }

    public void addExprs(Expr e) {
        this.exprs.add(e);
    }

    public Expr getExpr() {
        return expr;
    }

    public void setExpr(Expr expr) {
        this.expr = expr;
    }

    public List<CompOp> getCompOps() {
        return compOps;
    }

    public void setCompOps(List<CompOp> compOps) {
        this.compOps = compOps;
    }

    public List<Expr> getExprs() {
        return exprs;
    }

    public void setExprs(List<Expr> exprs) {
        this.exprs = exprs;
    }

    public void genC(int count) {
//        String str = "";
//        int aux_c = count;
//
//        while (count != 0) {
//            str = str.concat("  ");
//            count--;
//        }
//
//        System.out.println(str + this.getClass().getName());
//
//        aux_c++;
//        expr.genC(aux_c);
//        for (Expr e : exprs) {
//            e.genC(aux_c);
//        }
//        for (CompOp c : compOps) {
//            c.genC(aux_c);
//        }
    }

    private Expr expr;
    private List<CompOp> compOps;
    private List<Expr> exprs;
}
