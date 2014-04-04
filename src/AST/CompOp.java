/*
 * CompOp: guarda o operador, e faz o genC.
 */
package AST;

public class CompOp {

    private String op;

    public CompOp(String op) {
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

}
