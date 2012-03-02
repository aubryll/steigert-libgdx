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

public class MenuScreen
    extends
        AbstractScreen
{
    public MenuScreen(
        Tyrian game )
    {
        super( game );
    }

    @Override
    public void resize(
        int width,
        int height )
    {
        super.resize( width, height );

        // retrieve the skin (created on the AbstractScreen class)
        Skin skin = super.getSkin();

        // create the table actor
        Table table = new Table( getSkin() );
        table.width = width;
        table.height = height;

        // add the table to the stage and retrieve its layout
        stage.addActor( table );
        TableLayout layout = table.getTableLayout();

        // register the label "welcome"
        Label welcomeLabel = new Label( "Welcome to Tyrian for Android!", skin );
        layout.register( "welcomeMessage", welcomeLabel );

        // register the button "start game"
        TextButton startGameButton = new TextButton( "Start game", skin );
        startGameButton.setClickListener( new ClickListener() {
            @Override
            public void click(
                Actor actor,
                float x,
                float y )
            {
                game.setScreen( game.getStartGameScreen() );
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
                game.setScreen( game.getOptionsScreen() );
            }
        } );
        layout.register( "optionsButton", optionsButton );

        // register the button "hall of fame"
        TextButton hallOfFameButton = new TextButton( "Hall of Fame", skin );
        hallOfFameButton.setClickListener( new ClickListener() {
            @Override
            public void click(
                Actor actor,
                float x,
                float y )
            {
                game.setScreen( game.getHallOfFameScreen() );
            }
        } );
        layout.register( "hallOfFameButton", hallOfFameButton );

        // finally, parse the layout descriptor
        layout.parse( Gdx.files.internal( "layout-descriptors/menu-screen.txt" ).readString() );
    }
}
