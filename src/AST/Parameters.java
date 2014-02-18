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

    public void genC(int tabs) {
//        String x = "";
//        int tab = tabs;
//        while (tabs != 0) {
//            x = x.concat("  ");
//            tabs--;
//        }
//        System.out.println(x + this.getClass().getName());
//        varargslist.genC(tab + 1);
    }

    private Varargslist varargslist;
}
