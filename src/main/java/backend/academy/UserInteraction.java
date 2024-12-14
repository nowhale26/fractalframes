package backend.academy;

import backend.academy.functions.variations.VariationFunction;
import backend.academy.functions.variations.VariationsList;
import backend.academy.image.FractalImage;
import backend.academy.renders.ImageRenderer;
import backend.academy.renders.MultiThreadRenderer;
import backend.academy.renders.OneThreadRenderer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class UserInteraction {

    private UserInteraction() {

    }

    public static int getHeight(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        int yRes = -1;
        while (yRes < Constants.MIN_RES || yRes > Constants.MAX_Y) {
            writer.println("Введите высоту изображения от " + Constants.MIN_RES + Constants.BEFORE + Constants.MAX_Y);
            String heightInput = reader.readLine();
            if (heightInput == null) {
                writer.println("Ошибка: не удалось считать высоту");
                continue;
            }

            if (!StringUtils.isNumeric(heightInput)) {
                writer.println("Ошибка: Высота должна быть числом.");
                continue;
            }

            yRes = Integer.parseInt(heightInput);
        }

        return yRes;

    }

    public static int getWidth(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        int xRes = -1;
        while (xRes < Constants.MIN_RES || xRes > Constants.MAX_X) {
            writer.println("Введите ширину изображения от " + Constants.MIN_RES + Constants.BEFORE + Constants.MAX_X);
            String widthInput = reader.readLine();
            if (widthInput == null) {
                writer.println("Ошибка: не удалось считать ширину");
                continue;
            }

            if (!StringUtils.isNumeric(widthInput)) {
                writer.println("Ошибка: Ширина должна быть числом.");
                continue;
            }

            xRes = Integer.parseInt(widthInput);
        }
        return xRes;
    }

    public static int getFractalDots(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        int fractalDots = -1;
        while (fractalDots < Constants.MIN_FRACTALS_IT || fractalDots > Constants.MAX_FRACTALS_IT) {
            writer.println("Введите количество фрактальных точек от " + Constants.MIN_FRACTALS_IT + Constants.BEFORE
                + Constants.MAX_FRACTALS_IT);
            String fractalDotsInput = reader.readLine();
            if (fractalDotsInput == null) {
                writer.println("Ошибка: не удалось считать количество фрактальных точек");
                continue;
            }

            if (!StringUtils.isNumeric(fractalDotsInput)) {
                writer.println("Ошибка: Количество фрактальных точек должно быть числом.");
                continue;
            }

            fractalDots = Integer.parseInt(fractalDotsInput);
        }
        return fractalDots;
    }

    public static int getIterations(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        int iterations = -1;
        while (iterations < Constants.MIN_FRACTALS_IT || iterations > Constants.MAX_FRACTALS_IT) {
            writer.println(
                "Введите количество итераций от " + Constants.MIN_FRACTALS_IT + Constants.BEFORE
                    + Constants.MAX_FRACTALS_IT);
            String iterationsInput = reader.readLine();
            if (iterationsInput == null) {
                writer.println("Ошибка: не удалось считать количество итераций");
                continue;
            }

            if (!StringUtils.isNumeric(iterationsInput)) {
                writer.println("Ошибка: Количество итераций должно быть числом.");
                continue;
            }

            iterations = Integer.parseInt(iterationsInput);
        }
        return iterations;
    }

    public static int getTransformsNum(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        int transformsNum = -1;
        while (transformsNum < Constants.MIN_TRANSFORMS || transformsNum > Constants.MAX_TRANSFORMS) {
            writer.println("Введите количество афинных преобразований от " + Constants.MIN_TRANSFORMS + Constants.BEFORE
                + Constants.MAX_TRANSFORMS);
            String transformsInput = reader.readLine();
            if (transformsInput == null) {
                writer.println("Ошибка: не удалось считать количество афинных преобразований");
                continue;
            }

            if (!StringUtils.isNumeric(transformsInput)) {
                writer.println("Ошибка: Количество афинных преобразований должно быть числом.");
                continue;
            }

            transformsNum = Integer.parseInt(transformsInput);
        }
        return transformsNum;
    }

    public static boolean getSymmetry(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        int sym = -1;
        while (sym != 0 && sym != 1) {
            writer.println(
                "Введите 1, если хотите использовать параметр симметрии");
            writer.println("Введите 0, если не хотите использовать параметр симметрии");
            String symInput = reader.readLine();
            if (symInput == null) {
                writer.println("Ошибка: не удалось считать параметр симметрии");
                continue;
            }

            if (!StringUtils.isNumeric(symInput)) {
                writer.println("Ошибка: Параметр симметрии должен быть числом.");
                continue;
            }

            sym = Integer.parseInt(symInput);
        }
        return sym == 1;
    }

    public static VariationFunction getVariation(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        writer.println("Выберите одну нелинейную функцию из списка ниже, нужно ввести ее номер\n");
        List<VariationFunction> variations = VariationsList.VARIATION_FUNCTIONS;
        for (int i = 0; i < variations.size(); i++) {
            writer.println(variations.get(i).getName() + " Номер: " + i);
        }
        int variationIndex = -1;
        while (variationIndex < 0 || variationIndex > variations.size() - 1) {
            String variationInput = reader.readLine();
            if (variationInput == null) {
                writer.println("Ошибка: не удалось считать номер нелинейной функции");
                continue;
            }

            if (!StringUtils.isNumeric(variationInput)) {
                writer.println("Ошибка: Номер нелинейной функции должен быть числом.");
                continue;
            }
            variationIndex = Integer.parseInt(variationInput);
        }
        return variations.get(variationIndex);
    }

    public static int getNumOfThreads(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        int threadsNum = -1;
        while (threadsNum < Constants.MIN_THREADS || threadsNum > Constants.MAX_THREADS) {
            writer.println("Введите количество потоков от " + Constants.MIN_THREADS + Constants.BEFORE
                + Constants.MAX_THREADS);
            writer.println("(1 поток означает работу в однопоточном режиме)");
            String transformsInput = reader.readLine();
            if (transformsInput == null) {
                writer.println("Ошибка: не удалось считать количество потоков");
                continue;
            }

            if (!StringUtils.isNumeric(transformsInput)) {
                writer.println("Ошибка: Количество потоков должно быть числом.");
                continue;
            }

            threadsNum = Integer.parseInt(transformsInput);
        }
        return threadsNum;

    }

    public static FractalImage getUserInput(InputStream input, OutputStream output) throws IOException {
        int height = getHeight(input, output);
        int width = getWidth(input, output);
        int fractalDots = getFractalDots(input, output);
        int iterations = getIterations(input, output);
        int transformsNum = getTransformsNum(input, output);
        boolean symmetry = getSymmetry(input, output);
        VariationFunction variation = getVariation(input, output);
        int threads = getNumOfThreads(input, output);
        return new FractalImage(fractalDots, transformsNum, iterations, width, height, variation, symmetry, threads);
    }

    public static boolean onlyMultiThread(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        int bool = -1;
        while (bool != 0 && bool != 1) {
            writer.println(
                "Введите 1, если хотите использовать программу только в многопоточном режиме");
            writer.println("Введите 0, если хотите использовать оба режима");
            String boolInput = reader.readLine();
            if (boolInput == null) {
                writer.println("Ошибка: не удалось считать режим");
                continue;
            }

            if (!StringUtils.isNumeric(boolInput)) {
                writer.println("Ошибка: Режим симметрии должен быть числом.");
                continue;
            }

            bool = Integer.parseInt(boolInput);
        }
        return bool == 1;
    }

    public static void printStatistics(double oneThreadTime, double multiThreadTime, OutputStream output) {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        writer.println("Время исполнения программы в однопоточном режиме: " + oneThreadTime + Constants.SECONDS);
        writer.println("Время исполнения программы в многопоточном режиме: " + multiThreadTime + Constants.SECONDS);
        double comparison = oneThreadTime / multiThreadTime;
        writer.println("Программа в многопоточном режиме выполнилась в " + comparison + " раз быстрее");
    }

    public static void printStartRender(OutputStream output) {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        writer.println("Рендер картинки начался...");
    }

    public static void chooseThreadingMode(FractalImage image)
        throws IOException {
        ImageRenderer renderer;
        ImageRenderer renderer2;
        if (image.getThreads() == 1) {
            renderer = new OneThreadRenderer();
            printStartRender(System.out);
            renderImage(renderer, image, false);
        } else if (onlyMultiThread(System.in, System.out)) {
            renderer = new MultiThreadRenderer();
            printStartRender(System.out);
            renderImage(renderer, image, true);
        } else {
            renderer = new OneThreadRenderer();
            printStartRender(System.out);
            long oneThreadStartTime = System.currentTimeMillis();
            renderImage(renderer, image, false);
            double oneThreadTime = (double) (System.currentTimeMillis() - oneThreadStartTime) / Constants.TO_SECONDS;
            renderer2 = new MultiThreadRenderer();
            printStartRender(System.out);
            long multiThreadStartTime = System.currentTimeMillis();
            renderImage(renderer2, image, true);
            double multiThreadTime =
                (double) (System.currentTimeMillis() - multiThreadStartTime) / Constants.TO_SECONDS;
            printStatistics(oneThreadTime, multiThreadTime, System.out);
        }
    }

    private static void renderImage(ImageRenderer renderer, FractalImage image, boolean multithread) {
        renderer.render(image);
        image.createImage(multithread);
    }
}
