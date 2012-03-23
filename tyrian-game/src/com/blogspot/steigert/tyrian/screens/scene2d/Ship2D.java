package com.blogspot.steigert.tyrian.screens.scene2d;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
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
}
