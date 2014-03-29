/*
 * Fpdef: guarda uma Fplist ou um NAME. Possui uma flag p/ saber quando eh Name.
 */
package AST;

public class Fpdef {

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public PyNumber getNumber() {
        return number;
    }

    public void setNumber(PyNumber number) {
        this.number = number;
    }

    public Fplist getFplist() {
        return fplist;
    }

    public void setFplist(Fplist fplist) {
        this.fplist = fplist;
    }
    
    public void setToName(){
        this.tipo = "name";
    }
    
    public void setToFplist(){
        this.tipo = "fplist";
    }
    
    public void setToSelf(){
        this.tipo = "self";
    }
        
    public void setToNumber() {
        this.tipo = "number";
    }


    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        if (isName) {
//            name.genC(tab + 1);
//        } else {
//            fplist.genC(tab + 1);
//        }
    }

    private Name name;
    private Fplist fplist;
    private String tipo;
    private PyNumber number;

    
}
