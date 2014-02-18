/*
 * IfStmt: possui um Test e Suite para o IF, outro Test e Suite para o ELIF,
 * outro Suite para o ELSE.
 */
package AST;

import java.util.ArrayList;
import java.util.List;

public class IfStmt extends CompoundStmt {

    public IfStmt() {
        telif = new ArrayList<Test>();
        selif = new ArrayList<Suite>();
    }

    public void addTelif(Test telif) {
        this.telif.add(telif);
    }

    public void addSelif(Suite selif) {
        this.selif.add(selif);
    }

    public Test getTif() {
        return tif;
    }

    public void setTif(Test tif) {
        this.tif = tif;
    }

    public Suite getSif() {
        return sif;
    }

    public void setSif(Suite sif) {
        this.sif = sif;
    }

    public List<Test> getTelif() {
        return telif;
    }

    public void setTelif(List<Test> telif) {
        this.telif = telif;
    }

    public List<Suite> getSelif() {
        return selif;
    }

    public void setSelif(List<Suite> selif) {
        this.selif = selif;
    }

    public Suite getSelse() {
        return selse;
    }

    public void setSelse(Suite selse) {
        this.selse = selse;
    }

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        tif.genC(tab + 1);
//        sif.genC(tab + 1);
//        for (Test t : telif) {
//            t.genC(tab + 1);
//        }
//        for (Suite t : selif) {
//            t.genC(tab + 1);
//        }
//        if (selse != null) {
//            selse.genC(tab + 1);
//        }
    }

    private Test tif;
    private Suite sif;
    private List<Test> telif;
    private List<Suite> selif;
    private Suite selse;
}
