/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  twinskingdom.sounds;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 *
 *  
 */
public class Sound {

    public static final AudioClip ONE = Applet.newAudioClip(Sound.class.getResource("/sounds/one.mid"));
    public static final AudioClip TWO = Applet.newAudioClip(Sound.class.getResource("/sounds/OneMoreTime_wave.wav"));
    //public static final AudioClip TREE=Applet.newAudioClip(Sound.class.getResource(""));

    public static void main(String[] args) {
        //ONE.play();
        TWO.play();
    }

    private String url;
    private AudioClip audioClip;

    public Sound(String url) {
        this.url = url;
        this.audioClip = loadAudioClip();
    }

    private AudioClip loadAudioClip() {
        return Applet.newAudioClip(Sound.class.getResource(this.url));
    }

    public void loop() {
        this.audioClip.loop();
    }

    public void stop() {
        this.audioClip.stop();
    }

    public void play() {
        this.audioClip.play();
    }

}
