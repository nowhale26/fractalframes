package backend.academy;

import backend.academy.functions.variations.VariationFunction;
import backend.academy.functions.variations.VariationsList;
import org.apache.commons.lang3.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class UserInteraction {
    private int getHeight(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        int yRes = -1;
        while (yRes < Constants.MIN_RES || yRes > Constants.MAX_Y) {
            writer.println("Введите высоту изображения от " + Constants.MIN_RES + " до " + Constants.MAX_Y);
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

    private int getWidth(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        int xRes = -1;
        while (xRes < Constants.MIN_RES || xRes > Constants.MAX_X) {
            writer.println("Введите ширину изображения от " + Constants.MIN_RES + " до " + Constants.MAX_X);
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

    private int getFractalDots(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        int fractalDots = -1;
        while (fractalDots < Constants.MIN_FRACTALS_IT || fractalDots > Constants.MAX_FRACTALS_IT) {
            writer.println("Введите количество фрактальных точек от " + Constants.MIN_FRACTALS_IT + " до " +
                Constants.MAX_FRACTALS_IT);
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

    private int getIterations(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        int iterations = -1;
        while (iterations < Constants.MIN_FRACTALS_IT || iterations > Constants.MAX_FRACTALS_IT) {
            writer.println(
                "Введите количество итераций от " + Constants.MIN_FRACTALS_IT + " до " + Constants.MAX_FRACTALS_IT);
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

    private int getTransformsNum(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        int transformsNum = -1;
        while (transformsNum < Constants.MIN_TRANSFORMS || transformsNum > Constants.MAX_TRANSFORMS) {
            writer.println("Введите количество афинных преобразований от " + Constants.MIN_TRANSFORMS + " до " +
                Constants.MAX_TRANSFORMS);
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

    private boolean getSymmetry(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        int sym = -1;
        while (sym != 0 && sym != 1) {
            writer.println(
                "Введите 1, если хотите использовать параметр симметрии\n Введите 0, если не хотите использовать параметр симметрии");
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

    private VariationFunction getVariation(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        writer.println("Выберите одну нелинейную функцию из списка ниже, нужно ввести ее номер\n");
        VariationFunction[] variations = VariationsList.VARIATION_FUNCTIONS;
        for (int i = 0; i < variations.length; i++) {
            writer.println(variations[i].name + "Номер: " + i + "\n");
        }
        int variationIndex = -1;
        while (variationIndex < 0 || variationIndex > variations.length - 1) {
            String variationInput = reader.readLine();
            if (variationInput == null) {
                writer.println("Ошибка: не удалось считать номер нелинейной функции");
                continue;
            }

            if (!StringUtils.isNumeric(variationInput)) {
                writer.println("Ошибка: Номер нелинейной функции должен быть числом.");
                continue;
            }
            variationIndex=Integer.parseInt(variationInput);
        }
        return variations[variationIndex];
    }
}
