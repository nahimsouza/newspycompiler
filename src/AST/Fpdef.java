/*
 * Fpdef: guarda uma Fplist ou um NAME. Possui uma flag p/ saber quando eh Name.
 */
package AST;

public class Fpdef {

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

    public Fplist getFplist() {
        return fplist;
    }

    public void setFplist(Fplist fplist) {
        this.fplist = fplist;
    }
    
    public void setToName(){
        this.tipo = "name";
    }
    
    public void setToFplist(){
        this.tipo = "fplist";
    }
    
    public void setToSelf(){
        this.tipo = "self";
    }
        
    public void setToNumber() {
        this.tipo = "number";
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    
    
    public void genC(PW pw) {
        if (tipo.equals("name")){
            this.name.genC(pw);
        } else if (tipo.equals("self")) {
            pw.print("_class_"+ classname +" *this");
        } else if (tipo.equals("fplist")) {
            fplist.genC(pw);
        } else if (tipo.equals("number")) {
            this.number.genC(pw);
        }
    }

    private Name name;
    private Fplist fplist;
    private String tipo;
    private PyNumber number;
    private String classname;

    
}
