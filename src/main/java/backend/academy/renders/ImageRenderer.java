package backend.academy.renders;

import backend.academy.Coordinate;
import backend.academy.functions.TransformFunction;
import backend.academy.image.FractalImage;

public interface ImageRenderer {
    public void render(FractalImage fractalImage);

    public void logGammaCorrection(FractalImage fractalImage);

    default TransformFunction[] generateTransformsList(int n){
        TransformFunction[] transforms = new TransformFunction[n];
        for(int i=0;i<n;i++){
            transforms[i] = new TransformFunction();
            transforms[i].generateFunction();
        }
        return transforms;
    }

}
