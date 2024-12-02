package backend.academy;

public class TransformFunction {
    private Coefficient coefficient;
    private int numOfFuncs;

    public TransformFunction() {

    }

    public TransformFunction(Coefficient coefficient){
        this.coefficient.setA(coefficient.getA());
        this.coefficient.setB(coefficient.getB());
        this.coefficient.setC(coefficient.getC());
        this.coefficient.setD(coefficient.getD());
        this.coefficient.setE(coefficient.getE());
        this.coefficient.setF(coefficient.getF());
    }

    private TransformFunction generateFunction() {
        return null;
    }

}
