/*
 * Atom: guarda um listmaker ou Name ou NUMBER ou STRING (podem ser varias STRINGS)
 */
package AST;

import java.util.ArrayList;
import java.util.List;

public class Atom {

    public Atom() {
        string = new ArrayList<PyString>();
        hasParameters = false;
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

    public boolean hasParameters() {
        return hasParameters;
    }

    public void setHasParameters(boolean hasParameters) {
        this.hasParameters = hasParameters;
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
        tipo = "intCast";
    }
    
    public void setToFloatCast(){
        tipo = "floatCast";
    }
    
    public void genC(PW pw) {
    
        if (tipo.equals("name")) {
            this.name.genC(pw);
            if (hasParameters) {
                pw.print("(");
                this.parameters.genC(pw);
                pw.print(")");
            }
        } else if (tipo.equals("listmaker")) {
            this.listmaker.genC(pw);
        } else if (tipo.equals("number")) {
            this.number.genC(pw);
        } else if (tipo.equals("string")) {
            for (PyString s : string) {
                // ver se precisa imprimir as aspas aqui
                s.genC(pw);
            }
        } else if (tipo.equals("self")) {
            /* TODO: tratar o 'self', no exemplo está assim:
                int _A_get( _class_A *this ) {
                     return this->_A_i;
                }
            */
            
            pw.print("this->_CN");
            this.name.genC(pw);
            
        }  else if (tipo.equals("func")) {
            /* 
                quando eh tipo func, o nome o objeto fica guardado em name e o nome da funcao em funcName
                a chamada a.m() deve imprimir _a->vt[0](_a); onde vt[0] indica a posicao do metodo 'm' no vetor de funcoes
                por enqto imprimi só _a->vt[m](_a); mas depois precisa ver como fica.
            */ 
            this.name.genC(pw);
            
            pw.print("->vt[");
            this.funcName.genC(pw);
            pw.print("]");
            
            pw.print("(");
            this.name.genC(pw);
            if (hasParameters) {
                pw.print(", ");
                this.parameters.genC(pw);
            }
            
            pw.print(")");
            
        }  else if (tipo.equals("partest")) {
            // deve imprimir algo do tipo: (1>1)
            pw.print("(");
            this.parTest.genC(pw);
            pw.print(")");
            
        }  else if (tipo.equals("intCast")) {
            // deve imprimir algo do tipo (int)(1 + 1)
            pw.print("(int)");
            pw.print("(");
            this.parTest.genC(pw);
            pw.print(")");
           
        }  else if (tipo.equals("floatCast")) {
            // deve imprimir algo do tipo (float)(1 + 1)
            pw.print("(float)");
            pw.print("(");
            this.parTest.genC(pw);
            pw.print(")");
           
        }
        
    }
    private Listmaker listmaker;
    private Name name;
    private Name funcName;
    private PyNumber number;
    private List<PyString> string;
    private String tipo;
    private Parameters parameters;
    private Test parTest;
    private boolean hasParameters;

}
