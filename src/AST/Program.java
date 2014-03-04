package AST;

import java.util.*;

public class Program {

    public Program(Vector<Stmt> stmtList) {
        this.stmtList = stmtList;
    }

    public Vector<Stmt> getStmtList() {
        return stmtList;
    }

    public void genC(PW pw) {

          // genC do Vinicius e Victor, verificar depois

//        Stmt tmp = null;
//        //System.out.println("PROGRAM");
//        pw.println("#include <stdio.h>");
//        pw.println("#include <stdlib.h>");
//        pw.println(" ");
//        pw.println("int main() {");
//        pw.add();
//        for (int i = 0; i < this.classList.size(); i++) {
//            tmp = (Stmt) this.classList.get(i);
//            if (tmp != null) {
//                tmp.genC(pw);
//            }
//        }
//        //pw.println(" ");
//        pw.println("return 0;");
//        pw.sub();
//        pw.println("}");
    }

    private Vector<Stmt> stmtList;
}
