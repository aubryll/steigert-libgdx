package com.blogspot.steigert.tyrian.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.OnActionCompleted;
import com.badlogic.gdx.scenes.scene2d.actions.Delay;
import com.badlogic.gdx.scenes.scene2d.actions.FadeIn;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;
import com.badlogic.gdx.scenes.scene2d.actions.Sequence;
import com.badlogic.gdx.scenes.scene2d.ui.Align;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.blogspot.steigert.tyrian.Tyrian;

/**
 * Shows a splash image and moves on to the next screen.
 */
public class SplashScreen
    extends
        AbstractScreen
{
    private Texture splashTexture;
    private Image splashImage;

    public SplashScreen(
        Tyrian game )
    {
        super( game );
    }

    @Override
    public void show()
    {
        super.show();

        // load the texture with the splash image
        splashTexture = new Texture( "image-atlases/splash.png" );

        // set the linear texture filter to improve the image stretching
        splashTexture.setFilter( TextureFilter.Linear, TextureFilter.Linear );
    }

    @Override
    public void resize(
        int width,
        int height )
    {
        super.resize( width, height );

        // in the image atlas, our splash image begins at (0,0) of the
        // upper-left corner and has a dimension of 512x301
        TextureRegion splashRegion = new TextureRegion( splashTexture, 0, 0, 512, 301 );

        // here we create the splash image actor and set its size
        splashImage = new Image( splashRegion, Scaling.stretch, Align.BOTTOM | Align.LEFT );
        splashImage.width = width;
        splashImage.height = height;

        // this is needed for the fade-in effect to work correctly; we're just
        // making the image completely transparent
        splashImage.color.a = 0f;

        // configure the fade-in/out effect on the splash image
        Sequence actions = Sequence.$( FadeIn.$( 0.75f ), Delay.$( FadeOut.$( 0.75f ), 1.75f ) );
        actions.setCompletionListener( new OnActionCompleted() {
            @Override
            public void completed(
                Action action )
            {
                // when the image is faded out, move on to the next screen
                game.setScreen( game.getMenuScreen() );
            }
        } );
        splashImage.action( actions );

        // and finally we add the actor to the stage
        stage.addActor( splashImage );
    }

    @Override
    public void dispose()
    {
        super.dispose();
        splashTexture.dispose();
    }
}
