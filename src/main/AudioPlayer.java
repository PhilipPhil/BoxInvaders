package main;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AudioPlayer {

    private static Map<String, File> soundMap = new HashMap<>();


    public static void load(){
        File BasicBossShooting = new File("res/BasicBossShooting.WAV");
        File BasicEnemyCrash = new File("res/BasicEnemyCrash.WAV");
        File BossShowingUp = new File("res/BossShowingUp.WAV");
        File HealthUp = new File("res/HealthUp.WAV");
        File MenuClick = new File("res/MenuClick.WAV");
        File SmartBossShooting = new File("res/SmartBossShooting.WAV");
        File SmartEnemyCrash = new File("res/SmartEnemyCrash.WAV");
        File Song1 = new File("res/Song1.WAV");
        File StartGame = new File("res/StartGame.WAV");

        soundMap.put("BasicBossShooting",BasicBossShooting);
        soundMap.put("BasicEnemyCrash",BasicEnemyCrash);
        soundMap.put("BossShowingUp",BossShowingUp);
        soundMap.put("HealthUp",HealthUp);
        soundMap.put("MenuClick",MenuClick);
        soundMap.put("SmartBossShooting",SmartBossShooting);
        soundMap.put("SmartEnemyCrash",SmartEnemyCrash);
        soundMap.put("StartGame",StartGame);
        soundMap.put("dead",new File("res/dead.WAV"));
        soundMap.put("newlevel",new File("res/newlevel.WAV"));
        soundMap.put("DingFast",new File("res/DingFast.WAV"));
        soundMap.put("DingSlow",new File("res/DingSlow.WAV"));




    }

    public static void playSound(File sound){
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static File getSound(String key){
        return soundMap.get(key);
    }
}
