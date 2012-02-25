package com.blogspot.steigert.tyrian.domain;

public class Ship
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

    public Item getFrontGun()
    {
        return frontGun;
    }

    public Item getShield()
    {
        return shield;
    }

    public boolean contains(
        Item item )
    {
        if( item == null ) return false;
        return ( item.equals( shipModel ) || item.equals( frontGun ) || item.equals( shield ) );
    }

    public void install(
        Item item )
    {
        if( item instanceof ShipModel ) {
            this.shipModel = (ShipModel) item;
        } else if( item instanceof FrontGun ) {
            this.frontGun = (FrontGun) item;
        } else if( item instanceof Shield ) {
            this.shield = (Shield) item;
        } else {
            throw new IllegalArgumentException( "Unknown item: " + item );
        }
    }
}
