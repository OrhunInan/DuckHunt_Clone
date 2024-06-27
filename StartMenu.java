import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

/**
 * Extension of the StackPane class used for calling start menu.
 */
public class StartMenu extends StackPane {

    /**
     * Default constructor for the object.
     * Constructor also sets start menu as the current scene on the gameStage
     */
    public StartMenu(){

        String startMassage = "\n\n\n\n\nPRESS ENTER TO START\nPRESS ESC TO EXIT";

        this.setPrefSize(DuckHunt.screenWidth, DuckHunt.screenHeight);
        this.setBackground(DuckHunt.assets.getStartscreenBackground());
        this.getChildren().add(new CenteredOrangeText(startMassage, 15 * DuckHunt.scale));

        SoundController titleMusic = new SoundController("Title", true, DuckHunt.volume);
        titleMusic.play();

        Scene startScene = new Scene(this, DuckHunt.screenWidth, DuckHunt.screenHeight);
        startScene.setOnKeyPressed(e -> { //Defining key actions.

            if (e.getCode() == KeyCode.ENTER) { // Jumps to selection menu.

                DuckHunt.activeScreen = new SelectionMenu(titleMusic);
            }
            else if (e.getCode() == KeyCode.ESCAPE) { // Exits game.

                DuckHunt.gameStage.close();
            }
        });

        DuckHunt.gameStage.setScene(startScene);
    }

    /**
     * Construct for the cases where the program needs to call this object with
     * already playing background music
     *
     * @param titleMusic actively playing SoundController
     * object that is created for background music
     */
    public StartMenu(SoundController titleMusic){

        String startMassage = "\n\n\n\n\nPRESS ENTER TO START\nPRESS ESC TO EXIT";

        this.setPrefSize(DuckHunt.screenWidth, DuckHunt.screenHeight);
        this.setBackground(DuckHunt.assets.getStartscreenBackground());
        this.getChildren().add(new CenteredOrangeText(startMassage, 15 * DuckHunt.scale));

        Scene startScene = new Scene(this, DuckHunt.screenWidth, DuckHunt.screenHeight);
        startScene.setOnKeyPressed(e -> { //Defining key actions.

            if (e.getCode() == KeyCode.ENTER) {// Jumps to selection menu.

                DuckHunt.activeScreen = new SelectionMenu(titleMusic);
            }
            else if (e.getCode() == KeyCode.ESCAPE) { // Exits game.

                DuckHunt.gameStage.close();
            }
        });

        DuckHunt.gameStage.setScene(startScene);
    }

}
