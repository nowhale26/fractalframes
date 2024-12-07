package backend.academy.functions.variations;

import java.util.List;

public class VariationsList {

    private VariationsList() {

    }

    public static final List<VariationFunction> VARIATION_FUNCTIONS = List.of(
        new SinusoidalVariation(),
        new SphericalVariation(),
        new SwirlVariation(),
        new HorseshoeVariation(),
        new HandkerchiefVariation(),
        new HeartVariation()
    );
}
