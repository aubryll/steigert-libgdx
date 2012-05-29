package com.blogspot.steigert.tyrian.screens.scene2d;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.blogspot.steigert.tyrian.Tyrian;
import com.blogspot.steigert.tyrian.domain.Ship;
import com.blogspot.steigert.tyrian.screens.AbstractScreen;

/**
 * The ship's 2D representation.
 */
public class Ship2D
    extends
        Image
{
    /**
     * The speed's unit is pixels per second.
     * <p>
     * The divisor is the time in seconds we want the ship to move between the
     * edges of the screen.
     */
    private static final float MAX_HORIZONTAL_SPEED = ( AbstractScreen.GAME_VIEWPORT_WIDTH / 1.5f );
    private static final float MAX_VERTICAL_SPEED = ( AbstractScreen.GAME_VIEWPORT_HEIGHT / 1.0f );

    /**
     * The acceleration is given in pixels per second².
     */
    private static final float MAX_ACCELERATION = 8;

    /**
     * The ship's position.
     */
    private final Vector2 position;

    /**
     * The ship's velocity.
     */
    private final Vector2 velocity;

    /**
     * The ship's acceleration.
     */
    private final Vector2 acceleration;

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
        this.position = new Vector2();
        this.velocity = new Vector2();
        this.acceleration = new Vector2();
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

    /**
     * Sets the ship's initial position.
     */
    public void setInitialPosition(
        float x,
        float y )
    {
        position.set( x, y );
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
                Gdx.app.debug( Tyrian.LOG,
                    Gdx.input.getAccelerometerX() + "," + Gdx.input.getAccelerometerY() + ","
                        + Gdx.input.getAccelerometerZ() );
            }

            // x: 4 (back), 2 (still), 0 (forward)
            // I'll translate the above values to (-2,0,2) so that my next
            // calculations are simpler
            float accelerationX = ( Gdx.input.getAccelerometerX() - 2f );
            if( accelerationX < - 2f ) accelerationX = - 2f;
            else if( accelerationX > 2f ) accelerationX = 2f;

            // y: -2 (left), 0 (still), 2 (right)
            float accelerationY = Gdx.input.getAccelerometerY();
            if( accelerationY < - 2f ) accelerationY = - 2f;
            else if( accelerationY > 2f ) accelerationY = 2f;

            // 2 means 100% of acceleration, so let's adjust the values
            accelerationX /= 2;
            accelerationY /= 2;

            // notice the inverted axis because the game is displayed in
            // landscape mode
            acceleration.x = ( accelerationY * MAX_ACCELERATION );
            acceleration.y = ( - accelerationX * MAX_ACCELERATION );

        } else {

            // calculate the horizontal and vertical acceleration
            acceleration.x = ( Gdx.input.isKeyPressed( Input.Keys.LEFT ) ? - MAX_ACCELERATION
                : ( Gdx.input.isKeyPressed( Input.Keys.RIGHT ) ? MAX_ACCELERATION : 0 ) );
            acceleration.y = ( Gdx.input.isKeyPressed( Input.Keys.UP ) ? MAX_ACCELERATION
                : ( Gdx.input.isKeyPressed( Input.Keys.DOWN ) ? - MAX_ACCELERATION : 0 ) );

            // note that when the keys are not pressed, the acceleration will be
            // zero, so the ship's velocity won't be affected

        }

        // apply the delta on the acceleration
        acceleration.mul( delta );

        // modify the ship's velocity
        velocity.add( acceleration );

        // check the max speed
        if( velocity.x < 0 ) {
            velocity.x = Math.max( velocity.x, - MAX_HORIZONTAL_SPEED * delta );
        } else if( velocity.x > 0 ) {
            velocity.x = Math.min( velocity.x, MAX_HORIZONTAL_SPEED * delta );
        }
        if( velocity.y < 0 ) {
            velocity.y = Math.max( velocity.y, - MAX_VERTICAL_SPEED * delta );
        } else if( velocity.y > 0 ) {
            velocity.y = Math.min( velocity.y, MAX_VERTICAL_SPEED * delta );
        }

        // update the ship's position
        position.add( velocity );

        // make sure the ship is inside the stage
        if( position.x < 0 ) {
            position.x = 0;
            velocity.x = 0;
        } else if( position.x > stage.width() - width ) {
            position.x = stage.width() - width;
            velocity.x = 0;
        }
        if( position.y < 0 ) {
            position.y = 0;
            velocity.y = 0;
        } else if( position.y > stage.height() - height ) {
            position.y = stage.height() - height;
            velocity.y = 0;
        }

        // update the ship's actual position
        x = position.x;
        y = position.y;
    }
}
