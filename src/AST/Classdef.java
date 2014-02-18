/*
 * Classdef: guarda um NAME, uma lista de Atom e um Suite.
 */
package AST;

import java.util.ArrayList;

public class Classdef extends CompoundStmt{

    public Classdef() {
        this.atoms = new ArrayList<Atom>();
    }

    public void addAtom(Atom a) {
        this.atoms.add(a);
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Suite getSuite() {
        return suite;
    }

    public void setSuite(Suite suite) {
        this.suite = suite;
    }
    
    public void genC(PW pw){
// GenC do Vinicius, apenas como base, precisa verificar arrumar algumas coisas
        
//        pw.print("typedef struct ");
//        this.name.genC(pw);
//        pw.println("{");
//        // if (this.name != null){
//        //     this.name.genC(pw);
//        // }
//        
//        for (int i = 0; i < this.atoms.size(); i++){
//            this.atoms.get(i).genC(pw);
//            pw.println(";");
//        }
//        pw.print("}");
//        this.name.genC(pw);
//        pw.println(";");
//
//        
//        if (this.suite != null)
//            this.suite.genC(pw);
    }

    private Name name;
    private ArrayList<Atom> atoms;
    private Suite suite;

}
