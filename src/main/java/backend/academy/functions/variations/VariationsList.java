package backend.academy.functions.variations;

public class VariationsList {

    private VariationsList(){

    }

    public static final VariationFunction[] VARIATION_FUNCTIONS = new VariationFunction[]{
        new SinusoidalVariation(),
        new SphericalVariation(),
        new SwirlVariation(),
        new HorseshoeVariation(),
        new HandkerchiefVariation(),
        new HeartVariation()
    };
}
