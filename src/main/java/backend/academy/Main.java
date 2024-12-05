package backend.academy;

import backend.academy.image.FractalImage;
import backend.academy.renders.ImageRenderer;
import backend.academy.renders.MultiThreadRenderer;
import backend.academy.renders.OneThreadRenderer;
import lombok.experimental.UtilityClass;
import java.io.IOException;

@UtilityClass
public class Main {
    public static void main(String[] args) throws IOException {
        FractalImage image = UserInteraction.getUserInput(System.in,System.out);
        ImageRenderer renderer;
        ImageRenderer renderer2;
        if(image.getThreads()==1){
            renderer = new OneThreadRenderer();
            renderImage(renderer, image, false);
        } else if(UserInteraction.onlyMultiThread(System.in, System.out)){
            renderer = new MultiThreadRenderer();
            renderImage(renderer, image, true);
        } else {

        }

    }

    private void renderImage(ImageRenderer renderer, FractalImage image, boolean multithread){
        renderer.render(image);
        renderer.logGammaCorrection(image);
        image.createImage(multithread);
    }
}
