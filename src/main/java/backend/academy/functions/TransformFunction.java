package backend.academy.functions;

import backend.academy.Constants;
import backend.academy.Coordinate;
import backend.academy.image.Rgb;
import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
public class TransformFunction {
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;
    private Rgb rgb;

    public TransformFunction() {

    }

    public TransformFunction(double a, double b, double c, double d, double e, double f, Rgb rgb) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.rgb = rgb;
    }

    public void generateFunction() {
        while(true){
            a = ThreadLocalRandom.current().nextDouble(-1, 1);
            double dBound = 1d - Math.pow(a, 2);
            d = ThreadLocalRandom.current().nextDouble(-1d + dBound, 1d - dBound);
            b = ThreadLocalRandom.current().nextDouble(-1, 1);
            double eBound = 1d - Math.pow(b, 2);
            e = ThreadLocalRandom.current().nextDouble(-1d + eBound, 1d - eBound);
            c = ThreadLocalRandom.current().nextDouble(-1, 1);
            f = ThreadLocalRandom.current().nextDouble(-1, 1);
            if(a*a+b*b+d*d+e*e<1+(a*e-b*d)*(a*e-b*d)){
                break;
            }
        }
        rgb = Constants.COLOURS[ThreadLocalRandom.current().nextInt(0, Constants.COLOURS.length)];

    }

    public void useTransform(Coordinate coordinate) {
        double x = coordinate.getX();
        double y = coordinate.getY();
        coordinate.setX(a * x + b * y + c);
        coordinate.setY(d * x + e * y + f);
    }

}
