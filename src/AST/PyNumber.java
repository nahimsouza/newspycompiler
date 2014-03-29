/*
 * PyNumber: representa um numero em Python
 */
package AST;

public class PyNumber {

    public PyNumber(double n) {
        number = n;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
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

    private double number;
}
