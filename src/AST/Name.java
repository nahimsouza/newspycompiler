/*
 * Name: apenas uma string. Usada por causa do genC (?)
 */
package AST;

public class Name {

    public Name(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public void genC(PW pw) {
        pw.print("_" + name);
    }
    
    private String name;
}
