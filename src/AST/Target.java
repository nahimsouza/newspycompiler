/*
 * Target: apenas getters, setters para o NAME e o genC.
 */
package AST;

public class Target {

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Name getFuncName() {
        return funcName;
    }

    public void setFuncName(Name funcName) {
        this.funcName = funcName;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
    
    public void setToName(){
        this.tipo = "name";
    }
    
    public void setToSelf(){
        this.tipo = "self";
    }
    
    public void setToFunc(){
        this.tipo = "func"; // chamada de funcao
    }

    public void genC(PW pw) {
        if (tipo.equals("name")){
            name.genC(pw);
        } else if (tipo.equals("func")) {
            //imprime algo do tipo  _a->vt[0](_a);
            name.genC(pw);            
            pw.print("->vt[");
            this.funcName.genC(pw);
            pw.print("]");
            
            pw.print("(");
            this.name.genC(pw);
            if (this.parameters.getVarargslist() != null) {
                pw.print(", ");
                this.parameters.genC(pw);
            }
            pw.print(")");
            
        } else {
            pw.print("this->_CN_");
            name.genC(pw);
        }
    }

    private Name name;
    private String tipo;
    private Name funcName;
    private Parameters parameters;
}
