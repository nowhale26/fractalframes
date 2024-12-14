package backend.academy;

import backend.academy.image.FractalImage;
import java.io.IOException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) throws IOException {
        FractalImage image = UserInteraction.getUserInput(System.in, System.out);
        UserInteraction.chooseThreadingMode(image);
    }

}
