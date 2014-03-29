/*
 * Atom: guarda um listmaker ou Name ou NUMBER ou STRING (podem ser varias STRINGS)
 */
package AST;

import java.util.ArrayList;
import java.util.List;

public class Atom {

    public Atom() {
        string = new ArrayList<PyString>();
    }

    public Listmaker getListmaker() {
        return listmaker;
    }

    public void setListmaker(Listmaker listmaker) {
        this.listmaker = listmaker;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public PyNumber getNumber() {
        return number;
    }

    public void setNumber(PyNumber number) {
        this.number = number;
    }

    public void addString(PyString string) {
        this.string.add(string);
    }

    public List<PyString> getStringList() {
        return string;
    }
    
    public String getTipo() {
        return tipo;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public Name getFuncName() {
        return funcName;
    }

    public void setFuncName(Name funcName) {
        this.funcName = funcName;
    }

    public Test getParTest() {
        return parTest;
    }

    public void setParTest(Test parTest) {
        this.parTest = parTest;
    }

    // Apenas armazena em tipo o que esta sendo guardado no Atom.
    public void setToName() {
        tipo = "name";
    }
    
    public void setToListmaker() {
        tipo = "listmaker";
    }

    public void setToNumber() {
        tipo = "number";
    }

    public void setToString() {
        tipo = "string";
    }
    
    public void setToSelf() {
        tipo = "self";
    }
    
    public void setToFunc(){
        tipo = "func";
    }
    
    public void setToParTest(){
        tipo = "partest";
    }
    
    public void setToIntCast(){
        tipo = "intTest";
    }
    
    public void setToFloatCast(){
        tipo = "floatCast";
    }
    

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        if (tipo.equals("testlist1")) {
//            testlist1.genC(tab + 1);
//        } else if (tipo.equals("name")) {
//            name.genC(tab + 1);
//        } else if (tipo.equals("number")) {
//            number.genC(tab + 1);
//        } else if (tipo.equals("string")) {
//            for (PyString s : string) {
//                s.genC(tab + 1);
//            }
//        }
    }
    private Listmaker listmaker;
    private Name name;
    private Name funcName;
    private PyNumber number;
    private List<PyString> string;
    private String tipo;
    private Parameters parameters;
    private Test parTest;

}
