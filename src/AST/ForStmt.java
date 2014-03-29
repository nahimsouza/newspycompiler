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

    public void setNumber1(double number1) {
        this.number1 = number1;
    }

    public double getNumber2() {
        return number2;
    }

    public void setNumber2(double number2) {
        this.number2 = number2;
    }

    public void genC(int tabs) {
        // acho q vai mudar tudo isso ae...

//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        exprlist.genC(tab + 1);
//        testlist.genC(tab + 1);
//        suite.genC(tab + 1);
//        esuite.genC(tab + 1);
    }

    private Name name;
    private Atom atom;
    private Suite suite;
    private Suite elseSuite;
    private double number1;
    private double number2;
}
