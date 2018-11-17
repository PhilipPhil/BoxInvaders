package main;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MusicPlayer {

    public static Clip clip;
    public static File music;


    public static void load(){
        music = new File("res/Song1.WAV");

        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(music));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startMusic(){
        try {

            clip.loop(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stopMusic(){
        try {
            clip.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
