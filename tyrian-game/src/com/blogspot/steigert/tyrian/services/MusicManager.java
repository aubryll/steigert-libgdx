package com.blogspot.steigert.tyrian.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.blogspot.steigert.tyrian.Tyrian;

/**
 * A service that manages the background music.
 * <p>
 * Only one music may be playing at a given time.
 */
public class MusicManager
{
    /**
     * The available music files.
     */
    public enum TyrianMusic
    {
        MENU( "music/menu.ogg" ),
        LEVEL( "music/level.ogg" );

        private final String fileName;

        private TyrianMusic(
            String musicFileName )
        {
            this.fileName = musicFileName;
        }

        public String getFileName()
        {
            return fileName;
        }
    }

    /**
     * Holds the music currently being played, if any.
     */
    private Music musicBeingPlayed;

    /**
     * The volume to be set on the music.
     */
    private float volume = 1f;

    /**
     * Creates the music manager.
     */
    public MusicManager()
    {
    }

    /**
     * Plays the given music (starts the streaming).
     * <p>
     * If there is already a music being played it is stopped automatically.
     */
    public void play(
        TyrianMusic music )
    {
        Gdx.app.log( Tyrian.LOG, "Playing music: " + music.name() );
        stop();

        FileHandle musicFile = Gdx.files.internal( music.getFileName() );
        musicBeingPlayed = Gdx.audio.newMusic( musicFile );
        musicBeingPlayed.setVolume( volume );
        musicBeingPlayed.setLooping( true );
        musicBeingPlayed.play();
    }

    /**
     * Stops and disposes the current music being played, if any.
     */
    public void stop()
    {
        if( musicBeingPlayed != null ) {
            Gdx.app.log( Tyrian.LOG, "Stopping current music" );
            musicBeingPlayed.stop();
            musicBeingPlayed.dispose();
        }
    }

    /**
     * Sets the music volume which must be inside the range [0,1].
     */
    public void setVolume(
        float volume )
    {
        Gdx.app.log( Tyrian.LOG, "Adjusting volume to: " + volume );

        // check and set the new volume
        if( volume < 0 || volume > 1f ) {
            throw new IllegalArgumentException( "The volume must be inside the range: [0,1]" );
        }
        this.volume = volume;

        // if there is a music being played, change its volume
        if( musicBeingPlayed != null ) {
            musicBeingPlayed.setVolume( volume );
        }
    }
}
