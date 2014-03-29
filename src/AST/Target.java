/*
 * Target: apenas getters, setters para o NAME e o genC.
 */
package AST;

public class Target {

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
    
    public void setToName(){
        this.tipo = "name";
    }
    
    public void setToSelf(){
        this.tipo = "self";
    }

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        name.genC(tab + 1);
    }

    private Name name;
    private String tipo;
}
