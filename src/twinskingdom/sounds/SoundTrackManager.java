/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twinskingdom.sounds;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import twinskingdom.game.GameSettings;

/**
 *
 *  
 */
public class SoundTrackManager implements Observer {

    private static final Sound MAIN_DEFAULT_TRACK = new Sound("/sounds/VivaldiPrimavera_wave.wav");

    private List<String> soundList;

    private Sound currentSoundTrack;
    private static SoundTrackManager instance;
    private boolean audioEnabled;

    public SoundTrackManager() {
        this.currentSoundTrack = MAIN_DEFAULT_TRACK;
        this.audioEnabled = true;
        this.soundList = new LinkedList<>();
        this.soundList.add("/sounds/OneMoreTime_wave.wav");
        this.soundList.add("/sounds/BadGuy_wave.wav");
        this.soundList.add("/sounds/WhatIsLove_wave.wav");
        this.soundList.add("/sounds/BachBadinere_wave.wav");
        this.soundList.add("/sounds/Blue_wave.wav");
        this.soundList.add("/sounds/Boss_theme_last.mid");
        this.soundList.add("/sounds/FinalBoss_wave.wav");
    }

    static {
        instance = new SoundTrackManager();
    }

    public static SoundTrackManager getInstance() {
        return instance;
    }

    public Sound getCurrentSoundTrack() {
        return currentSoundTrack;
    }

    public void setCurrentSoundTrack(int levelID) {
        this.currentSoundTrack.stop();
        try {
            this.currentSoundTrack = new Sound(soundList.get(levelID));
        } catch (Exception e) {
            e.printStackTrace();
            this.currentSoundTrack = MAIN_DEFAULT_TRACK;
        }
        if (audioEnabled) {
            this.currentSoundTrack.loop();
        } else {
            this.currentSoundTrack.stop();
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        GameSettings gameSettings = (GameSettings) o;
        this.audioEnabled = gameSettings.isAudioEnabled();
        if (!gameSettings.isAudioEnabled()) {
            currentSoundTrack.stop();
        } else {
            currentSoundTrack.loop();
        }

    }

}
