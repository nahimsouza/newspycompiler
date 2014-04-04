/*
 * Funcdef: armazena um NAME os PARAMETERS e um Suite
 */
package AST;

public class Funcdef extends CompoundStmt {

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public Suite getSuite() {
        return suite;
    }

    public void setSuite(Suite suite) {
        this.suite = suite;
    }

    public void genC(PW pw) {
        pw.print("void _CN_" + name.getName() + "(");
        if (this.parameters.getVarargslist() != null) {
            parameters.genC(pw);
        }
        pw.print(")");
        
        pw.print(" ");
        if (this.suite != null) {
            suite.genC(pw);
        }
    }

    private Name name;
    private Parameters parameters;
    private Suite suite;
}
