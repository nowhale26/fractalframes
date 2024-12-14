package backend.academy.renders;

import backend.academy.functions.AffineFunction;
import backend.academy.image.FractalImage;

public class OneThreadRenderer extends ImageRenderer {
    @Override
    public void render(FractalImage fractalImage) {
        final double aspectRatio = (double) fractalImage.xRes / (double) fractalImage.yRes;
        AffineFunction[] transforms = generateTransformsList(fractalImage.transformsNum);
        doRendering(fractalImage, aspectRatio, transforms, 0, fractalImage.fractalDots, false);
        logGammaCorrection(fractalImage);
    }

    @Override
    protected void logGammaCorrection(FractalImage fractalImage) {
        double max = getMax(fractalImage);
        gammaCorrection(fractalImage, max, 0, fractalImage.xRes);
    }
}
