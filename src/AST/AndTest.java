/*
 * AndTest: apenas uma lista de NotTest.
 */
package AST;

import java.util.ArrayList;
import java.util.List;

public class AndTest {

    public AndTest() {
        nottest = new ArrayList<NotTest>();
    }

    public void addNottest(NotTest t) {
        this.nottest.add(t);
    }

    public List<NotTest> getNottest() {
        return nottest;
    }
    
    

    public void genC(PW pw) {

//        pw.print("(");
        this.nottest.get(0).genC(pw);
        for(int i = 1; i < this.nottest.size(); i++){
            pw.print(" && ");    
            this.nottest.get(i).genC(pw);
        }
//        pw.print(")");

    }

    private List<NotTest> nottest;
}
