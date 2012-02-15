package com.blogspot.steigert.tyrian;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;

/**
 * The game's main class, called as application events are fired.
 */
public class Tyrian
    implements
        ApplicationListener
{
    // constant useful for logging
    public static final String LOG = Tyrian.class.getSimpleName();

    // a libgdx helper class that logs the current FPS each second
    private FPSLogger fpsLogger;

    @Override
    public void create()
    {
        Gdx.app.log( Tyrian.LOG, "Creating game" );
        fpsLogger = new FPSLogger();
    }

    @Override
    public void resize(
        int width,
        int height )
    {
        Gdx.app.log( Tyrian.LOG, "Resizing game to: " + width + " x " + height );
    }

    @Override
    public void render()
    {
        // the following code clears the screen with the given RGB color (green)
        Gdx.gl.glClearColor( 0f, 1f, 0f, 1f );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        // output the current FPS
        fpsLogger.log();
    }

    @Override
    public void pause()
    {
        Gdx.app.log( Tyrian.LOG, "Pausing game" );
    }

    @Override
    public void resume()
    {
        Gdx.app.log( Tyrian.LOG, "Resuming game" );
    }

    @Override
    public void dispose()
    {
        Gdx.app.log( Tyrian.LOG, "Disposing game" );
    }
}
