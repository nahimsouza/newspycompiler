/*
 * Listmaker: apenas uma lista de Tests
 */
package AST;

import java.util.ArrayList;
import java.util.List;

public class Listmaker {

    public Listmaker() {
        this.tests = new ArrayList<Test>();
    }

    public void addTest(Test t) {
        this.tests.add(t);
    }

    public void genC(int count) {
//        String str = "";
//        int aux_c = count;
//
//        while(count != 0){
//            str = str.concat("  ");
//            count--;
//        }
//        
//        System.out.println(str + this.getClass().getName());
//        aux_c++;
//        for(Test t: tests){
//            t.genC(aux_c);
//        }
    }

    private List<Test> tests;
}
