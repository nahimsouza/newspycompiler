/*
* Term: guarda uma lista de Operadores e uma lista de Factors
*/

package AST;

import java.util.LinkedList;
import java.util.List;

public class Term {

    public Term() {
        this.factors = new LinkedList<Factor>();
        this.ops = new LinkedList<String>();
    }

    public void addFactor(Factor f) {
        this.factors.add(f);
    }

    public void addOp(String op) {
        this.ops.add(op);
    }

    public Factor getFactor() {
        return factor;
    }

    public void setFactor(Factor factor) {
        this.factor = factor;
    }

    public List<Factor> getFactors() {
        return factors;
    }

    public List<String> getOps() {
        return ops;
    }
    
    public void genC(PW pw) {
        
        this.factor.genC(pw);
        for(int i = 1; i < this.factors.size(); i++){
            pw.print(ops.get(i));    
            this.factors.get(i).genC(pw);
        }
    }

    private List<Factor> factors;
    private List<String> ops;
    private Factor factor;
}
