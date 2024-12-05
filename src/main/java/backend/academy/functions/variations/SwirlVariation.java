package backend.academy.functions.variations;

import backend.academy.Coordinate;

public class SwirlVariation extends VariationFunction{
    public final String name = "Вихревая функция";

    @Override
    public void useVariation(Coordinate coordinate) {
        double x = coordinate.getX();
        double y = coordinate.getY();
        double r = Math.sqrt(x*x+y*y);
        double newX = x*Math.sin(r*r)-y*Math.cos(r*r);
        double newY = x*Math.cos(r*r)+y*Math.sin(r*r);
        coordinate.setX(newX);
        coordinate.setY(newY);
    }

    @Override
    public String getName() {
        return name;
    }
}
