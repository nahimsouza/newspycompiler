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

    public void genC(PW pw) {
        this.fpdefs.get(0).genC(pw);
        for (int i = 1; i < this.fpdefs.size(); i++) {
            pw.print(", ");
            this.fpdefs.get(i).genC(pw);
        }
    }
    private List<Fpdef> fpdefs;
}
