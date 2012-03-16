package com.blogspot.steigert.tyrian.screens.scene2d;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.OnActionCompleted;
import com.badlogic.gdx.scenes.scene2d.actions.FadeIn;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
    private static final int MOVE_SPEED = 250;

    private final Ship ship;
    private final List<AtlasRegion> regions;

    private boolean enabled;

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
     * FPS.
     */
    private void moveShip(
        float delta )
    {
        // check the enabled flag
        if( ! enabled ) return;

        // check the input and move the ship
        if( Gdx.input.isKeyPressed( Input.Keys.UP ) ) y += ( MOVE_SPEED * delta );
        else if( Gdx.input.isKeyPressed( Input.Keys.DOWN ) ) y -= ( MOVE_SPEED * delta );
        if( Gdx.input.isKeyPressed( Input.Keys.LEFT ) ) x -= ( MOVE_SPEED * delta );
        else if( Gdx.input.isKeyPressed( Input.Keys.RIGHT ) ) x += ( MOVE_SPEED * delta );

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
        // create the actor
        List<AtlasRegion> regions = textureAtlas.findRegions( ship.getShipModel().getSimpleName() );
        final Ship2D ship2d = new Ship2D( ship, regions );

        // add a fade-in effect; enable the actor when it completes
        ship2d.color.a = 0f;
        FadeIn fadeInAction = FadeIn.$( 1f );
        fadeInAction.setCompletionListener( new OnActionCompleted() {
            @Override
            public void completed(
                Action action )
            {
                ship2d.enabled = true;
            }
        } );
        ship2d.action( fadeInAction );

        // return the result
        return ship2d;
    }
}
