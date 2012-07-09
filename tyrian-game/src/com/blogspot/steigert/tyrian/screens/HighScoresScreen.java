package com.blogspot.steigert.tyrian.screens;

import com.badlogic.gdx.scenes.scene2d.ActorEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.blogspot.steigert.tyrian.Tyrian;
import com.blogspot.steigert.tyrian.domain.Profile;
import com.blogspot.steigert.tyrian.services.SoundManager.TyrianSound;
import com.blogspot.steigert.tyrian.utils.DefaultActorListener;

/**
 * A simple high scores screen.
 */
public class HighScoresScreen
    extends
        AbstractScreen
{
    public HighScoresScreen(
        Tyrian game )
    {
        super( game );
    }

    @Override
    public void show()
    {
        super.show();
        Profile profile = game.getProfileManager().retrieveProfile();

        // retrieve the default table actor
        Table table = super.getTable();
        table.defaults().spaceBottom( 30 );
        table.add( "High scores" ).colspan( 2 );

        // episode 1 high-score
        String level1Highscore = String.valueOf( profile.getHighScore( 0 ) );
        Label episode1HighScore = new Label( level1Highscore, getSkin() );
        table.row();
        table.add( "Episode 1" );
        table.add( episode1HighScore );

        String level2Highscore = String.valueOf( profile.getHighScore( 1 ) );
        Label episode2HighScore = new Label( level2Highscore, getSkin() );
        table.row();
        table.add( "Episode 2" ).center();
        table.add( episode2HighScore );

        String level3Highscore = String.valueOf( profile.getHighScore( 2 ) );
        Label episode3HighScore = new Label( level3Highscore, getSkin() );
        table.row();
        table.add( "Episode 3" );
        table.add( episode3HighScore );

        // register the back button
        TextButton backButton = new TextButton( "Back to main menu", getSkin() );
        backButton.addListener( new DefaultActorListener() {
            @Override
            public void touchUp(
                ActorEvent event,
                float x,
                float y,
                int pointer,
                int button )
            {
                super.touchUp( event, x, y, pointer, button );
                game.getSoundManager().play( TyrianSound.CLICK );
                game.setScreen( new MenuScreen( game ) );
            }
        } );
        table.row();
        table.add( backButton ).size( 250, 60 ).colspan( 2 );
    }
}
