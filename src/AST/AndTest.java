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

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        for (NotTest t : nottest) {
//            t.genC(tab + 1);
//        }
    }

    private List<NotTest> nottest;
}
