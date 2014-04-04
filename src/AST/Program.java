package AST;

import java.util.*;

public class Program {

    public Program(LinkedList<Stmt> stmtList) {
        this.stmtList = stmtList;
    }

    public LinkedList<Stmt> getStmtList() {
        return stmtList;
    }

    public void genC(PW pw) {

        // Based on Angiolucci & Marucci

        Stmt tmp = null;
        //System.out.println("PROGRAM");
        
        pw.println("#include <stdio.h>");
        pw.println("#include <stdlib.h>");
        pw.println(" ");
        
        pw.println("// Define o tipo Func que eh um ponteiro para funcao ");
        pw.println("typedef void (*Func)();");
        pw.println(" ");
              
        pw.println("int main() {");
        pw.add();
        
        for (Stmt stmt : stmtList) {
            if (stmt != null) {
                stmt.genC(pw);
            }
        }
        
        pw.println(" ");
        pw.println("return 0;");
        pw.sub();
        pw.println("}");
    }

    private LinkedList<Stmt> stmtList;
}
