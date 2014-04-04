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

    public void genC(PW pw) {
        pw.print("\"" + string + "\"");
    }

    private String string;
}
