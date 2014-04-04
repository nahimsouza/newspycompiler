/*
 * Factor: guarda um operador e um Factor, ou, um Atom. 
 * Possui uma variavel isFactor apenas para saber se nao eh um Atom.
*/

package AST;

public class Factor {

    public boolean isFactor() {
        return isFactor;
    }

    public void setIsFactor(boolean isFactor) {
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

    public void genC(PW pw) {

        if (isFactor) {
            pw.print(op);
            this.factor.genC(pw);
        } else {
            atom.genC(pw);
        }
        
    }

    private boolean isFactor; //se eh factor ou atom
    private String op;
    private Factor factor;
    private Atom atom;
}
