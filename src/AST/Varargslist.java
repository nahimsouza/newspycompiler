/*
 * Varargslist: uma lista de argumentos para as funcoes
 */
package AST;

import java.util.ArrayList;
import java.util.List;

public class Varargslist {

    public Varargslist() {
        this.fpdefs = new ArrayList<Fpdef>();
        this.tests = new ArrayList<Test>();
    }

    public void addTest(Test t) {
        this.tests.add(t);
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
//        for (Test t : tests) {
//            t.genC(tab + 1);
//        }
//        if (name1 != null) {
//            name1.genC(tab + 1);
//        }
//        if (name2 != null) {
//            name2.genC(tab + 1);
//        }
    }

    private List<Fpdef> fpdefs;
    private List<Test> tests;
}
