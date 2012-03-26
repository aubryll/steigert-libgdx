package com.blogspot.steigert.tyrian.screens.scene2d;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.blogspot.steigert.tyrian.Tyrian;
import com.blogspot.steigert.tyrian.domain.Ship;

/**
 * The ship's 2D representation.
 */
public class Ship2D
    extends
        Image
{
    /**
     * The speed's unit is pixels per second.
     */
    private static final float MAX_MOVE_SPEED = 250;
    private static final float MAX_MOVE_SPEED_HALF = MAX_MOVE_SPEED / 2;

    /**
     * Creates a new {@link Ship2D}.
     */
    private Ship2D(
        Ship ship,
        List<AtlasRegion> regions )
    {
        // the super constructor does a lot of work
        super( regions.get( 0 ) );

        // set some basic attributes
        this.touchable = false;
    }

    /**
     * Factory method to create a {@link Ship2D}.
     */
    public static Ship2D create(
        Ship ship,
        TextureAtlas textureAtlas )
    {
        List<AtlasRegion> regions = textureAtlas.findRegions( ship.getShipModel().getSimpleName() );
        Ship2D ship2d = new Ship2D( ship, regions );
        return ship2d;
    }

    @Override
    public void act(
        float delta )
    {
        super.act( delta );
        moveShip( delta );
    }

    /**
     * Moves the ship around the screen.
     * <p>
     * Note that the "move speed" is multiplied by the delta time so that the
     * ship moves the configured amount of pixels independently of the current
     * FPS output.
     */
    private void moveShip(
        float delta )
    {
        // check the input and move the ship
        if( Gdx.input.isPeripheralAvailable( Peripheral.Accelerometer ) ) {

            // output the accelerometer axis
            if( Tyrian.DEV_MODE ) {
                Gdx.app.log( Tyrian.LOG,
                    Gdx.input.getAccelerometerX() + " / " + Gdx.input.getAccelerometerY() + " / "
                        + Gdx.input.getAccelerometerZ() );
            }

            // x: 4 (back), 2 (still), 0 (forward)
            // I'll translate these values to: -2, 0, 2
            float adjustedX = ( Gdx.input.getAccelerometerX() - 2f );
            if( adjustedX < - 2f ) adjustedX = - 2f;
            else if( adjustedX > 2f ) adjustedX = 2f;

            // y: -2 (left), 0 (still), 2 (right)
            float adjustedY = Gdx.input.getAccelerometerY();
            if( adjustedY < - 2f ) adjustedY = - 2f;
            else if( adjustedY > 2f ) adjustedY = 2f;

            // the player may be looking down (1) or looking up (2)
            // 1: standing, sitted, laying down facing down
            // 2: laying down facing up
            if( Gdx.input.getAccelerometerZ() > 0 ) {
                adjustedX = - adjustedX;
            } else {
                adjustedY = - adjustedY;
            }

            // notice the inverted axis because the game is displayed in
            // landscape mode
            x += ( adjustedY * MAX_MOVE_SPEED_HALF * delta );
            y += ( adjustedX * MAX_MOVE_SPEED_HALF * delta );

        } else {
            if( Gdx.input.isKeyPressed( Input.Keys.UP ) ) y += ( MAX_MOVE_SPEED * delta );
            else if( Gdx.input.isKeyPressed( Input.Keys.DOWN ) ) y -= ( MAX_MOVE_SPEED * delta );
            if( Gdx.input.isKeyPressed( Input.Keys.LEFT ) ) x -= ( MAX_MOVE_SPEED * delta );
            else if( Gdx.input.isKeyPressed( Input.Keys.RIGHT ) ) x += ( MAX_MOVE_SPEED * delta );
        }

        // make sure the ship is inside the stage
        if( x < 0 ) x = 0;
        else if( x > stage.width() - width ) x = stage.width() - width;
        if( y < 0 ) y = 0;
        else if( y > stage.height() - height ) y = stage.height() - height;
    }
}
