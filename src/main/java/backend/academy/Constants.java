package backend.academy;

import backend.academy.image.Rgb;
import java.util.List;

public class Constants {

    private Constants() {

    }

    public static final List<Rgb> COLOURS = List.of(
        new Rgb(255, 0, 0),
        new Rgb(0, 0, 255),
        new Rgb(127, 255, 212),
        new Rgb(255, 0, 255),
        new Rgb(0, 255, 0)
    );

    public static final int MIN_RES = 100;
    public static final int MAX_X = 1920;
    public static final int MAX_Y = 1080;
    public static final int MIN_FRACTALS_IT = 100;
    public static final int MAX_FRACTALS_IT = 100000;
    public static final int MIN_TRANSFORMS = 1;
    public static final int MAX_TRANSFORMS = 100;
    public static final int MIN_THREADS = 1;
    public static final int MAX_THREADS = 8;
    public static final double GAMMA = 2.2;
    public static final double SCALE = 5.0;
    public static final int TO_SECONDS = 1000;
    public static final int START_ITERATIONS = -20;
    public static final String BEFORE = " до ";
    public static final String SECONDS = " секунд ";

}
