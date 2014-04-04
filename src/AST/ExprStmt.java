/*
 * ExprStmt: apenas getters, setters e genC.
 */
package AST;

public class ExprStmt extends SmallStmt {

    public Targetlist getTargetlist() {
        return targetlist;
    }

    public void setTargetlist(Targetlist targetlist) {
        this.targetlist = targetlist;
    }

    public Augassign getAugassign() {
        return augassign;
    }

    public void setAugassign(Augassign augassign) {
        this.augassign = augassign;
    }

    public Listmaker getListmaker() {
        return listmaker;
    }

    public void setListmaker(Listmaker listmaker) {
        this.listmaker = listmaker;
    }

    public void genC(PW pw) {
        
        /*
            Deve imprimir algo como:
            x += 2;
        */
        
        this.targetlist.genC(pw);
        if (this.augassign != null) {
            this.augassign.genC(pw);
            this.listmaker.genC(pw);
        }
        pw.print(";");
        pw.println(" ");
    }

    private Targetlist targetlist;
    private Augassign augassign;
    private Listmaker listmaker;
}
