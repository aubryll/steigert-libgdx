package com.blogspot.steigert.tyrian.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
    private TextureAtlas atlas;

    public AbstractScreen(
        Tyrian game )
    {
        this.game = game;
        this.stage = new Stage( Tyrian.VIEWPORT_WIDTH, Tyrian.VIEWPORT_HEIGHT, true );
    }

    protected String getName()
    {
        return getClass().getSimpleName();
    }

    /**
     * Whether to use the fixed viewport dimensions (
     * {@link Tyrian#VIEWPORT_WIDTH} and {@link Tyrian#VIEWPORT_HEIGHT}).
     * <p>
     * Implementations should return <code>true</code> on resolution-dependent
     * screens. In this case, the screen will work with a fixed resolution and
     * later it will be stretched to the actual display's resolution.
     * <p>
     * On the other hand, if the screen knows to adjust itself to any given
     * resolution, this method should return <code>false</code>. It's up to the
     * implementation to resize its components.
     */
    protected abstract boolean useFixedViewportDimensions();

    // Lazily loaded collaborators

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

    public TextureAtlas getAtlas()
    {
        if( atlas == null ) {
            atlas = new TextureAtlas( Gdx.files.internal( "image-atlases/pages-info" ) );
        }
        return atlas;
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

        // resize the viewport
        if( ! useFixedViewportDimensions() ) {
            stage.setViewport( width, height, false );
        }
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

        // dispose the screen when leaving the screen;
        // note that the dipose() method is not called automatically by the
        // framework, so we must figure out when it's appropriate to call it
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

        // as the collaborators are lazily loaded, they may be null
        if( font != null ) font.dispose();
        if( batch != null ) batch.dispose();
        if( skin != null ) skin.dispose();
        if( atlas != null ) atlas.dispose();
    }
}
