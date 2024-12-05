package backend.academy.functions.variations;

import backend.academy.Coordinate;

public class HandkerchiefVariation extends VariationFunction {
    public final String name = "Функция \"Носовой платок\"";

    @Override
    public void useVariation(Coordinate coordinate) {
        double x = coordinate.getX();
        double y = coordinate.getY();
        double r = Math.sqrt(x*x+y*y);
        double t = Math.atan(x/y);
        double newX = Math.sin(t+r)*r;
        double newY = Math.cos(t-r)*r;
        coordinate.setX(newX);
        coordinate.setY(newY);
    }

    @Override
    public String getName() {
        return name;
    }
}
