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

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        name.genC(tab + 1);
//        parameters.genC(tab + 1);
//        suite.genC(tab + 1);
    }

    private Name name;
    private Parameters parameters;
    private Suite suite;
}
