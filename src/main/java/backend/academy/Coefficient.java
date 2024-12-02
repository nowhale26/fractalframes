package backend.academy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coefficient {
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;

    public Coefficient(double a, double b, double c, double d, double e, double f){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
}
