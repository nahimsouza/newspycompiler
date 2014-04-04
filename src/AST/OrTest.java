/*
 * OrTest: guarda apenas uma lista de AndTest
 */
package AST;

import java.util.LinkedList;
import java.util.List;

public class OrTest {

    public OrTest() {
        this.andtest = new LinkedList<AndTest>();
    }

    public void addAndTest(AndTest a) {
        this.andtest.add(a);
    }

    public List<AndTest> getAndtest() {
        return andtest;
    }
    
    

    public void genC(PW pw) {
                
//        pw.print("(");
        this.andtest.get(0).genC(pw);
        for(int i = 1; i < this.andtest.size(); i++){
            pw.print(" || ");    
            this.andtest.get(i).genC(pw);
        }
//        pw.print(")");
    }

    private List<AndTest> andtest;
}
