package com.blogspot.steigert.tyrian.domain;

public enum FrontGun
    implements
        Item
{
    PULSE_CANNON( "Pulse-Cannon", 500 ),
    MULTI_CANNON( "Multi-Cannon", 750 ),
    VULCAN_CANNON( "Vulcan Cannon", 600 ),
    PROTRON( "Protron", 600 ),
    MISSILE_LAUNCHER( "Missile Launcher", 850 );

    private final String name;
    private final int price;

    private FrontGun(
        String name,
        int price )
    {
        this.name = name;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public int getPrice()
    {
        return price;
    }
}
