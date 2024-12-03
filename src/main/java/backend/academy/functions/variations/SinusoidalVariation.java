package backend.academy.functions.variations;

import backend.academy.Coordinate;

public class SinusoidalVariation extends VariationFunction {
    public final String name = "Синусоидальная функция";

    @Override
    public void useVariation(Coordinate coordinate) {
        double x = coordinate.getX();
        double y = coordinate.getY();
        coordinate.setX(Math.sin(x));
        coordinate.setY(Math.sin(y));
    }
}
