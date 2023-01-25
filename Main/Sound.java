package Main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip; 
    URL soundUrl[] = new URL[30]; 

    public Sound()
    {
        soundUrl[0] = getClass().getResource("/Res/sounds/BlueBoyAdventure.wav"); 
        soundUrl[1] = getClass().getResource("/Res/sounds/coin.wav"); 
        soundUrl[2] = getClass().getResource("/Res/sounds/powerup.wav"); 
        soundUrl[3] = getClass().getResource("/Res/sounds/unlock.wav"); 
        soundUrl[4] = getClass().getResource("/Res/sounds/fanfare.wav"); 
        soundUrl[5] = getClass().getResource("/Res/sounds/burning.wav"); 
        soundUrl[6] = getClass().getResource("/Res/sounds/hitmonster.wav"); 
        soundUrl[7] = getClass().getResource("/Res/sounds/gameover.wav"); 
    }

    public void setFile(int i)
    {
        try 
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrl[i]);
            clip = AudioSystem.getClip(); 
            clip.open(ais); 
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
        }
    }

    public void play()
    {
        clip.start(); 
    }

    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY); 
    }

    public void stop()
    {
        clip.stop();
    }    
}
