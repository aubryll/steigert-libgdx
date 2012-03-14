package com.blogspot.steigert.tyrian.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.TableLayout;
import com.blogspot.steigert.tyrian.Tyrian;
import com.blogspot.steigert.tyrian.domain.Profile;
import com.blogspot.steigert.tyrian.services.SoundManager.TyrianSound;

/**
 * A simple high scores screen.
 */
public class HighScoresScreen
    extends
        AbstractScreen
{
    private Table table;

    public HighScoresScreen(
        Tyrian game )
    {
        super( game );
    }

    @Override
    public void show()
    {
        super.show();

        // retrieve the custom skin for our 2D widgets
        Skin skin = super.getSkin();

        // create the table actor and add it to the stage
        table = new Table( skin );
        stage.addActor( table );

        // retrieve the table's layout
        TableLayout layout = table.getTableLayout();
        Profile profile = game.getProfileManager().retrieveProfile();

        // create the labels widgets
        String level1Highscore = String.valueOf( profile.getHighScore( 0 ) );
        Label episode1HighScore = new Label( level1Highscore, skin );
        layout.register( "episode1HighScore", episode1HighScore );

        String level2Highscore = String.valueOf( profile.getHighScore( 1 ) );
        Label episode2HighScore = new Label( level2Highscore, skin );
        layout.register( "episode2HighScore", episode2HighScore );

        String level3Highscore = String.valueOf( profile.getHighScore( 2 ) );
        Label episode3HighScore = new Label( level3Highscore, skin );
        layout.register( "episode3HighScore", episode3HighScore );

        // register the back button
        TextButton backButton = new TextButton( "Back to main menu", skin );
        backButton.setClickListener( new ClickListener() {
            @Override
            public void click(
                Actor actor,
                float x,
                float y )
            {
                game.getSoundManager().play( TyrianSound.CLICK );
                game.setScreen( game.getMenuScreen() );
            }
        } );
        layout.register( "backButton", backButton );

        // finally, parse the layout descriptor
        layout.parse( Gdx.files.internal( "layout-descriptors/high-scores-screen.txt" ).readString() );
    }

    @Override
    public void resize(
        int width,
        int height )
    {
        super.resize( width, height );

        // resize the table
        table.width = width;
        table.height = height;

        // we need a complete redraw
        table.invalidateHierarchy();
    }
}
