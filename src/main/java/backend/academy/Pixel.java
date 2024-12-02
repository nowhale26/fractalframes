package backend.academy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pixel {
    private final Coordinate coordinate;
    private Rgb rgb;
    private int counter;

    public Pixel(Coordinate coord){
        this.coordinate = coord;
        counter=0;
    }
}
