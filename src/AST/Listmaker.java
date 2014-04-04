/*
 * Listmaker: apenas uma lista de Tests
 */
package AST;

import java.util.LinkedList;
import java.util.List;

public class Listmaker {

    public Listmaker() {
        this.tests = new LinkedList<Test>();
    }

    public void addTest(Test t) {
        this.tests.add(t);
    }

    public void genC(PW pw) {
                
        this.tests.get(0).genC(pw);
        for(int i = 1; i < this.tests.size(); i++){
            pw.print(", ");    
            this.tests.get(i).genC(pw);
        }
    }

    private List<Test> tests;
}
