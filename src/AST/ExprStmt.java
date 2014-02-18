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

    
    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        targetlist.genC(tab + 1);
//        augassign.genC(tab + 1);
//        testlist.genC(tab + 1);
    }

    private Targetlist targetlist;
    private Augassign augassign;
    private Listmaker listmaker;
}
