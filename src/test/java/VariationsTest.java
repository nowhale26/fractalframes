import backend.academy.Coordinate;
import backend.academy.functions.variations.HandkerchiefVariation;
import backend.academy.functions.variations.HeartVariation;
import backend.academy.functions.variations.HorseshoeVariation;
import backend.academy.functions.variations.SinusoidalVariation;
import backend.academy.functions.variations.SphericalVariation;
import backend.academy.functions.variations.SwirlVariation;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class VariationsTest {

    @Test
    public void testSwirl() {
        SwirlVariation variation = new SwirlVariation();
        Coordinate coord = new Coordinate(1, 1);
        double x = 1.0;
        double y = 1.0;
        double r = Math.sqrt(x * x + y * y);
        double newX = x * Math.sin(r * r) - y * Math.cos(r * r);
        double newY = x * Math.cos(r * r) + y * Math.sin(r * r);
        variation.useVariation(coord);
        assertThat(newX).isEqualTo(coord.getX());
        assertThat(newY).isEqualTo(coord.getY());
    }

    @Test
    public void testSpherical() {
        SphericalVariation variation = new SphericalVariation();
        Coordinate coord = new Coordinate(1, 1);
        double x = 1.0;
        double y = 1.0;
        double r2 = x * x + y * y;
        double newX = x / r2;
        double newY = y / r2;
        variation.useVariation(coord);
        assertThat(newX).isEqualTo(coord.getX());
        assertThat(newY).isEqualTo(coord.getY());
    }

    @Test
    public void testSinusoidal() {
        SinusoidalVariation variation = new SinusoidalVariation();
        Coordinate coord = new Coordinate(1, 1);
        double x = 1.0;
        double y = 1.0;
        double scale = 5.0;
        double newX = scale * Math.sin(x);
        double newY = scale * Math.sin(y);
        variation.useVariation(coord);
        assertThat(newX).isEqualTo(coord.getX());
        assertThat(newY).isEqualTo(coord.getY());
    }
    @Test
    public void testHorseshoe(){
        HorseshoeVariation variation = new HorseshoeVariation();
        Coordinate coord = new Coordinate(1, 1);
        double x = 1.0;
        double y = 1.0;
        double r = Math.sqrt(x*x+y*y);
        double newX = ((x-y)*(x+y))/r;
        double newY = 2*x*y;
        variation.useVariation(coord);
        assertThat(newX).isEqualTo(coord.getX());
        assertThat(newY).isEqualTo(coord.getY());
    }
    @Test
    public void testHeart(){
        HeartVariation variation = new HeartVariation();
        Coordinate coord = new Coordinate(1, 1);
        double x = 1.0;
        double y = 1.0;
        double r = Math.sqrt(x*x+y*y);
        double t = Math.atan(x/y);
        double newX = r*Math.sin(t*r);
        double newY = -1*r*Math.cos(t*r);
        variation.useVariation(coord);
        assertThat(newX).isEqualTo(coord.getX());
        assertThat(newY).isEqualTo(coord.getY());
    }
    @Test
    public void testHandkerchief(){
        HandkerchiefVariation variation = new HandkerchiefVariation();
        Coordinate coord = new Coordinate(1, 1);
        double x = 1.0;
        double y = 1.0;
        double r = Math.sqrt(x*x+y*y);
        double t = Math.atan(x/y);
        double newX = Math.sin(t+r)*r;
        double newY = Math.cos(t-r)*r;
        variation.useVariation(coord);
        assertThat(newX).isEqualTo(coord.getX());
        assertThat(newY).isEqualTo(coord.getY());
    }


}
