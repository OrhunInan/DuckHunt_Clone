import javafx.scene.image.Image;
import javafx.scene.layout.*;


 /**
  * This class contains all the image assets used in the game.
  */
public class Assets {

    private Image[][] DuckArr;
    private Image[] backgroundArr;
    private Image[] foregroundArr;
    private Image[] crosshairArr;
    public Image favicon;
    private Background startscreenBackground;

     /**
      * Constructor for the Assets class.
      */
    public Assets(){

        this.favicon = new Image("assets/favicon/1.png");
        this.startscreenBackground = new Background(
                new BackgroundImage(
                        new Image("assets/welcome/1.png"),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(DuckHunt.screenWidth,DuckHunt.screenHeight,false,
                                false,false,false)
                )
        );

        // Assigning all the arrays.
        getDuckArr();
        getForegroundArr();
        getBackgroundArr();
        getCrosshairArr();
    }

    /**
      * This method initializes the DuckArr array with all the images of ducks.
      */
    private void getDuckArr(){

        this.DuckArr = new Image[3][8];
        String relativePath;
        String[] color = {"black", "blue", "red"};

        for (int j = 0; j < 3 ; j++){

            for (int i = 0; i < 8 ; i++){

                relativePath = "assets/duck_" + color[j] + "/" + (i + 1) + ".png";
                this.DuckArr[j][i] = new Image(relativePath);
            }
        }
    }

     /**
      * This method initializes the backgroundArr array with all the images of backgrounds.
      */
    private void getBackgroundArr(){

        this.backgroundArr = new Image[6];
        String relativePath;

        for (int i = 0; i < 6 ; i++){

            relativePath = "assets/background/" + (i+1) + ".png";
            this.backgroundArr[i] = new Image(relativePath, DuckHunt.screenWidth, DuckHunt.screenHeight,
                    false, false, true);
        }
    }

     /**
      * This method initializes the crosshairArr array with all the images of crosshairs.
      */
    private void getCrosshairArr(){

        this.crosshairArr = new Image[7];
        String relativePath;

        for (int i = 0; i < 7 ; i++){

            relativePath = "assets/crosshair/" + (i+1) + ".png";
            this.crosshairArr[i] = new Image(relativePath, 11 * DuckHunt.scale,
                    11 * DuckHunt.scale, false, false);
        }
    }

     /**
      * This method initializes the foreground array with images.
      */
    private void getForegroundArr(){
        this.foregroundArr = new Image[6];
        String relativePath;

        for (int i = 0; i < 6 ; i++){

            relativePath = "assets/foreground/" + (i+1) + ".png";
            this.foregroundArr[i] = new Image(relativePath, DuckHunt.screenWidth, DuckHunt.screenHeight,
                    false, false);
        }
    }

     /**
      * This method returns the start screen background.
      *
      * @return startScreenBackground
      */
    public Background getStartscreenBackground() {

        return startscreenBackground;
    }

     /**
      * This method returns the duck image based on color and movement.
      *
      * @param color color of the duck. 0: Black, 1:Blue, 2: Red.
      * @param movement specific texture of the duck. 0: diagonal(1),
      * 1: diagonal(2), 2: diagonal(3), 3: diagonal(1), 4: diagonal(2),
      * 5: diagonal(3), 6: falling(1), 7: falling(2),
      * @return DuckArr[color][movement]
      */
    public Image getDuck(int color, int movement){

        return DuckArr[color][movement];
    }

     /**
      * This method returns the crosshair image based on index.
      *
      * @param index correlation between index and assets file is:
      * index = asset name - 1.
      * @return crosshairArr[index]
      */
    public Image getCrosshair(int index){

        return crosshairArr[index];
    }

     /**
      * This method returns the background image based on index.
      *
      * @param index correlation between index and assets file is:
      * index = asset name - 1.
      * @return backgroundArr[index]
      */
    public Image getBackground(int index){

        return backgroundArr[index];
    }

     /**
      * This method returns the foreground image based on index.
      *
      * @param index correlation between index and assets file is:
      * index = asset name - 1.
      * @return foregroundArr[index]
      */
    public Image getForeground(int index){

        return foregroundArr[index];
    }
}
