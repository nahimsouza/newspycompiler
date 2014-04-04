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

    public List<ArithExpr> getArithexprs() {
        return arithexprs;
    }
    

    public void genC(PW pw) {
//       pw.print("(");
        this.arithexprs.get(0).genC(pw);
        for(int i = 1; i < this.arithexprs.size(); i++){
            pw.print(" & ");    
            this.arithexprs.get(i).genC(pw);
        }
//        pw.print(")");

    }

    private List<ArithExpr> arithexprs;
}
