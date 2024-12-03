package backend.academy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pixel {
    private int x;
    private int y;
    private Rgb rgb;
    private int counter;

    public Pixel(int x, int y){
        this.x=x;
        this.y=y;
        counter=0;
    }
}
