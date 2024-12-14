import backend.academy.functions.AffineFunction;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TransformsTest {

    @Test
    public void testCoefficients(){
        AffineFunction transform = new AffineFunction();
        transform.generateFunction();
        double a = transform.getA();
        double d = transform.getD();
        double result = a*a+d*d;
        assertThat(result).isLessThan(1);
        double b = transform.getB();
        double e = transform.getE();
        double result2 = b*b+e*e;
        assertThat(result2).isLessThan(1);
    }
}
