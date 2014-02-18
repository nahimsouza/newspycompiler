/*
 * Fplist: guarda uma lista de Fpdfs
 */
package AST;

import java.util.ArrayList;
import java.util.List;

public class Fplist {

    public Fplist() {
        this.fpdefs = new ArrayList<Fpdef>();
    }

    public void addFpdef(Fpdef f) {
        this.fpdefs.add(f);
    }

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        for (Fpdef f : fpdefs) {
//            f.genC(tab + 1);
//        }
 
    }
    private List<Fpdef> fpdefs;
}
