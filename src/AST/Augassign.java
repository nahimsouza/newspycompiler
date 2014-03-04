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

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        System.out.println(x + "  " + op);
    }

    private String op;
}
