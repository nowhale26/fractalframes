package backend.academy.renders;

import backend.academy.Constants;
import backend.academy.Coordinate;
import backend.academy.functions.TransformFunction;
import backend.academy.image.FractalImage;
import backend.academy.image.Rgb;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class MultiThreadRenderer implements ImageRenderer {
    @Override
    public void render(FractalImage fractalImage) {
        final double aspectRatio = (double) fractalImage.xRes / (double) fractalImage.yRes;
        final double xMin = -1 * aspectRatio;
        final double xMax = aspectRatio;
        final double yMin = -1;
        final double yMax = 1;

        TransformFunction[] transforms = generateTransformsList(fractalImage.transformsNum);
        int dotsPerThread = fractalImage.fractalDots / fractalImage.getThreads();
        try (ExecutorService executor = Executors.newFixedThreadPool(fractalImage.getThreads())) {
            for (int i = 0; i < fractalImage.getThreads(); i++) {
                int start = i * dotsPerThread;
                int finish = (i == fractalImage.getThreads() - 1) ? fractalImage.getFractalDots() : start +
                    dotsPerThread;
                executor.submit(() -> threadRender(start, finish, fractalImage, xMin, xMax, yMin, yMax, transforms));
            }
            executor.shutdown();
        }

    }

    @Override
    public void logGammaCorrection(FractalImage fractalImage) {
        double max = 0.0;
        for (int x = 0; x < fractalImage.xRes; x++) {
            for (int y = 0; y < fractalImage.yRes; y++) {
                if (fractalImage.getPixels()[x][y].getCounter() != 0) {
                    double log = Math.log10(fractalImage.getPixels()[x][y].getCounter());
                    fractalImage.getPixels()[x][y].setNormal(log);
                    if (fractalImage.getPixels()[x][y].getNormal() > max) {
                        max = fractalImage.getPixels()[x][y].getNormal();
                    }
                }
            }
        }
        double finalMax = max;
        int xPerThread = fractalImage.getXRes() / fractalImage.getThreads();
        try (ExecutorService executor = Executors.newFixedThreadPool(fractalImage.getThreads())) {
            for (int i = 0; i < fractalImage.getThreads(); i++) {
                int startX = i * xPerThread;
                int finishX = (i == fractalImage.getThreads() - 1) ? fractalImage.getXRes() : startX + xPerThread;

                executor.submit(
                    () -> threadLogGammaCorrection(fractalImage, startX, finishX, finalMax));
            }
            executor.shutdown();
        }

    }

    private void threadRender(
        int start,
        int finish,
        FractalImage fractalImage,
        double xMin,
        double xMax,
        double yMin,
        double yMax,
        TransformFunction[] transforms
    ) {
        for (int num = start; num < finish; num++) {
            double x = ThreadLocalRandom.current().nextDouble(xMin, xMax);
            double y = ThreadLocalRandom.current().nextDouble(yMin, yMax);
            Coordinate coord = new Coordinate(x, y);
            for (int step = -20; step < fractalImage.iterations; step++) {
                TransformFunction transform = transforms[ThreadLocalRandom.current().nextInt(0,
                    fractalImage.transformsNum)];
                transform.useTransform(coord);
                fractalImage.variation.useVariation(coord);
                if (step >= 0 && coord.getX() >= xMin && coord.getX() <= xMax && coord.getY() >= yMin &&
                    coord.getY() <= yMax) {
                    int xPixel = (int) ((coord.getX() - xMin) / (xMax - xMin) * (fractalImage.xRes - 1));
                    int yPixel = (int) ((coord.getY() - yMin) / (yMax - yMin) * (fractalImage.yRes - 1));

                    if (xPixel >= 0 && xPixel < fractalImage.xRes && yPixel >= 0 && yPixel < fractalImage.yRes) {
                        var currentPixel = fractalImage.getPixels()[xPixel][yPixel];
                        synchronized (currentPixel) {
                            if (currentPixel.getCounter() == 0) {
                                currentPixel.getRgb().setRed(transform.getRgb().getRed());
                                currentPixel.getRgb().setGreen(transform.getRgb().getGreen());
                                currentPixel.getRgb().setBlue(transform.getRgb().getBlue());
                            } else {
                                Rgb rgb = currentPixel.getRgb();
                                int red = (rgb.getRed() + transform.getRgb().getRed()) / 2;
                                int green = (rgb.getGreen() + transform.getRgb().getGreen()) / 2;
                                int blue = (rgb.getBlue() + transform.getRgb().getBlue()) / 2;
                                currentPixel.getRgb().setRed(red);
                                currentPixel.getRgb().setGreen(green);
                                currentPixel.getRgb().setBlue(blue);
                            }
                            int counter = currentPixel.getCounter();
                            currentPixel.setCounter(counter + 1);
                        }

                    }

                }
            }
        }
    }

    private void threadLogGammaCorrection(
        FractalImage fractalImage,
        int startX,
        int finishX,
        double max
    ) {
        for (int x = startX; x < finishX; x++) {
            for (int y = 0; y < fractalImage.yRes; y++) {
                double normal = fractalImage.getPixels()[x][y].getNormal();
                fractalImage.getPixels()[x][y].setNormal(normal / max);
                int red = (int) (fractalImage.getPixels()[x][y].getRgb().getRed() *
                    Math.pow(fractalImage.getPixels()[x][y].getNormal(), (1.0 / Constants.GAMMA)));
                fractalImage.getPixels()[x][y].getRgb().setRed(red);
                int green = (int) (fractalImage.getPixels()[x][y].getRgb().getGreen() *
                    Math.pow(fractalImage.getPixels()[x][y].getNormal(), (1.0 / Constants.GAMMA)));
                fractalImage.getPixels()[x][y].getRgb().setGreen(green);
                int blue = (int) (fractalImage.getPixels()[x][y].getRgb().getBlue() *
                    Math.pow(fractalImage.getPixels()[x][y].getNormal(), (1.0 / Constants.GAMMA)));
                fractalImage.getPixels()[x][y].getRgb().setBlue(blue);
            }
        }
    }
}
