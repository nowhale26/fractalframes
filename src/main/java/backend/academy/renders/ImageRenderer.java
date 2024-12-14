package backend.academy.renders;

import backend.academy.Constants;
import backend.academy.Coordinate;
import backend.academy.functions.AffineFunction;
import backend.academy.image.FractalImage;
import backend.academy.image.Pixel;
import backend.academy.image.Rgb;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.concurrent.ThreadLocalRandom;

public abstract class ImageRenderer {
    public abstract void render(FractalImage fractalImage);

    protected abstract void logGammaCorrection(FractalImage fractalImage);

    protected AffineFunction[] generateTransformsList(int n) {
        AffineFunction[] transforms = new AffineFunction[n];
        for (int i = 0; i < n; i++) {
            transforms[i] = new AffineFunction();
            transforms[i].generateFunction();
        }
        return transforms;
    }

    protected void gammaCorrection(FractalImage fractalImage, double max, int startX, int finishX) {
        for (int x = startX; x < finishX; x++) {
            for (int y = 0; y < fractalImage.yRes; y++) {
                double normal = fractalImage.getPixels()[x][y].getNormal();
                fractalImage.getPixels()[x][y].setNormal(normal / max);
                int red = (int) (fractalImage.getPixels()[x][y].getRgb().getRed()
                    * Math.pow(fractalImage.getPixels()[x][y].getNormal(), (1.0 / Constants.GAMMA)));
                fractalImage.getPixels()[x][y].getRgb().setRed(red);
                int green = (int) (fractalImage.getPixels()[x][y].getRgb().getGreen()
                    * Math.pow(fractalImage.getPixels()[x][y].getNormal(), (1.0 / Constants.GAMMA)));
                fractalImage.getPixels()[x][y].getRgb().setGreen(green);
                int blue = (int) (fractalImage.getPixels()[x][y].getRgb().getBlue()
                    * Math.pow(fractalImage.getPixels()[x][y].getNormal(), (1.0 / Constants.GAMMA)));
                fractalImage.getPixels()[x][y].getRgb().setBlue(blue);
            }
        }
    }

    @SuppressFBWarnings({"PREDICTABLE_RANDOM", "NOS_NON_OWNED_SYNCHRONIZATION"})
    protected void doRendering(
        FractalImage fractalImage,
        double aspectRatio,
        AffineFunction[] transforms,
        int startDots,
        int finishDots,
        boolean multithread
    ) {
        final double xMin = -1 * aspectRatio;
        final double xMax = aspectRatio;
        final double yMin = -1;
        final double yMax = 1;

        for (int num = startDots; num < finishDots; num++) {
            double x = ThreadLocalRandom.current().nextDouble(xMin, xMax);
            double y = ThreadLocalRandom.current().nextDouble(yMin, yMax);
            Coordinate coord = new Coordinate(x, y);
            for (int step = Constants.START_ITERATIONS; step < fractalImage.iterations; step++) {
                AffineFunction transform = transforms[ThreadLocalRandom.current().nextInt(0,
                    fractalImage.transformsNum)];
                transform.useAffine(coord);
                fractalImage.variation.useVariation(coord);
                if (step >= 0 && coord.getX() >= xMin && coord.getX() <= xMax && coord.getY() >= yMin
                    && coord.getY() <= yMax) {
                    int xPixel = (int) ((coord.getX() - xMin) / (xMax - xMin) * (fractalImage.xRes - 1));
                    int yPixel = (int) ((coord.getY() - yMin) / (yMax - yMin) * (fractalImage.yRes - 1));

                    if (xPixel >= 0 && xPixel < fractalImage.xRes && yPixel >= 0 && yPixel < fractalImage.yRes) {
                        Pixel currentPixel = fractalImage.getPixels()[xPixel][yPixel];
                        if (multithread) {
                            synchronized (currentPixel) {
                                setColours(currentPixel, transform);
                            }
                        } else {
                            setColours(currentPixel, transform);
                        }
                    }

                }
            }
        }
    }

    protected double getMax(FractalImage fractalImage) {
        double max = 0;
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
        return max;
    }

    protected void setColours(Pixel currentPixel, AffineFunction transform) {
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
