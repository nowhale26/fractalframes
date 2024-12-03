package backend.academy.image;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pixel {
    private Rgb rgb;
    private int counter;
    private double normal;

    public Pixel(){
        rgb = new Rgb();
        rgb.setRed(0);
        rgb.setGreen(0);
        rgb.setBlue(0);
        counter=0;
    }

}
