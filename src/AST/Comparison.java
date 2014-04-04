/*
 * Comparision: guarda um Expr, e uma lista CompOps e Exprs.
 */
package AST;

import java.util.LinkedList;
import java.util.List;

public class Comparison {

    public Comparison() {
        this.compOps = new LinkedList<CompOp>();
        this.exprs = new LinkedList<Expr>();
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

    public void genC(PW pw) {
        expr.genC(pw);
        for(int i = 1; i < this.compOps.size(); i++){
            compOps.get(i).genC(pw);
            exprs.get(i).genC(pw);
        }            
    }

    private Expr expr;
    private List<CompOp> compOps;
    private List<Expr> exprs;
}
