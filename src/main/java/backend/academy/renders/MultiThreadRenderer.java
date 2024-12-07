package backend.academy.renders;

import backend.academy.functions.TransformFunction;
import backend.academy.image.FractalImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadRenderer extends ImageRenderer {
    @Override
    public void render(FractalImage fractalImage) {
        final double aspectRatio = (double) fractalImage.xRes / (double) fractalImage.yRes;
        TransformFunction[] transforms = generateTransformsList(fractalImage.transformsNum);
        int dotsPerThread = fractalImage.fractalDots / fractalImage.getThreads();
        try (ExecutorService executor = Executors.newFixedThreadPool(fractalImage.getThreads())) {
            for (int i = 0; i < fractalImage.getThreads(); i++) {
                int start = i * dotsPerThread;
                int finish = (i == fractalImage.getThreads() - 1) ? fractalImage.getFractalDots() : start
                    + dotsPerThread;
                executor.submit(() -> doRendering(fractalImage, aspectRatio, transforms, start, finish, true));
            }
            executor.shutdown();
        }
        logGammaCorrection(fractalImage);
    }

    @Override
    protected void logGammaCorrection(FractalImage fractalImage) {
        double max = getMax(fractalImage);
        int xPerThread = fractalImage.getXRes() / fractalImage.getThreads();
        try (ExecutorService executor = Executors.newFixedThreadPool(fractalImage.getThreads())) {
            for (int i = 0; i < fractalImage.getThreads(); i++) {
                int startX = i * xPerThread;
                int finishX = (i == fractalImage.getThreads() - 1) ? fractalImage.getXRes() : startX + xPerThread;

                executor.submit(
                    () -> gammaCorrection(fractalImage, max, startX, finishX));
            }
            executor.shutdown();
        }

    }

}
