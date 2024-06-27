import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * Extension of the StackPane class used for calling selection screen.
 */
public class SelectionMenu extends StackPane {

    /**
     * Default constructor for the SelectionMenu.
     * Constructor also sets selection menu as the current scene
     * on the gameStage
     *
     * @param titleMusic actively playing SoundController
     * object that is created for background music
     */
    public SelectionMenu(SoundController titleMusic){

        this.setPrefSize(DuckHunt.screenWidth, DuckHunt.screenHeight);


        //Resets background and crosshair choices.
        DuckHunt.backgroundSelection = 0;
        DuckHunt.crosshairSelection = 0;

        String selectionMessage = "USE ARROW KEYS TO NAVIGATE\nPRESS ENTER TO START\nPRESS ESC TO EXIT";

        //Initiation of the selection text.
        HBox top = new HBox();
        top.getChildren().add(new CenteredOrangeText(selectionMessage, 8*DuckHunt.scale));
        top.setAlignment(Pos.TOP_CENTER);
        top.setPrefSize(DuckHunt.screenWidth, DuckHunt.screenHeight);
        top.setMaxHeight(DuckHunt.screenHeight);

        //Initiation of crosshair and background.
        ImageView crosshair = new ImageView(DuckHunt.assets.getCrosshair(DuckHunt.crosshairSelection));
        ImageView foreground = new ImageView(DuckHunt.assets.getForeground(DuckHunt.backgroundSelection));
        ImageView background = new ImageView(DuckHunt.assets.getBackground(DuckHunt.backgroundSelection));

        this.getChildren().addAll(background, foreground, top, crosshair);

        Scene scene = new Scene(this, DuckHunt.screenWidth, DuckHunt.screenHeight);
        scene.setOnKeyPressed(e -> { // Defining Key Actions

            switch (e.getCode()){

                case UP: // Changes crosshair.

                    DuckHunt.crosshairSelection = Math.floorMod(DuckHunt.crosshairSelection - 1, 7);
                    crosshair.setImage(DuckHunt.assets.getCrosshair(DuckHunt.crosshairSelection));
                    break;

                case DOWN: // Changes crosshair.

                    DuckHunt.crosshairSelection = Math.floorMod(DuckHunt.crosshairSelection + 1, 7);
                    crosshair.setImage(DuckHunt.assets.getCrosshair(DuckHunt.crosshairSelection));
                    break;

                case LEFT: // Changes background.

                    DuckHunt.backgroundSelection = Math.floorMod(DuckHunt.backgroundSelection - 1, 6);
                    foreground.setImage(DuckHunt.assets.getForeground(DuckHunt.backgroundSelection));
                    background.setImage(DuckHunt.assets.getBackground(DuckHunt.backgroundSelection));
                    break;

                case RIGHT: // Changes background.

                    DuckHunt.backgroundSelection = Math.floorMod(DuckHunt.backgroundSelection + 1, 6);
                    foreground.setImage(DuckHunt.assets.getForeground(DuckHunt.backgroundSelection));
                    background.setImage(DuckHunt.assets.getBackground(DuckHunt.backgroundSelection));
                    break;

                case ENTER: // Starts game.

                    scene.setOnKeyPressed(q -> {});

                    if (DuckHunt.volume != 0){

                        titleMusic.stop();

                        SoundController introMusic = new SoundController("Intro", false,
                                DuckHunt.volume);
                        introMusic.setOnEndOfMedia(() -> {

                            DuckHunt.activeScreen = new Game(1);
                        });
                        introMusic.play();
                    }
                    else DuckHunt.activeScreen = new Game(1);

                    break;

                case ESCAPE: //Returns to title screen.

                    DuckHunt.activeScreen = new StartMenu(titleMusic);
                    break;
            }
        });

        DuckHunt.gameStage.setScene(scene);
    }
}
