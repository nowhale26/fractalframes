package backend.academy.functions.variations;

import backend.academy.Coordinate;

public abstract class VariationFunction {
    public abstract void useVariation(Coordinate coordinate);

    public abstract String getName();
}
