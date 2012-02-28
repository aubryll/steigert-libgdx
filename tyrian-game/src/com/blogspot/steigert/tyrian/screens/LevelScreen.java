package com.blogspot.steigert.tyrian.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.blogspot.steigert.tyrian.Tyrian;

public class LevelScreen
    extends
        AbstractScreen
{
    public LevelScreen(
        Tyrian game )
    {
        super( game );
    }

    @Override
    public void render(
        float delta )
    {
        super.render( delta );

        // return to the profile screen when ESC is pressed
        if( Gdx.input.isKeyPressed( Keys.ESCAPE ) ) {
            game.setScreen( game.getProfileScreen() );
        }
    }
}
