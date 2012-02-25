package com.blogspot.steigert.tyrian.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.blogspot.steigert.tyrian.Tyrian;

/**
 * For now the menu screen just writes a message on the center of the screen.
 */
public class MenuScreen
    extends
        AbstractScreen
{
    private String message = "Welcome to the great menu screen!";
    private float x, y;

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

        // calculate the center point for the message
        TextBounds bounds = font.getBounds( message );
        x = ( width - bounds.width ) / 2;
        y = ( height - bounds.height ) / 2;
    }

    @Override
    public void render(
        float delta )
    {
        super.render( delta );

        // draw the message
        batch.begin();
        font.draw( batch, message, x, y );
        batch.end();
    }
}
