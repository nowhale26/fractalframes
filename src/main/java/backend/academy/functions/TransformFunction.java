package backend.academy.functions;

import backend.academy.Constants;
import backend.academy.Coordinate;
import backend.academy.image.Rgb;

import java.util.concurrent.ThreadLocalRandom;

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

    public TransformFunction(double a, double b, double c, double d, double e, double f,Rgb rgb) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.rgb.setRed(rgb.getRed());
        this.rgb.setGreen(rgb.getGreen());
        this.rgb.setBlue(rgb.getBlue());
    }

    public void generateFunction() {
        a = ThreadLocalRandom.current().nextDouble(-1, 1);
        double dBound = 1d - Math.pow(a, 2);
        d = ThreadLocalRandom.current().nextDouble(-1d + dBound, 1d - dBound);
        b = ThreadLocalRandom.current().nextDouble(-1, 1);
        double eBound = 1d - Math.pow(b, 2);
        e = ThreadLocalRandom.current().nextDouble(-1d + eBound, 1d - eBound);
        c = ThreadLocalRandom.current().nextDouble(-5, 5);
        f = ThreadLocalRandom.current().nextDouble(-5, 5);
        rgb = Constants.COLOURS[ThreadLocalRandom.current().nextInt(0,Constants.COLOURS.length)];
    }

    public void useTransform(Coordinate coordinate){
        double x = coordinate.getX();
        double y = coordinate.getY();
        coordinate.setX(a*x+b*y+c);
        coordinate.setY(d*x+e*y+f);
    }

}
