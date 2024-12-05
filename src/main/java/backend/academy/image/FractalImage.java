package backend.academy.image;

import backend.academy.functions.variations.VariationFunction;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j @Getter
@Setter
public class FractalImage {
    public final int fractalDots;
    public final int transformsNum;
    public final int iterations;
    public final int xRes;
    public final int yRes;
    public final VariationFunction variation;
    private Pixel[][] pixels;
    private boolean symmetry;
    private int threads;

    public FractalImage(
        int fractalDots,
        int transformsNum,
        int iterations,
        int xRes,
        int yRes,
        VariationFunction variation,
        boolean symmetry,
        int threads
    ) {
        this.fractalDots = fractalDots;
        this.transformsNum = transformsNum;
        this.iterations = iterations;
        this.xRes = xRes;
        this.yRes = yRes;
        this.variation = variation;
        this.symmetry=symmetry;
        this.threads = threads;
        pixels = new Pixel[xRes][yRes];
        for (int x = 0; x < xRes; x++) {
            for (int y = 0; y < yRes; y++) {
                pixels[x][y] = new Pixel();
            }
        }
    }

    public void createImage(boolean multithread) {
        BufferedImage image = new BufferedImage(xRes, yRes, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < xRes; x++) {
            for (int y = 0; y < yRes; y++) {
                int red = pixels[x][y].getRgb().getRed();
                int green = pixels[x][y].getRgb().getGreen();
                int blue = pixels[x][y].getRgb().getBlue();
                int rgb = (red << 16) | (green << 8) | blue;
                image.setRGB(x, y, rgb);
                if(symmetry){
                    int mirroredX = xRes-1-x;
                    image.setRGB(mirroredX,y,rgb);
                }
            }
        }
        try {
            String name;
            if(multithread){
                name = "multi_thread_output_image.png";
            } else {
                name = "one_thread_output_image.png";
            }
            File outputFile = new File(name);
            ImageIO.write(image, "png", outputFile);
            log.info("Изображение сохранено");
        } catch (IOException e) {
            log.info("Не удалось записать файл");
        }

    }
}
