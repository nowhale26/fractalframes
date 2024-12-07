package backend.academy.image;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rgb {
    private int red;
    private int green;
    private int blue;

    public Rgb() {

    }

    public Rgb(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
}
