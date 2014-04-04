/*
 * Augassign: apenas getters, setters e genC
 */
package AST;

public class Augassign {

    public Augassign(String op) {
        this.op = op;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public void genC(PW pw) {
        pw.print(op);
    }

    private String op;
}
