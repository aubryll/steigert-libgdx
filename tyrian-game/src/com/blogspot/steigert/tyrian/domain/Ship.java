package com.blogspot.steigert.tyrian.domain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;
import com.blogspot.steigert.tyrian.Tyrian;

/**
 * A ship configuration.
 */
public class Ship
    implements
        Serializable
{
    private ShipModel shipModel;
    private FrontGun frontGun;
    private Shield shield;

    public Ship()
    {
    }

    public ShipModel getShipModel()
    {
        return shipModel;
    }

    public FrontGun getFrontGun()
    {
        return frontGun;
    }

    public Shield getShield()
    {
        return shield;
    }

    /**
     * Checks whether the ship contains the given item.
     */
    public boolean contains(
        Item item )
    {
        if( item == null ) return false;
        return ( item.equals( shipModel ) || item.equals( frontGun ) || item.equals( shield ) );
    }

    /**
     * Installs the given item on the ship.
     * <p>
     * No credit verification is done here.
     */
    public void install(
        Item item )
    {
        Gdx.app.log( Tyrian.LOG, "Installing item: " + item );
        if( item instanceof ShipModel ) {
            shipModel = (ShipModel) item;
        } else if( item instanceof FrontGun ) {
            frontGun = (FrontGun) item;
        } else if( item instanceof Shield ) {
            shield = (Shield) item;
        } else {
            throw new IllegalArgumentException( "Unknown item: " + item );
        }
    }

    // Serializable implementation

    @Override
    public void read(
        Json json,
        OrderedMap<String,Object> jsonData )
    {
        shipModel = ShipModel.valueOf( json.readValue( "shipModel", String.class, jsonData ) );
        frontGun = FrontGun.valueOf( json.readValue( "frontGun", String.class, jsonData ) );
        shield = Shield.valueOf( json.readValue( "shield", String.class, jsonData ) );
    }

    @Override
    public void write(
        Json json )
    {
        json.writeValue( "shipModel", shipModel.name() );
        json.writeValue( "frontGun", frontGun.name() );
        json.writeValue( "shield", shield.name() );
    }
}
