/*
 * ArithExpr: uma lista de operadores e outra de Terms.
 */
package AST;

import java.util.ArrayList;
import java.util.List;

public class ArithExpr {

    public ArithExpr() {
        this.terms = new ArrayList<Term>();
        this.ops = new ArrayList<String>();
    }

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        term.genC(tab + 1);
//        for (Term t : terms) {
//            t.genC(tab + 1);
//        }
//        for (String s : ops) {
//            System.out.println(x + "  " + s.toString());
//        }
    }

    public void addTerm(Term t) {
        this.terms.add(t);
    }

    public void addOp(String s) {
        this.ops.add(s);
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    private Term term;
    private List<Term> terms;
    private List<String> ops;
}
