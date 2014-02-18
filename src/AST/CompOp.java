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

    public void genC(int count) {
//        String str = "";
//
//        while (count != 0) {
//            str = str.concat("  ");
//            count--;
//        }
//
//        System.out.println(str + this.getClass().getName());
//
//        str = str.concat("  ");
//        System.out.println(str + this.getClass().getName() + op.toString());
    }

}
