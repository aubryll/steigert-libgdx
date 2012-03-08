package com.blogspot.steigert.tyrian.domain;

import java.util.Locale;

/**
 * The available ship's front-guns.
 */
public enum FrontGun
    implements
        Item
{
    PULSE_CANNON( "Pulse-Cannon", 500, Shot.BULLET ),
    WAVE_CANNON( "Multi-Cannon", 750, Shot.WAVE ),
    VULCAN_CANNON( "Vulcan Cannon", 600, Shot.FIREBALL ),
    PROTRON_LAUNCHER( "Protron", 600, Shot.PROTON ),
    MISSILE_LAUNCHER( "Missile Launcher", 850, Shot.MISSILE );

    private final String name;
    private final int price;
    private final Shot shot;

    private FrontGun(
        String name,
        int price,
        Shot shot )
    {
        this.name = name;
        this.price = price;
        this.shot = shot;
    }

    public String getName()
    {
        return name;
    }

    public int getPrice()
    {
        return price;
    }

    /**
     * Retrieves the shot fired by this gun.
     */
    public Shot getShot()
    {
        return shot;
    }

    @Override
    public String toString()
    {
        return String.format( Locale.US, "%s ($%d) - Damage: %s", name, price,
            shot.getDamage() );
    }
}
