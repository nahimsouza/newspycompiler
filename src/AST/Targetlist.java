/*
 * Targetlist: uma lista de Targets
 */
package AST;

import java.util.LinkedList;
import java.util.List;

public class Targetlist {

    public Targetlist() {
        this.targets = new LinkedList<Target>();
    }

    public void addTarget(Target t) {
        this.targets.add(t);
    }

    public void genC(PW pw) {
        
        /*
            A ideia eh imprimir algo do tipo:
            var1 = var2 = var3      (qndo tiver uma lista)
        
            E imprimir:
            var1             (qndo imprimir apenas 1)
        */
        
        this.targets.get(0).genC(pw);
        for(int i = 1; i < this.targets.size(); i++){
            pw.print(" = ");    
            this.targets.get(i).genC(pw);
        }
    }

    private List<Target> targets;
}
