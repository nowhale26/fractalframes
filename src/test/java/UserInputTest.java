import backend.academy.Constants;
import backend.academy.UserInteraction;
import backend.academy.functions.variations.VariationFunction;
import backend.academy.functions.variations.VariationsList;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import static org.assertj.core.api.Assertions.assertThat;

public class UserInputTest {

    @Test
    public void testHeight() throws IOException {
        String simulatedInput = "50\n" +
            "1100\n" +
            "ф\n" +
            Constants.MAX_Y + "\n";
        InputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int YRes = UserInteraction.getHeight(input, output);
        assertThat(YRes).isEqualTo(Constants.MAX_Y);
    }

    @Test
    public void testWidth() throws IOException {
        String simulatedInput = "0\n" +
            "2000\n" +
            "f\n" +
            Constants.MAX_X + "\n";
        InputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int xRes = UserInteraction.getWidth(input, output);
        assertThat(xRes).isEqualTo(Constants.MAX_X);
    }

    @Test
    public void testFractalDots() throws IOException {
        String simulatedInput = "50\n" +
            "100001\n" +
            "g\n" +
            Constants.MIN_FRACTALS_IT + "\n";
        InputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int fractalDots = UserInteraction.getFractalDots(input, output);
        assertThat(fractalDots).isEqualTo(Constants.MIN_FRACTALS_IT);
    }

    @Test
    public void testIterations() throws IOException {
        String simulatedInput = "0\n" +
            "200000\n" +
            "o\n" +
            Constants.MAX_FRACTALS_IT + "\n";
        InputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int iterations = UserInteraction.getIterations(input, output);
        assertThat(iterations).isEqualTo(Constants.MAX_FRACTALS_IT);
    }

    @Test
    public void testTransforms() throws IOException {
        String simulatedInput = "0\n" +
            "101\n" +
            "q\n" +
            Constants.MAX_TRANSFORMS + "\n";
        InputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int transformsNum = UserInteraction.getTransformsNum(input, output);
        assertThat(transformsNum).isEqualTo(Constants.MAX_TRANSFORMS);
    }
    @Test
    public void testSymmetry() throws IOException {
        String simulatedInput =  "2\n" +
            "f\n" +
            "0\n";
        InputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        boolean symmetry = UserInteraction.getSymmetry(input, output);
        assertThat(symmetry).isEqualTo(false);
    }
    @Test
    public void testVariation() throws IOException {
        String simulatedInput =  "150\n" +
            "a\n" +
            "0\n";
        InputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        VariationFunction variation = UserInteraction.getVariation(input, output);
        assertThat(variation).isEqualTo(VariationsList.VARIATION_FUNCTIONS[0]);
    }
    @Test
    public void testThreads() throws IOException {
        String simulatedInput =  "0\n" +
            "9\n" +
            "h\n" +
            Constants.MIN_THREADS + "\n";
        InputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int threads = UserInteraction.getNumOfThreads(input, output);
        assertThat(threads).isEqualTo(Constants.MIN_THREADS);
    }

}
