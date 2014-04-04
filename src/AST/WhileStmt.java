/*
 * WhileStmt: contem um Test e dois Suites (um a mais para o ELSE)
 */
package AST;

public class WhileStmt extends CompoundStmt {
    
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Suite getSuite() {
        return suite;
    }

    public void setSuite(Suite suite) {
        this.suite = suite;
    }

    public Suite getEsuite() {
        return esuite;
    }

    public void setEsuite(Suite esuite) {
        this.esuite = esuite;
    }

    public void genC(PW pw) {
        pw.print("while ( ");
        this.test.genC(pw);
        pw.print(")");
        this.suite.genC(pw);
                
        // while-else
        if (this.esuite != null) {
            pw.print("if (!(" + test + "))");
            this.esuite.genC(pw);
        }
    }

    private Test test;
    private Suite suite;
    private Suite esuite;
}
