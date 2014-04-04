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

    public List<AndExpr> getAndexprs() {
        return andexprs;
    }

    public void genC(PW pw) {
    
//        pw.print("(");
        this.andexprs.get(0).genC(pw);
        for(int i = 1; i < this.andexprs.size(); i++){
            pw.print(" ^ ");    
            this.andexprs.get(i).genC(pw);
        }
//        pw.print(")");

    }

    private List<AndExpr> andexprs;
}
