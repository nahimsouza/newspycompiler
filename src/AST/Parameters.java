/*
* Parameters: apenas uma Varargslist (lista de argumentos)
 */
package AST;

public class Parameters {

    public Varargslist getVarargslist() {
        return varargslist;
    }

    public void setVarargslist(Varargslist varargslist) {
        this.varargslist = varargslist;
    }

    public void genC(PW pw) {
        if (this.varargslist != null) {
            this.varargslist.genC(pw);
        }
    }

    private Varargslist varargslist;
}
