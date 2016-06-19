import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

/**
 * Created by ${perdu} on ${16.06.16}.
 */

/**
 * Dieses enum kapselt alle Sounds, damit sie überall aufrufbar sind
 *
 * 1) benennen
 * 2) um einen speziellen Sound zu spielen einfach nur mit SoundEffect.SOUND_NAME.play() aufrufen
 * 3) Mit der  static method SoundEffect.init()
 *      kann man alle vorladen
 * 4)  mit der static variable SoundEffect.volume kann man den Sound vor einstellen
 */
public enum Klang  {
    HURRA ("sounds/Hurra.wav"),//Applaus fürs geschaffte level
    INTRO ("sounds/Lalalalalala.wav"), // der nervige Startsound :)
    TOT ("sounds/Tot.wav"),// der Sound wird gespielt wenn die Bombe explodiert
    HINTERGRUND ("sounds/background.wav"); //Hintergrund

    private static final int MAX_VOLUME = 64;

    private Clip clip;

    // Konstruktor um jedes Element des Enums zu bilden

    Klang(String soundFileName) {
        try {
            URL url = this.getClass().getClassLoader().getResource(soundFileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

/**
 * play oder re-play sound effect von Anfang an
 *
 *
 * @return void
 */
    public void play(final int volume)
    {
            setVolume(volume);
            if( clip.isRunning() )
                clip.stop();
            clip.setFramePosition( 0 );    // wiederholt das stück
            clip.start();
        }
        /**
         * Lautstärkeregelung
         *
         * @param vol the volume
         */
    public void setVolume(final int vol) {

        int volume = vol;
        if (volume < 0) {
            volume = MAX_VOLUME;
        }
        float gainDb = 0.0f;
        final FloatControl gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        if (volume == 0) {
            gainDb = gain.getMinimum();

        } else if (volume < MAX_VOLUME) {

            gainDb = (float) (Math.log10(MAX_VOLUME - volume) * 13.0);
        }
        gain.setValue(-gainDb);
    }

    /**
     * loopt den Clip
     *
     * @param
     * @return void
     */
    public void playloop()
    {
            setVolume(64);
            if( clip.isRunning() )
                clip.stop();
            clip.setFramePosition( 0 );    // rewind to beginning
            clip.loop( Clip.LOOP_CONTINUOUSLY );
        }

    /**
     * stoppt den Clip
     *
     * @param
     * @return void
     */

    public void gestoppt() {
        {
          //  if (clip.isRunning())
                clip.stop();}
        }


static void init() {
        values();
    }
}