package backend.academy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinate {
    private double x;
    private double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
