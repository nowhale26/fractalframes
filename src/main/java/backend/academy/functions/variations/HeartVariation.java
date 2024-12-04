package backend.academy.functions.variations;

import backend.academy.Coordinate;

public class HeartVariation extends VariationFunction{
    public final String name = "Функция \"Сердце\"";
    @Override
    public void useVariation(Coordinate coordinate) {
        double x = coordinate.getX();
        double y = coordinate.getY();
        double r = Math.sqrt(x*x+y*y);
        double t = Math.atan(x/y);
        double newX = r*Math.sin(t*r);
        double newY = -1*r*Math.cos(t*r);
        coordinate.setX(newX);
        coordinate.setY(newY);
    }
}
