package com.blogspot.steigert.tyrian.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blogspot.steigert.tyrian.Tyrian;

/**
 * The base class for all game screens.
 */
public abstract class AbstractScreen
    implements
        Screen
{
    protected final Tyrian game;
    protected final BitmapFont font;
    protected final SpriteBatch batch;

    public AbstractScreen(
        Tyrian game )
    {
        this.game = game;
        this.font = new BitmapFont();
        this.batch = new SpriteBatch();
    }

    @Override
    public void show()
    {
    }

    @Override
    public void resize(
        int width,
        int height )
    {
    }

    @Override
    public void render(
        float delta )
    {
        // the following code clears the screen with the given RGB color (black)
        Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
    }

    @Override
    public void hide()
    {
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }

    @Override
    public void dispose()
    {
        font.dispose();
        batch.dispose();
    }
}
