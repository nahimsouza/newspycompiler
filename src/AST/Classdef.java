/*
 * Classdef: guarda um NAME, uma lista de Atom e um Suite.
 */
package AST;

import java.util.LinkedList;
import java.util.List;

public class Classdef extends CompoundStmt{

    public Classdef() {
        this.atoms = new LinkedList<Atom>();
        this.functions = new LinkedList<Funcdef>();
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
        
        pw.print("\ntypedef struct _St_" + name.getName() + " {");
        pw.print("\n\tFunc *vt; \n\t} " + "_class_" + name.getName() + ";");
        
        pw.print("\n\n\tclass_" + name.getName() + " *new_A(void); \n");
        
        
        List<Stmt> stmts = this.getSuite().getStmts();
        if (stmts != null) {
            for (Stmt stmt : stmts) {
                if (stmt instanceof Funcdef) {
                    Funcdef f = (Funcdef) stmt;
                    f.setClassname(this.name.getName());
                    if (f.getParameters() != null) {
                        List<Fpdef> fpdefs = f.getParameters().getVarargslist().getFpdefs();
                        for (Fpdef fpdef : fpdefs) {
                            fpdef.setClassname(this.name.getName());
                        }
                    }
                }
            }
        }
        
        
        
        // OBS: suite coloca identacao e imprime {}
        pw.print(" ");
        if (this.suite != null) {
            suite.genC(pw);
        }
        
        // Adicionar uma lista de metodos na classe
        if (stmts != null) {
            for (Stmt stmt : stmts) {
                if (stmt instanceof Funcdef) {
                    functions.add((Funcdef) stmt);
                }
            }
        }
        
        if (functions.size() > 0) {
            pw.print("\n\nFunc VTclass_" + name.getName() + "[] = {\n\t");
            
            pw.print("( void (*)() ) _" + name.getName() + "_" + functions.get(0).getName().getName());
            for (int i = 1; i < functions.size(); i++) {
                pw.print(",");
                pw.print("( void (*)() ) _" + name.getName() + "_" + functions.get(i).getName().getName());
            }
            pw.print("\n");
            pw.print("};\n\n");
            
            
            pw.print("\n\n_class_" + name.getName() + " *new_" + name.getName() + "()\n" + "{\n" + " _class_" + name.getName() + " *t;\n");
            pw.print("  if ( (t = malloc(sizeof(_class_" + name.getName() + "))) != NULL )\n\t");
            pw.print("t->vt = VTclass_" + name.getName() + ";");
            pw.print("\n");
            pw.print("return t;\n } \n\n"); 
        }
        
    }

    private Name name;
    private LinkedList<Atom> atoms;
    private Suite suite;
    private LinkedList<Funcdef> functions;

}
