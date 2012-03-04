package com.blogspot.steigert.tyrian.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.blogspot.steigert.tyrian.Tyrian;

/**
 * The base class for all game screens.
 */
public abstract class AbstractScreen
    implements
        Screen
{
    protected final Tyrian game;
    protected final Stage stage;

    private BitmapFont font;
    private SpriteBatch batch;
    private Skin skin;

    public AbstractScreen(
        Tyrian game )
    {
        this.game = game;
        this.stage = new Stage( 0, 0, true );
    }

    protected String getName()
    {
        return getClass().getSimpleName();
    }

    public BitmapFont getFont()
    {
        if( font == null ) {
            font = new BitmapFont();
        }
        return font;
    }

    public SpriteBatch getBatch()
    {
        if( batch == null ) {
            batch = new SpriteBatch();
        }
        return batch;
    }

    protected Skin getSkin()
    {
        if( skin == null ) {
            FileHandle skinFile = Gdx.files.internal( "skin/uiskin.json" );
            FileHandle textureFile = Gdx.files.internal( "skin/uiskin.png" );
            skin = new Skin( skinFile, textureFile );
        }
        return skin;
    }

    // Screen implementation

    @Override
    public void show()
    {
        Gdx.app.log( Tyrian.LOG, "Showing screen: " + getName() );

        // set the stage as the input processor
        Gdx.input.setInputProcessor( stage );
    }

    @Override
    public void resize(
        int width,
        int height )
    {
        Gdx.app.log( Tyrian.LOG, "Resizing screen: " + getName() + " to: " + width + " x " + height );

        // resize the stage
        stage.setViewport( width, height, true );
    }

    @Override
    public void render(
        float delta )
    {
        // (1) process the game logic

        // update the actors
        stage.act( delta );

        // (2) draw the result

        // clear the screen with the given RGB color (black)
        Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );

        // draw the actors
        stage.draw();
    }

    @Override
    public void hide()
    {
        Gdx.app.log( Tyrian.LOG, "Hiding screen: " + getName() );

        // dispose the resources by default
        dispose();
    }

    @Override
    public void pause()
    {
        Gdx.app.log( Tyrian.LOG, "Pausing screen: " + getName() );
    }

    @Override
    public void resume()
    {
        Gdx.app.log( Tyrian.LOG, "Resuming screen: " + getName() );
    }

    @Override
    public void dispose()
    {
        Gdx.app.log( Tyrian.LOG, "Disposing screen: " + getName() );
        stage.dispose();
        if( font != null ) font.dispose();
        if( batch != null ) batch.dispose();
        if( skin != null ) skin.dispose();
    }
}
