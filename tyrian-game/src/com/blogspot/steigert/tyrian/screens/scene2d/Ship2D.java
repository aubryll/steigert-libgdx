package com.blogspot.steigert.tyrian.screens.scene2d;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.blogspot.steigert.tyrian.domain.Ship;

public class Ship2D
    extends
        Image
{
    private final List<AtlasRegion> regions;

    public Ship2D(
        Ship ship,
        TextureAtlas textureAtlas )
    {
        regions = textureAtlas.findRegions( name );
    }
}
