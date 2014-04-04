/*
 * Varargslist: uma lista de argumentos para as funcoes
 */
package AST;

import java.util.LinkedList;
import java.util.List;

public class Varargslist {

    public Varargslist() {
        this.fpdefs = new LinkedList<Fpdef>();
        this.tests = new LinkedList<Test>();
    }

    public void addTest(Test t) {
        this.tests.add(t);
    }

    public void addFpdef(Fpdef f) {
        this.fpdefs.add(f);
    }

    public List<Fpdef> getFpdefs() {
        return fpdefs;
    }

    public void setFpdefs(List<Fpdef> fpdefs) {
        this.fpdefs = fpdefs;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
   
    public void genC(PW pw) {
        
        this.fpdefs.get(0).genC(pw);
        if (this.tests.get(0) != null) {
            pw.print("=");
            tests.get(0).genC(pw);
        }
        for (int i = 1; i < this.fpdefs.size(); i++) {
            pw.print(", ");
            this.fpdefs.get(i).genC(pw);
            if (this.tests.get(i) != null) {
                pw.print("=");
                tests.get(i).genC(pw);
            }
        }
    }

    private List<Fpdef> fpdefs;
    private List<Test> tests;
}
