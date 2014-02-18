/*
 * PyNumber: representa um numero em Python
 */
package AST;

public class PyNumber {

    public PyNumber(int n) {
        number = n;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName() + ": " + number);
    }

    private int number;
}
