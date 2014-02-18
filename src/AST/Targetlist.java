/*
 * Targetlist: uma lista de Targets
 */
package AST;

import java.util.ArrayList;
import java.util.List;

public class Targetlist {

    public Targetlist() {
        this.targets = new ArrayList<Target>();
    }

    public void addTarget(Target t) {
        this.targets.add(t);
    }

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        for (Target t : targets) {
//            t.genC(tab + 1);
//        }
    }

    private List<Target> targets;
}
