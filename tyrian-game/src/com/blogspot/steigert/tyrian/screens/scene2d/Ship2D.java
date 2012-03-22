package com.blogspot.steigert.tyrian.screens.scene2d;

import java.util.List;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

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
    private static final float MAX_MOVE_SPEED = 200;

    private final Ship ship;
    private final List<AtlasRegion> regions;

    /**
     * Creates a new {@link Ship2D}.
     */
    private Ship2D(
        Ship ship,
        List<AtlasRegion> regions )
    {
        // the super constructor does a lot of work
        super( regions.get( 0 ) );

        // set the basic attributes
        this.ship = ship;
        this.touchable = false;
        this.regions = regions;
    }

    // Main operations

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
     * Note that the "move speed" is multiplied by the delta time so that the ship moves the configured amount of pixels
     * independently of the current FPS output.
     */
    private void moveShip(
        float delta )
    {
        Gdx.app.log( Tyrian.LOG,
            String.format( "%f %f %f", Gdx.input.getAccelerometerX(), Gdx.input.getAccelerometerY(), Gdx.input.getAccelerometerZ() ) );

        // check the input and move the ship
        if( Gdx.input.isPeripheralAvailable( Peripheral.Accelerometer ) ) {

            float accelerometerX = Gdx.input.getAccelerometerX();
            float adjustedVerticalSpeed = 0f;
            if( accelerometerX <= 5f ) adjustedVerticalSpeed = MAX_MOVE_SPEED;
            else if( accelerometerX >= 9f ) adjustedVerticalSpeed = - MAX_MOVE_SPEED;
            else adjustedVerticalSpeed = ( - 100 * accelerometerX + 700 );
            y += adjustedVerticalSpeed * delta;

        } else {
            // with the keyboard it's easier
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
}
