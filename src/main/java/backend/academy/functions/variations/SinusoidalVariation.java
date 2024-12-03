package backend.academy.functions.variations;

import backend.academy.Coordinate;

public class SinusoidalVariation extends VariationFunction {
    public final String name = "Синусоидальная функция";

    @Override
    public void useVariation(Coordinate coordinate) {
        double x = coordinate.getX();
        double y = coordinate.getY();
        double scale = 5.0;
        coordinate.setX(scale*Math.sin(x));
        coordinate.setY(scale*Math.sin(y));
    }
}
