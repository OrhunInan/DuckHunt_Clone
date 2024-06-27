import javafx.application.Application;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class DuckHunt extends Application {

    // These are the parameters for customizing sound and screen size.
    public static double volume = 0.6;
    public static double scale = 4;

    // These are the parameters that are crucial for the execution of the game. PLEASE DO NOT CHANGE ANY PARAMETERS!
    public static Stage gameStage; // This variable is for accessing the mainStage from outside the class.
    public static double screenWidth; // Easy access to scaled Screen Width.
    public static double screenHeight; // Easy access to scaled Screen  Height.
    public static Assets assets; // Caching and easily accessing assets.
    public static int backgroundSelection = 0; // For accessing background selection from outside of class.
    public static int crosshairSelection = 0; // For accessing crosshair selection from outside of class.
    public static StackPane activeScreen; // For changing active screen from outside of class.

    /**
     * Initialization of the program
     * This function is necessary for IDE.
     *
     * @param args arguments given at the start of the program.
     */
    public static void main(String[] args) {

        screenWidth = scale *256;
        screenHeight = scale *240;
        assets = new Assets();

        launch(args); // Start of javafx application.
    }

    /**
     *
     * @param MainStage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage MainStage){

        gameStage = MainStage; // Making mainStage accessible form outside the class.

        // Adding necessary modifications to mainStage/gameStage.
        gameStage.getIcons().add(DuckHunt.assets.favicon);
        gameStage.setTitle("HUBBM DuckHunt");
        gameStage.setResizable(false);

        activeScreen = new StartMenu(); // Starts showing start menu.


        gameStage.show();
    }
}