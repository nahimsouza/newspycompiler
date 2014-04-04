/*
 * ArithExpr: uma lista de operadores e outra de Terms.
 */
package AST;

import java.util.LinkedList;
import java.util.List;

public class ArithExpr {

    public ArithExpr() {
        this.terms = new LinkedList<Term>();
        this.ops = new LinkedList<String>();
    }

    public void genC(PW pw) {

//        pw.print("(");
        this.term.genC(pw);
        for(int i = 0; i < this.terms.size(); i++){
            pw.print( ops.get(i));    
            this.terms.get(i).genC(pw);
        }
//        pw.print(")");

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

    public List<Term> getTerms() {
        return terms;
    }

    public List<String> getOps() {
        return ops;
    }

    private Term term;
    private List<Term> terms;
    private List<String> ops;
}
