package com.blogspot.steigert.tyrian.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.TableLayout;
import com.blogspot.steigert.tyrian.Tyrian;
import com.blogspot.steigert.tyrian.services.SoundManager.TyrianSound;

public class MenuScreen
    extends
        AbstractScreen
{
    private Table table;

    public MenuScreen(
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
        table.width = stage.width();
        table.height = stage.height();
        stage.addActor( table );

        // retrieve the table's layout
        TableLayout layout = table.getTableLayout();

        // register the button "start game"
        TextButton startGameButton = new TextButton( "Start game", skin );
        startGameButton.setClickListener( new ClickListener() {
            @Override
            public void click(
                Actor actor,
                float x,
                float y )
            {
                game.getSoundManager().play( TyrianSound.CLICK );
                game.setScreen( new StartGameScreen( game ) );
            }
        } );
        layout.register( "startGameButton", startGameButton );

        // register the button "options"
        TextButton optionsButton = new TextButton( "Options", skin );
        optionsButton.setClickListener( new ClickListener() {
            @Override
            public void click(
                Actor actor,
                float x,
                float y )
            {
                game.getSoundManager().play( TyrianSound.CLICK );
                game.setScreen( new OptionsScreen( game ) );
            }
        } );
        layout.register( "optionsButton", optionsButton );

        // register the button "high scores"
        TextButton highScoresButton = new TextButton( "High Scores", skin );
        highScoresButton.setClickListener( new ClickListener() {
            @Override
            public void click(
                Actor actor,
                float x,
                float y )
            {
                game.getSoundManager().play( TyrianSound.CLICK );
                game.setScreen( new HighScoresScreen( game ) );
            }
        } );
        layout.register( "highScoresButton", highScoresButton );

        // finally, parse the layout descriptor
        layout.parse( Gdx.files.internal( "layout-descriptors/menu-screen.txt" ).readString() );
    }
}
