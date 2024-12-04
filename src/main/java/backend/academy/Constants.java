package backend.academy;

import backend.academy.image.Rgb;

public class Constants {

    private Constants() {

    }

    public static final Rgb[] COLOURS = new Rgb[]
        {
            new Rgb(255, 0, 0),
            new Rgb(0, 0, 255),
            new Rgb(127, 255, 212),
            new Rgb(255,0,255),
            new Rgb(0,255,0)
        };

    public static final int MIN_RES = 100;
    public static final int MAX_X = 1920;
    public static final int MAX_Y = 1080;
    public static final int MIN_FRACTALS_IT = 100;
    public static final int MAX_FRACTALS_IT = 100000;
    public static final int MIN_TRANSFORMS = 1;
    public static final int MAX_TRANSFORMS = 100;

}
