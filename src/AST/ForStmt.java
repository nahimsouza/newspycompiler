/*
 * ForStmt: contem um NAME (q ficou no lugar do exprlist), um Atom, dois Suites,
 * e dois numeros
 */
package AST;

public class ForStmt extends CompoundStmt {

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Atom getAtom() {
        return atom;
    }

    public void setAtom(Atom atom) {
        this.atom = atom;
    }

    public Suite getSuite() {
        return suite;
    }

    public void setSuite(Suite suite) {
        this.suite = suite;
    }

    public Suite getElseSuite() {
        return elseSuite;
    }

    public void setElseSuite(Suite elseSuite) {
        this.elseSuite = elseSuite;
    }

    public double getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public double getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public void genC(PW pw) {
        // imprime o for comum: for(i=0; i<10; i++)
        
        String nome = name.getName();
        pw.print("int " + nome + ";");
        pw.print("for (" + nome + "=" + number1.toString() + "; " + nome + "<=" + number2.toString() + "; " + nome + "++");
        if (suite != null) {
            suite.genC(pw);
        }
        
        // TODO: parte ELSE do FOR
        // TODO: for NAME in Atom:
        
    }

    private Name name;
    private Atom atom;
    private Suite suite;
    private Suite elseSuite;
    private Integer number1;
    private Integer number2;
}
