/*
 * NotTest: guarda um NotTest ou um Comparision, e um isNotTest apenas para saber
 * quando eh um ou outro.
 */
package AST;

public class NotTest {

    public boolean isNotTest() {
        return isNotTest;
    }

    public void setIsNotTest(boolean isNotTest) {
        this.isNotTest = isNotTest;
    }
    
    public NotTest getNotTest() {
        return notTest;
    }

    public void setNotTest(NotTest notTest) {
        this.notTest = notTest;
    }

    public Comparison getComparison() {
        return comparison;
    }

    public void setComparison(Comparison comparison) {
        this.comparison = comparison;
    }

    public void genC(PW pw) {
        if (this.isNotTest) {
            pw.print(" !");
            this.notTest.genC(pw);
        } else {
            this.comparison.genC(pw);
        }

    }

    private boolean isNotTest;
    private NotTest notTest;
    private Comparison comparison;
}
