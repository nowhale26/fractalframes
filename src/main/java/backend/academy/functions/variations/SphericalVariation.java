package backend.academy.functions.variations;

import backend.academy.Coordinate;

public class SphericalVariation extends VariationFunction {
    public static final String NAME = "Сферическая функция";

    @Override
    public void useVariation(Coordinate coordinate) {
        double x = coordinate.getX();
        double y = coordinate.getY();
        double r2 = x * x + y * y;
        coordinate.setX(x / r2);
        coordinate.setY(y / r2);

    }

    @Override
    public String getName() {
        return NAME;
    }
}
