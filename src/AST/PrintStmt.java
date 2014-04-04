/*
 * PrintStmt: apenas uma lista de Tests.
 */
package AST;

import java.util.LinkedList;
import java.util.List;

public class PrintStmt extends SmallStmt {

    public PrintStmt() {
        this.tests = new LinkedList<Test>();
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

    public void genC(PW pw) {
        pw.print("printf(");
        pw.print("\"");
        for (int i = 0; i < this.tests.size(); i++) {
            this.tests.get(i).verifyType();
            String testType = this.tests.get(i).getTipo();
            String format = null;
            if (testType.equalsIgnoreCase("bool")) {
                format = " %d";
            } else if (testType.equalsIgnoreCase("number") || testType.equalsIgnoreCase("floatCast")) {
                format = " %f";
            } else if (testType.equalsIgnoreCase("string") || testType.equalsIgnoreCase("name")) {
                format = " %s";
            } else if (testType.equalsIgnoreCase("int") || testType.equalsIgnoreCase("intCast")) {
                format = " %d";
            } else {
                format = "%d";
            }
            pw.print(format);
        }
        pw.print("\"");

        for (int i = 0; i < this.tests.size(); i++) {
            pw.print(", ");
            this.tests.get(i).genC(pw);
        }

        pw.println(");");
    }



    private List<Test> tests;
}
