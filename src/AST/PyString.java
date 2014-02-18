/*
 * PyString: classe que representa uma String de Python
 */
package AST;

public class PyString {

    public PyString(String s) {
        string = s;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName() + ": " + string);
    }

    private String string;
}
