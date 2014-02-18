/*
 * Factor: guarda um operador e um Factor, ou, um Atom. 
 * Possui uma variavel isFactor apenas para saber se nao eh um Atom.
*/

package AST;

public class Factor {

    public boolean isFactor() {
        return isFactor;
    }

    public void setFactor(boolean isFactor) {
        this.isFactor = isFactor;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Factor getFactor() {
        return factor;
    }

    public void setFactor(Factor factor) {
        this.factor = factor;
    }

    public Atom getAtom() {
        return atom;
    }

    public void setAtom(Atom atom) {
        this.atom = atom;
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
//        if (isFactor) {
//            factor.genC(aux_c);
//            str = str.concat("  ");
//            System.out.println(str + this.getClass().getName());
//        } else {
//            atom.genC(aux_c);
//        }
    }

    private boolean isFactor; //se eh factor ou atom
    private String op;
    private Factor factor;
    private Atom atom;
}
