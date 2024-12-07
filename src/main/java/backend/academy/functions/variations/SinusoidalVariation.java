package backend.academy.functions.variations;

import backend.academy.Constants;
import backend.academy.Coordinate;

public class SinusoidalVariation extends VariationFunction {
    public static final String NAME = "Синусоидальная функция";

    @Override
    public void useVariation(Coordinate coordinate) {
        double x = coordinate.getX();
        double y = coordinate.getY();
        coordinate.setX(Constants.SCALE * Math.sin(x));
        coordinate.setY(Constants.SCALE * Math.sin(y));
    }

    @Override
    public String getName() {
        return NAME;
    }
}
