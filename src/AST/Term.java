/*
* Term: guarda uma lista de Operadores e uma lista de Factors
*/

package AST;

import java.util.ArrayList;
import java.util.List;

public class Term {

    public Term() {
        this.factors = new ArrayList<Factor>();
        this.ops = new ArrayList<String>();
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
//
//        factor.genC(aux_c);
//
//        for (Factor f : factors) {
//            f.genC(aux_c);
//        }
//
//        str = str.concat(" ");
//        for (String o : ops) {
//            System.out.println(str + o);
//        }
    }

    private List<Factor> factors;
    private List<String> ops;
    private Factor factor;
}
