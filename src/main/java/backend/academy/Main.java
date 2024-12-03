package backend.academy;

import backend.academy.functions.variations.HandkerchiefVariation;
import backend.academy.functions.variations.HorseshoeVariation;
import backend.academy.functions.variations.SinusoidalVariation;
import backend.academy.functions.variations.SphericalVariation;
import backend.academy.functions.variations.SwirlVariation;
import backend.academy.functions.variations.VariationFunction;
import backend.academy.image.FractalImage;
import backend.academy.renders.ImageRenderer;
import backend.academy.renders.OneThreadRenderer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        VariationFunction variation = new SphericalVariation();
        FractalImage image = new FractalImage(12000,10,40000,1920,1080, variation);
        ImageRenderer renderer = new OneThreadRenderer();
        renderer.render(image);
        renderer.logGammaCorrection(image);
        image.createImage();
    }
}
