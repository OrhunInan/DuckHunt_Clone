import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

/**
 * An object designed for pre-modifying MediaPlayer object.
 */
public class SoundController{

    private final MediaPlayer mediaPlayer;
    private boolean playing;

    /**
     * Constructor for SoundController class.
     *
     * @param fileName The name of the file to be played.
     * @param playOnRepeat Whether the sound should be played on repeat or not.
     * @param soundScale The volume of the sound effect.
     */
    public SoundController(String fileName, boolean playOnRepeat, double soundScale){

        this.mediaPlayer = getAsMediaPlayer(fileName, playOnRepeat);
        this.mediaPlayer.setVolume(soundScale);
    }

    /**
     * Returns a Media object from the given file name.
     *
     * @param fileName The name of the file to be played.
     * @return A Media object from the given file name.
     */
    private static Media getAsMedia(String fileName){

        return new Media(Paths.get( String.format("assets/effects/%s.mp3", fileName)).toUri().toString());
    }

    /**
     * Returns a MediaPlayer object from the given file name and whether it should be played on repeat or not.
     *
     * @param fileName The name of the file to be played.
     * @param playOnRepeat Whether the sound should be played on repeat or not.
     * @return A MediaPlayer object from the given file name and whether it should be played on repeat or not.
     */
    private static MediaPlayer getAsMediaPlayer(String fileName, boolean playOnRepeat){

        MediaPlayer mediaPlayer = new MediaPlayer(getAsMedia(fileName));
        if (playOnRepeat) mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        return mediaPlayer;
    }

    /**
     * Plays the sound effect. For more information please refer
     * to documentation for the MediaPlayer objects play method.
     */
    public void play(){

        this.mediaPlayer.play();
    }

    /**
     * Stops the sound effect.For more information please refer
     * to documentation for the MediaPlayer objects stop method.
     */
    public void stop(){

        this.mediaPlayer.stop();
    }

    /**
     * Sets a Runnable object to be run when the media has finished playing. For more
     * information please refer to documentation for the MediaPlayer objects setOnEndOfMedia method.
     *
     * @param value The Runnable object to be run when the media has finished playing.
     */
    public void setOnEndOfMedia(Runnable value){

        this.mediaPlayer.setOnEndOfMedia(value);
    }
}
