/*
 * PrintStmt: apenas uma lista de Tests.
 */
package AST;

import java.util.ArrayList;
import java.util.List;

public class PrintStmt extends SmallStmt {

    public PrintStmt() {
        this.tests = new ArrayList<Test>();
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public void addTest(Test test) {
        tests.add(test);
    }

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        for (Test t : tests) {
//            t.genC(tab + 1);
//        }
    }

    
    private List<Test> tests;
}
