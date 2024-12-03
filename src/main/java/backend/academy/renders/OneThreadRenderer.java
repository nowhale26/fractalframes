package backend.academy.renders;

import backend.academy.Coordinate;
import backend.academy.functions.TransformFunction;
import backend.academy.image.FractalImage;
import backend.academy.image.Rgb;
import java.util.concurrent.ThreadLocalRandom;

public class OneThreadRenderer implements ImageRenderer {
    @Override
    public void render(FractalImage fractalImage) {
        final double aspectRatio = (double) fractalImage.xRes / (double) fractalImage.yRes;
        final double xMin = -1 * aspectRatio;
        final double xMax = aspectRatio;
        final double yMin = -1;
        final double yMax = 1;

        TransformFunction[] transforms = generateTransformsList(fractalImage.transformsNum);

        for (int num = 0; num < fractalImage.fractalDots; num++) {
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
                        if (fractalImage.getPixels()[xPixel][yPixel].getCounter() == 0) {
                            fractalImage.getPixels()[xPixel][yPixel].getRgb().setRed(transform.getRgb().getRed());
                            fractalImage.getPixels()[xPixel][yPixel].getRgb().setGreen(transform.getRgb().getGreen());
                            fractalImage.getPixels()[xPixel][yPixel].getRgb().setBlue(transform.getRgb().getBlue());
                        } else {
                            Rgb rgb = fractalImage.getPixels()[xPixel][yPixel].getRgb();
                            int red = (rgb.getRed() + transform.getRgb().getRed()) / 2;
                            int green = (rgb.getGreen() + transform.getRgb().getGreen()) / 2;
                            int blue = (rgb.getBlue() + transform.getRgb().getBlue()) / 2;
                            fractalImage.getPixels()[xPixel][yPixel].getRgb().setRed(red);
                            fractalImage.getPixels()[xPixel][yPixel].getRgb().setGreen(green);
                            fractalImage.getPixels()[xPixel][yPixel].getRgb().setBlue(blue);
                        }
                        int counter = fractalImage.getPixels()[xPixel][yPixel].getCounter();
                        fractalImage.getPixels()[xPixel][yPixel].setCounter(counter + 1);
                    }

                }
            }
        }

    }

    @Override
    public void logGammaCorrection(FractalImage fractalImage) {
        double max = 0.0;
        double gamma = 2.2;
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

        for (int x = 0; x < fractalImage.xRes; x++) {
            for (int y = 0; y < fractalImage.yRes; y++) {
                double normal = fractalImage.getPixels()[x][y].getNormal();
                fractalImage.getPixels()[x][y].setNormal(normal/max);
                int red = (int) ( fractalImage.getPixels()[x][y].getRgb().getRed()*Math.pow(fractalImage.getPixels()[x][y].getNormal(),(1.0/gamma)));
                fractalImage.getPixels()[x][y].getRgb().setRed(red);
                int green = (int) ( fractalImage.getPixels()[x][y].getRgb().getGreen()*Math.pow(fractalImage.getPixels()[x][y].getNormal(),(1.0/gamma)));
                fractalImage.getPixels()[x][y].getRgb().setGreen(green);
                int blue = (int) ( fractalImage.getPixels()[x][y].getRgb().getBlue()*Math.pow(fractalImage.getPixels()[x][y].getNormal(),(1.0/gamma)));
                fractalImage.getPixels()[x][y].getRgb().setBlue(blue);
            }
        }
    }

}
