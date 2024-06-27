import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.util.Duration;

/**
 * this object is designed to create a pre-modified version of
 * the Text object from javaFX
 */
public class CenteredOrangeText extends Text {

    boolean isVisible;

    /**
     * Constructor for the CenteredOrangeText class.
     *
     * @param content String text to be showed
     * @param size Font size of the text
     */
    public CenteredOrangeText(String content, double size){

        super(content);
        this.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR,size));
        this.setFill(Color.ORANGE);
        this.setTextAlignment(TextAlignment.CENTER);
    }

    /**
     * This method adds a flash effect to the text.
     */
    public void addFlashEffect(){

        this.isVisible = true;

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> {

            this.setVisible(this.isVisible);
            this.isVisible = !this.isVisible;
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
