package com.blogspot.steigert.tyrian.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
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
import com.blogspot.steigert.tyrian.services.MusicManager.TyrianMusic;

/**
 * Shows a splash image and moves on to the next screen.
 */
public class SplashScreen
    extends
        AbstractScreen
{
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

        // start playing the menu music
        game.getMusicManager().play( TyrianMusic.MENU );

        // retrieve the splash image's region from the atlas
        AtlasRegion splashRegion = getAtlas().findRegion( "splash-image" );

        // here we create the splash image actor; its size is set when the
        // resize() method gets called
        splashImage = new Image( splashRegion, Scaling.stretch, Align.BOTTOM | Align.LEFT );

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
    public void resize(
        int width,
        int height )
    {
        super.resize( width, height );

        // resize the splash image
        splashImage.width = width;
        splashImage.height = height;

        // we need a complete redraw
        splashImage.invalidateHierarchy();
    }
}
