/*
 * Fpdef: guarda uma Fplist ou um NAME. Possui uma flag p/ saber quando eh Name.
 */
package AST;

public class Fpdef {

    public boolean isName() {
        return isName;
    }

    public void setName(boolean isName) {
        this.isName = isName;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Fplist getFplist() {
        return fplist;
    }

    public void setFplist(Fplist fplist) {
        this.fplist = fplist;
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

    private boolean isName; //else eh fplist
    private Name name;
    private Fplist fplist;
}
