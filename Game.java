import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Extension of the StackPane class used for calling game levels.
 */
public class Game extends StackPane{

    private int numOfDucks;
    private final Scene scene;
    private final int level;
    private int ammo;
    private final CenteredOrangeText ammoCount;

    /**
     * Constructor for a Game level
     *
     * @param level level to be played.
     */
    public Game(int level){

        this.setPrefSize(DuckHunt.screenWidth, DuckHunt.screenHeight);
        this.setMaxSize(DuckHunt.screenWidth, DuckHunt.screenHeight);
        this.numOfDucks = (level + 1)/2;
        this.ammo = this.numOfDucks * 3;
        this.level = level;

        //Initialization level counter.
        CenteredOrangeText levelCount = new CenteredOrangeText(String.format("Level: %d/6", this.level),
                8 * DuckHunt.scale);
        HBox levelCountPane = new HBox(levelCount);
        levelCountPane.setAlignment(Pos.TOP_CENTER);
        levelCountPane.setMouseTransparent(true);
        levelCountPane.setMaxSize(DuckHunt.screenWidth, DuckHunt.screenHeight);

        //Initialization ammo counter.
        ammoCount = new CenteredOrangeText(String.format("Ammo Left: %d", ammo), 8 * DuckHunt.scale);
        HBox ammoCountPane = new HBox(ammoCount);
        ammoCountPane.setAlignment(Pos.TOP_RIGHT);
        ammoCountPane.setMaxSize(DuckHunt.screenWidth, DuckHunt.screenHeight);

        //Initialization of a custom crosshair cursor.
        ImageView cursor = new ImageView(DuckHunt.assets.getCrosshair(DuckHunt.crosshairSelection));
        Pane cursorPane = new Pane(cursor);
        cursorPane.setMouseTransparent(true);

        // Background(and associated foreground) and the crosshair chosen by the player in the selection menu
        ImageView foreground = new ImageView(DuckHunt.assets.getForeground(DuckHunt.backgroundSelection));
        ImageView background = new ImageView(DuckHunt.assets.getBackground(DuckHunt.backgroundSelection));
        DuckLayout duckLayout = new DuckLayout(level);

        //setting everything in front of the duckLayout transparent to mouse clicks.
        foreground.setMouseTransparent(true);
        ammoCountPane.setMouseTransparent(true);
        levelCountPane.setMouseTransparent(true);

        this.getChildren().addAll(background, duckLayout, foreground, ammoCountPane, levelCountPane, cursorPane);

        this.scene = new Scene(this, DuckHunt.screenWidth, DuckHunt.screenHeight);

        // Defining new mouse rules and putting crosshair image in front of the real cursor.
        scene.setCursor(Cursor.NONE);
        scene.setOnMouseExited(e -> cursor.setVisible(false));
        scene.setOnMouseEntered(e -> cursor.setVisible(true));
        scene.setOnMouseMoved(e -> {
            cursor.setX(e.getX() - 5 * DuckHunt.scale);
            cursor.setY(e.getY() - 5 * DuckHunt.scale);
        });

        DuckHunt.gameStage.setScene(scene);
    }

    /**
     * Returns a random integer symbolizing a color according to Assets object.
     *
     * @return random integer between 0 and 2.
     */
    private static int getRandomColor(){

        return  ((int) (Math.random() * 3));
    }

    /**
     * Returns a random number in the range.
     *
     * @param min minimum number(inclusive).
     * @param max maximum number(exclusive).
     * @return random integer in given range.
     */
    private static int getRandomNumber(int min, int max){

        return  ((int) (Math.random() * max - min) + min);
    }
    /**
     * Calls game over screen to the gameStage.
     */
    private void getLevelPassed(){

        SoundController levelPassedMusic = new SoundController("LevelCompleted", false,
                DuckHunt.volume);
        levelPassedMusic.play();

        CenteredOrangeText levelPassedMessage = new CenteredOrangeText("YOU WIN!",
                15 * DuckHunt.scale);

        CenteredOrangeText selectionMessage = new CenteredOrangeText("Press ENTER to play next level",
                15 * DuckHunt.scale);
        selectionMessage.addFlashEffect();

        VBox textPane = new VBox(levelPassedMessage, selectionMessage);
        textPane.setAlignment(Pos.CENTER);

        this.getChildren().add(textPane);

        this.scene.setOnKeyPressed(e -> {// Adding Selection controls.

            if (e.getCode() == KeyCode.ENTER) {// Starts next level.

                levelPassedMusic.stop();
                DuckHunt.activeScreen = new Game(level + 1);
            }
        });
    }

    /**
     * Calls game over screen to the gameStage.
     */
    private void getGameOver(){

        SoundController gameOverMusic = new SoundController("LevelCompleted", false,
                DuckHunt.volume);
        gameOverMusic.play();

        CenteredOrangeText levelPassedMessage = new CenteredOrangeText("GAME OVER!",
                15 * DuckHunt.scale);

        CenteredOrangeText selectionMessage = new CenteredOrangeText("Press ENTER to play again\n"+
                "Press ESC to exit", 15 * DuckHunt.scale);
        selectionMessage.addFlashEffect();

        VBox textPane = new VBox( levelPassedMessage, selectionMessage);
        textPane.setAlignment(Pos.CENTER);

        this.getChildren().add(textPane);

        scene.setOnKeyPressed(e -> { // Adding Selection keys.

            if (e.getCode() == KeyCode.ENTER) { // Restarts the game.

                gameOverMusic.stop();
                DuckHunt.activeScreen = new Game(1);
            }
            else if (e.getCode() == KeyCode.ESCAPE) { // Jumps to start menu.

                gameOverMusic.stop();

                DuckHunt.activeScreen = new StartMenu();
            }
        });

    }

    /**
     * Calls end of game screen to the gameStage.
     */
    private void getEndOfGameScreen(){

        SoundController EndOfGameMusic = new SoundController("GameCompleted", false,
                DuckHunt.volume);
        EndOfGameMusic.play();

        CenteredOrangeText levelPassedMessage = new CenteredOrangeText("GAME OVER!",
                15 * DuckHunt.scale);

        CenteredOrangeText selectionMessage = new CenteredOrangeText("Press ENTER to play again\n"+
                "Press ESC to exit", 15 * DuckHunt.scale);
        selectionMessage.addFlashEffect();

        VBox textPane = new VBox( levelPassedMessage, selectionMessage);
        textPane.setAlignment(Pos.CENTER);

        this.getChildren().add(textPane);

        scene.setOnKeyPressed(e -> { // Adding Selection keys.

            if (e.getCode() == KeyCode.ENTER) { // Restarts the game.

                EndOfGameMusic.stop();
                DuckHunt.activeScreen = new Game(1);
            }
            else if (e.getCode() == KeyCode.ESCAPE) { // Jumps to title screen.

                EndOfGameMusic.stop();
                DuckHunt.activeScreen = new StartMenu();
            }
        });
    }

    /**
     * Subclass of Pane object specifically designed to layout ducks.
     */
    private class DuckLayout extends Pane {

        private Duck[] duckArr;
        private SoundController gunSound;

        /**
         * Constructor for the DuckLayout object.
         *
         * @param level decides how the ducks will be placed.
         */
        public DuckLayout(int level){

            switch (level){

                case 1:

                    level1();
                    break;


                case 2:

                    level2();
                    break;

                case 3:

                    level3();
                    break;

                case 4:

                    level4();
                    break;

                case 5:

                    level5();
                    break;

                case 6:

                    level6();
                    break;

            }

            this.setMaxHeight(DuckHunt.screenHeight);
            this.setMaxWidth(DuckHunt.screenWidth);
            this.getChildren().addAll(duckArr);
            this.setOnMouseClicked(e -> {

                ammoCount.setText(String.format("Ammo Left: %d", --ammo));

                gunSound = new SoundController("Gunshot", false, DuckHunt.volume);
                gunSound.play();

                for (Duck duck : duckArr) if (duck.eventIsOverThisObject(e.getX(), e.getY())) duck.removeDuck();

                if (numOfDucks != 0 && ammo == 0) {

                    this.setOnMouseClicked(q -> {});
                    getGameOver();
                }
            });
        }

        /**
         * This method places ducks for level 1.
         */
        private void level1(){

            this.duckArr = new Duck[1];
            this.duckArr[0] = new Duck(getRandomColor(), 6*DuckHunt.scale,
                    getRandomNumber(40, 91) * DuckHunt.scale, 1,0);
        }

        /**
         * This method places ducks for level 2.
         */
        private void level2(){

            this.duckArr = new Duck[1];
            this.duckArr[0] = new Duck(getRandomColor(), getRandomNumber(5, 218) * DuckHunt.scale,
                    140* DuckHunt.scale, 1,-1);
        }

        /**
         * This method places ducks for level 3.
         */
        private void level3(){

            this.duckArr = new Duck[2];
            this.duckArr[0] = new Duck(getRandomColor(), 6 * DuckHunt.scale,
                    getRandomNumber(40, 91)* DuckHunt.scale, 1,0);
            this.duckArr[1] = new Duck(getRandomColor(), 217* DuckHunt.scale,
                    getRandomNumber(40, 91) * DuckHunt.scale, -1,0);
        }

        /**
         * This method places ducks for level 4.
         */
        private void level4(){

            this.duckArr = new Duck[2];
            this.duckArr[0] = new Duck(getRandomColor(), getRandomNumber(5, 218) * DuckHunt.scale,
                    140 * DuckHunt.scale, 1,-1);
            this.duckArr[1] = new Duck(getRandomColor(), getRandomNumber(5, 218) * DuckHunt.scale,
                    140 * DuckHunt.scale, -1,-1);
        }

        /**
         * This method places ducks for level 5.
         */
        private void level5(){

            this.duckArr = new Duck[3];
            this.duckArr[0] = new Duck(getRandomColor(), 6 * DuckHunt.scale,  getRandomNumber(40, 91)* DuckHunt.scale,
                    1,0);
            this.duckArr[1] = new Duck(getRandomColor(), 217* DuckHunt.scale,  getRandomNumber(40, 91)* DuckHunt.scale,
                    -1,0);
            this.duckArr[2] = new Duck(getRandomColor(), getRandomNumber(5, 218) * DuckHunt.scale,
                    140* DuckHunt.scale, 1,-1);
        }

        /**
         * This method places ducks for level 6.
         */
        private void level6(){

            this.duckArr = new Duck[3];
            this.duckArr[0] = new Duck(getRandomColor(), getRandomNumber(5, 218) * DuckHunt.scale,
                    140 * DuckHunt.scale, 1,-1);
            this.duckArr[1] = new Duck(getRandomColor(), getRandomNumber(5, 218) * DuckHunt.scale,
                    140 * DuckHunt.scale, -1,-1);
            this.duckArr[2] = new Duck(getRandomColor(), getRandomNumber(5, 218) * DuckHunt.scale,
                    140 * DuckHunt.scale, 1,-1);
        }

        /**
         * Extension of the ImageView class for pre-modifying and adding new
         * fields to the object in order to emulate ducks movement
         */
        private class Duck extends ImageView {

            private final int color;
            private int xMovement;
            private int yMovement;
            private int movementTexture;
            private final double duckWidth;
            private final double duckHeight;
            private boolean isAlive;
            private final double duckZoneHeight = 179 * DuckHunt.scale;
            private final double duckZoneWidth =  255 * DuckHunt.scale;
            private Timeline duckMovement;

            /**
             * Constructor for Duck class.
             *
             * @param color The color of the duck.
             * @param X The x-coordinate of the duck.
             * @param Y The y-coordinate of the duck.
             * @param xMovement the movement direction of the duck along the x-axis
             * @param yMovement the movement direction of the duck along the y-axis
             */
            public Duck(int color, double X, double Y, int xMovement, int yMovement){

                super(DuckHunt.assets.getDuck(color, (yMovement == 0) ? 3:0));
                this.setScale();
                this.setX(X);
                this.setY(Y);
                this.isAlive = true;
                this.color = color;
                this.xMovement = xMovement;
                this.yMovement = yMovement;
                this.movementTexture = (yMovement == 0) ? 3 : 0;
                this.duckWidth = (yMovement == 0) ? 34 * DuckHunt.scale : 27 * DuckHunt.scale;
                this.duckHeight = (yMovement == 0) ? 20 * DuckHunt.scale : 29 * DuckHunt.scale;
                if (this.xMovement == -1) this.setScaleX(-this.getScaleX());

                this.duckMovement = new Timeline( new KeyFrame(Duration.seconds(0.1), event -> this.moveDuck()));
                this.duckMovement.setCycleCount(Timeline.INDEFINITE);
                this.duckMovement.play();
            }

            /**
             * Moves the duck according to its movement pattern.
             */
            private void moveDuck(){

                if (this.yMovement == 0){ // For horizontal movement.

                    if(this.isOutOfBounds('X'))  mirror('X');

                    this.setX(this.getX() + 6 * xMovement * DuckHunt.scale);
                    setTexture(3 + (++this.movementTexture % 3));
                }
                else { // For diagonal movement.

                    if (this.isOutOfBounds('B')) mirror('B'); // B is for Both.
                    if (this.isOutOfBounds('X')) mirror('X');
                    if (this.isOutOfBounds('Y')) mirror('Y');

                    this.setX(this.getX() + 4 * xMovement * DuckHunt.scale);
                    this.setY(this.getY() + 4 * yMovement * DuckHunt.scale);
                    this.setTexture(++this.movementTexture % 3);
                }
            }

            /**
             * Starts the kill animation for the duck and removes the duck from the game.
             */
            private void removeDuck(){

                if (this.isAlive){

                    if (--numOfDucks == 0) { // Finishes the level.

                        setOnMouseClicked(q -> {});

                        if(level != 6) getLevelPassed();
                        else getEndOfGameScreen();
                    }

                    // Killing duck.
                    this.duckMovement.stop();
                    this.isAlive = false;

                    // Start of hit animation
                    this.setImage(DuckHunt.assets.getDuck(this.color, 6));
                    this.duckMovement = new Timeline(new KeyFrame(Duration.seconds(0.3), event -> {}));

                    SoundController duckFalling = new SoundController("DuckFalls",
                            false, DuckHunt.volume);
                    duckFalling.play();

                    if (this.yMovement == 1) this.mirror('Y'); // mirrors the duck if the duck is upside down

                    // Start of falling portion of the hit animation.
                    this.duckMovement.setOnFinished(q -> {

                        this.mirror('X');
                        this.setImage(DuckHunt.assets.getDuck(this.color, 7));

                        this.duckMovement = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {

                            if (this.getY() <    210 * DuckHunt.scale) this.setY(this.getY() + 12 * DuckHunt.scale);
                            else this.duckMovement.stop();

                        }));
                        this.duckMovement.setCycleCount(Timeline.INDEFINITE);
                        this.duckMovement.play();
                    });

                    this.duckMovement.play();
                }
            }

            /**
             * Changes the texture for the duck
             *
             * @param movement index of the texture according to Assets object.
             */
            private void setTexture(int movement){

                this.setImage(DuckHunt.assets.getDuck(this.color, movement));
                setScale();
            }

            /**
             *Scales the duck according to DuckHunt. Scale field.
             */
            private void setScale(){

                this.setFitWidth(this.getImage().getWidth() * DuckHunt.scale);
                this.setFitHeight( this.getImage().getHeight() * DuckHunt.scale);
            }

            /**
             * Mirrors the duck according to specified axis.
             *
             * @param axis mirroring axis. X: X-axis, Y: Y-axis, Any other char: Both axes.
             */
            private void mirror(char axis){

                if (axis == 'X') {

                    this.xMovement *= -1;
                    this.setScaleX(-this.getScaleX());
                }
                else if ((axis == 'Y')) {

                    this.yMovement *= -1;
                    this.setScaleY(-this.getScaleY());
                }
                else {

                    mirror('X');
                    mirror('Y');
                }
            }

            /**
             * Checks if the duck went outside the gameStage.
             *
             * @param axis Axis to be checked. X: X-axis, Y: Y-axis, Any other char: Both axes.
             *
             * @return boolean value symbolizing if the duck went out of bounds.
             */
            private boolean isOutOfBounds(char axis){

                if (axis == 'X'){

                    return (this.getX() < 1 || this.getX() + this.duckWidth > this.duckZoneWidth);
                }
                else if(axis == 'Y'){

                    return (this.getY() < 1 || this.getY() + this.duckHeight > this.duckZoneHeight);
                }

                return (this.isOutOfBounds('X') && this.isOutOfBounds('Y'));
            }

            /**
             * Checks when coordinates are given for an event if the event happened inside the duck.
             *
             * @param eventX X-coordinate of the event.
             * @param eventY Y-coordinate of the event.
             * @return boolean value symbolizing if the event did happen inside the duck.
             */
            private boolean eventIsOverThisObject(double eventX, double eventY){

                return this.contains(eventX, eventY);
            }
        }
    }
}
