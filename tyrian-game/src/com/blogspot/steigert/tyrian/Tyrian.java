package com.blogspot.steigert.tyrian;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.blogspot.steigert.tyrian.screens.LevelScreen;
import com.blogspot.steigert.tyrian.screens.SplashScreen;
import com.blogspot.steigert.tyrian.services.LevelManager;
import com.blogspot.steigert.tyrian.services.MusicManager;
import com.blogspot.steigert.tyrian.services.PreferencesManager;
import com.blogspot.steigert.tyrian.services.ProfileManager;
import com.blogspot.steigert.tyrian.services.SoundManager;

/**
 * The game's main class, called as application events are fired.
 */
public class Tyrian
    extends
        Game
{
    // constant useful for logging
    public static final String LOG = Tyrian.class.getSimpleName();

    // whether we are in development mode
    public static final boolean DEV_MODE = false;

    // a libgdx helper class that logs the current FPS each second
    private FPSLogger fpsLogger;

    // services
    private PreferencesManager preferencesManager;
    private ProfileManager profileManager;
    private LevelManager levelManager;
    private MusicManager musicManager;
    private SoundManager soundManager;

    public Tyrian()
    {
    }

    // Services' getters

    public PreferencesManager getPreferencesManager()
    {
        return preferencesManager;
    }

    public ProfileManager getProfileManager()
    {
        return profileManager;
    }

    public LevelManager getLevelManager()
    {
        return levelManager;
    }

    public MusicManager getMusicManager()
    {
        return musicManager;
    }

    public SoundManager getSoundManager()
    {
        return soundManager;
    }

    // Game-related methods

    @Override
    public void create()
    {
        Gdx.app.log( Tyrian.LOG, "Creating game on " + Gdx.app.getType() );

        // create the preferences manager
        preferencesManager = new PreferencesManager();

        // create the music manager
        musicManager = new MusicManager();
        musicManager.setVolume( preferencesManager.getVolume() );
        musicManager.setEnabled( preferencesManager.isMusicEnabled() );

        // create the sound manager
        soundManager = new SoundManager();
        soundManager.setVolume( preferencesManager.getVolume() );
        soundManager.setEnabled( preferencesManager.isSoundEnabled() );

        // create the profile manager
        profileManager = new ProfileManager();
        profileManager.retrieveProfile();

        // create the level manager
        levelManager = new LevelManager();

        // create the helper objects
        fpsLogger = new FPSLogger();
    }

    @Override
    public void resize(
        int width,
        int height )
    {
        super.resize( width, height );
        Gdx.app.log( Tyrian.LOG, "Resizing game to: " + width + " x " + height );

        // show the splash screen when the game is resized for the first time;
        // this approach avoids calling the screen's resize method repeatedly
        if( getScreen() == null ) {
            if( DEV_MODE ) {
                setScreen( new LevelScreen( this, 0 ) );
            } else {
                setScreen( new SplashScreen( this ) );
            }
        }
    }

    @Override
    public void render()
    {
        super.render();

        // output the current FPS
        if( DEV_MODE ) fpsLogger.log();
    }

    @Override
    public void pause()
    {
        super.pause();
        Gdx.app.log( Tyrian.LOG, "Pausing game" );

        // persist the profile, because we don't know if the player will come
        // back to the game
        profileManager.persist();
    }

    @Override
    public void resume()
    {
        super.resume();
        Gdx.app.log( Tyrian.LOG, "Resuming game" );
    }

    @Override
    public void setScreen(
        Screen screen )
    {
        super.setScreen( screen );
        Gdx.app.log( Tyrian.LOG, "Setting screen: " + screen.getClass().getSimpleName() );
    }

    @Override
    public void dispose()
    {
        super.dispose();
        Gdx.app.log( Tyrian.LOG, "Disposing game" );

        // dipose some services
        musicManager.dispose();
        soundManager.dispose();
    }
}
