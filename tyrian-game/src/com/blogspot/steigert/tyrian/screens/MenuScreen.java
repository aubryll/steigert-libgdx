package com.blogspot.steigert.tyrian.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.blogspot.steigert.tyrian.Tyrian;

/**
 * For now the menu screen just writes a message on the center of the screen.
 */
public class MenuScreen
    extends
        AbstractScreen
{
    // setup the dimensions of the menu buttons
    private static final float BUTTON_WIDTH = 300f, BUTTON_HEIGHT = 60f;
    private static final float BUTTON_SPACING = 10f;

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

        final float buttonX = ( width - BUTTON_WIDTH ) / 2;
        float currentY = 280f;

        // button "start game"
        TextButton startGameButton = new TextButton( "Start game", getSkin() );
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
        startGameButton.x = buttonX;
        startGameButton.y = currentY;
        startGameButton.width = BUTTON_WIDTH;
        startGameButton.height = BUTTON_HEIGHT;
        stage.addActor( startGameButton );

        // button "options"
        TextButton optionsButton = new TextButton( "Options", getSkin() );
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
        optionsButton.x = buttonX;
        optionsButton.y = ( currentY -= BUTTON_HEIGHT + BUTTON_SPACING );
        optionsButton.width = BUTTON_WIDTH;
        optionsButton.height = BUTTON_HEIGHT;
        stage.addActor( optionsButton );

        // button "hall of fame"
        TextButton hallOfFameButton = new TextButton( "Hall of Fame", getSkin() );
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
        hallOfFameButton.x = buttonX;
        hallOfFameButton.y = ( currentY -= BUTTON_HEIGHT + BUTTON_SPACING );
        hallOfFameButton.width = BUTTON_WIDTH;
        hallOfFameButton.height = BUTTON_HEIGHT;
        stage.addActor( hallOfFameButton );
    }
}
