/*
 * IfStmt: possui um Test e Suite para o IF, outro Test e Suite para o ELIF,
 * outro Suite para o ELSE.
 */
package AST;

import java.util.LinkedList;
import java.util.List;

public class IfStmt extends CompoundStmt {

    public IfStmt() {
        telif = new LinkedList<Test>();
        selif = new LinkedList<Suite>();
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

    public void genC(PW pw) {
        pw.print("if(");
        this.tif.genC(pw);
        pw.print(")");
        this.sif.genC(pw);
        
        for(int i = 0; i < this.selif.size(); i++){
            pw.print("else if(");
            this.telif.get(i).genC(pw);
            pw.print(")");
            this.selif.get(i).genC(pw);
        }
        
        if (this.selse != null){
            pw.print("else");
            this.selse.genC(pw);

        }
    }

    private Test tif;
    private Suite sif;
    private List<Test> telif;
    private List<Suite> selif;
    private Suite selse;
}
