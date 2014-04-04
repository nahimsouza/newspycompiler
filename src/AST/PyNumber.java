/*
 * PyNumber: representa um numero em Python
 */
package AST;

public class PyNumber {

    public PyNumber(double n) {
        number = n;
        
        double x = Math.round(number);
        if (x == (double) number) {
            this.numberType = "int";
        } else {
            this.numberType = "float";
        }
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;

        double x = Math.round(number);
        if (x == (double) number) {
            this.numberType = "int";
        } else {
            this.numberType = "float";
        }
    }

    public String getNumberType() {
        return numberType;
    }

    public void setNumberType(String numberType) {
        this.numberType = numberType;
    }
    
    

    public void genC(PW pw) {
        pw.print(Double.toString(number));
    }

    private double number;
    private String numberType;
}
