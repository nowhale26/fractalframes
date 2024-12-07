package backend.academy;

import backend.academy.image.FractalImage;
import backend.academy.renders.ImageRenderer;
import backend.academy.renders.MultiThreadRenderer;
import backend.academy.renders.OneThreadRenderer;
import java.io.IOException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) throws IOException {
        FractalImage image = UserInteraction.getUserInput(System.in, System.out);
        ImageRenderer renderer;
        ImageRenderer renderer2;
        if (image.getThreads() == 1) {
            renderer = new OneThreadRenderer();
            UserInteraction.printStartRender(System.out);
            renderImage(renderer, image, false);
        } else if (UserInteraction.onlyMultiThread(System.in, System.out)) {
            renderer = new MultiThreadRenderer();
            UserInteraction.printStartRender(System.out);
            renderImage(renderer, image, true);
        } else {
            renderer = new OneThreadRenderer();
            UserInteraction.printStartRender(System.out);
            long oneThreadStartTime = System.currentTimeMillis();
            renderImage(renderer, image, false);
            double oneThreadTime = (double) (System.currentTimeMillis() - oneThreadStartTime) / Constants.TO_SECONDS;
            renderer2 = new MultiThreadRenderer();
            UserInteraction.printStartRender(System.out);
            long multiThreadStartTime = System.currentTimeMillis();
            renderImage(renderer2, image, true);
            double multiThreadTime =
                (double) (System.currentTimeMillis() - multiThreadStartTime) / Constants.TO_SECONDS;
            UserInteraction.printStatistics(oneThreadTime, multiThreadTime, System.out);
        }

    }

    private void renderImage(ImageRenderer renderer, FractalImage image, boolean multithread) {
        renderer.render(image);
        image.createImage(multithread);
    }
}
