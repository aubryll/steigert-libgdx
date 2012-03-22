package com.blogspot.steigert.tyrian.screens;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.OnActionCompleted;
import com.badlogic.gdx.scenes.scene2d.actions.FadeIn;
import com.blogspot.steigert.tyrian.Tyrian;
import com.blogspot.steigert.tyrian.domain.Level;
import com.blogspot.steigert.tyrian.domain.Profile;
import com.blogspot.steigert.tyrian.screens.scene2d.Ship2D;
import com.blogspot.steigert.tyrian.services.MusicManager.TyrianMusic;

public class LevelScreen
    extends
        AbstractScreen
{
    private final Profile profile;
    private final Level level;

    private Ship2D ship2d;

    public LevelScreen(
        Tyrian game,
        int targetLevelId )
    {
        super( game );

        // set the basic attributes
        profile = game.getProfileManager().retrieveProfile();
        level = game.getLevelManager().findLevelById( targetLevelId );
    }
    
    @Override
    protected boolean isGameScreen()
    {
        return true;
    }

    @Override
    public void show()
    {
        super.show();

        // play the level music
        game.getMusicManager().play( TyrianMusic.LEVEL );

        // create the ship and add it to the stage
        ship2d = Ship2D.create( profile.getShip(), getAtlas() );

        // center the ship horizontally
        ship2d.x = ( stage.width() / 2 + ship2d.width / 2 );
        ship2d.y = ( ship2d.height );

        // add the ship to the stage
        stage.addActor( ship2d );

        // add a fade-in effect to the whole stage
        stage.getRoot().color.a = 0f;
        FadeIn fadeInAction = FadeIn.$( 1f );
        fadeInAction.setCompletionListener( new OnActionCompleted() {
            @Override
            public void completed(
                Action action )
            {
                ship2d.setEnabled( true );
            }
        } );
        stage.getRoot().action( fadeInAction );
    }
}
