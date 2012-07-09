package com.blogspot.steigert.tyrian.screens;

import com.badlogic.gdx.scenes.scene2d.ActorEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.blogspot.steigert.tyrian.Tyrian;
import com.blogspot.steigert.tyrian.services.SoundManager.TyrianSound;
import com.blogspot.steigert.tyrian.utils.DefaultActorListener;

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
    public void show()
    {
        super.show();

        // retrieve the default table actor
        Table table = super.getTable();
        table.add( "Welcome to Tyrian for Android!" ).spaceBottom( 50 );
        table.row();

        // register the button "start game"
        TextButton startGameButton = new TextButton( "Start game", getSkin() );
        startGameButton.addListener( new DefaultActorListener() {
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
                game.setScreen( new StartGameScreen( game ) );
            }
        } );
        table.add( startGameButton ).size( 300, 60 ).uniform().spaceBottom( 10 );
        table.row();

        // register the button "options"
        TextButton optionsButton = new TextButton( "Options", getSkin() );
        optionsButton.addListener( new DefaultActorListener() {
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
                game.setScreen( new OptionsScreen( game ) );
            }
        } );
        table.add( optionsButton ).uniform().fill().spaceBottom( 10 );
        table.row();

        // register the button "high scores"
        TextButton highScoresButton = new TextButton( "High Scores", getSkin() );
        highScoresButton.addListener( new DefaultActorListener() {
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
                game.setScreen( new HighScoresScreen( game ) );
            }
        } );
        table.add( highScoresButton ).uniform().fill();
    }
}
