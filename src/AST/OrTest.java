/*
 * OrTest: guarda apenas uma lista de AndTest
 */
package AST;

import java.util.ArrayList;
import java.util.List;

public class OrTest {

    public OrTest() {
        this.andtest = new ArrayList<AndTest>();
    }

    public void addAndTest(AndTest a) {
        this.andtest.add(a);
    }

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        for (AndTest a : andtest) {
//            a.genC(tab + 1);
//        }
    }

    private List<AndTest> andtest;
}
