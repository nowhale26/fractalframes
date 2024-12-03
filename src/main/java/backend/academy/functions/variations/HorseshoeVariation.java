package backend.academy.functions.variations;

import backend.academy.Coordinate;

public class HorseshoeVariation extends VariationFunction{
    public final String name ="Функция \"Подкова\"";

    @Override
    public void useVariation(Coordinate coordinate) {
        double x = coordinate.getX();
        double y = coordinate.getY();
        double r = Math.sqrt(x*x+y*y);
        double newX = ((x-y)*(x+y))/r;
        double newY = 2*x*y;
        coordinate.setX(newX);
        coordinate.setY(newY);
    }
}
