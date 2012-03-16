package com.blogspot.steigert.tyrian.domain;

import java.util.Locale;

import com.blogspot.steigert.tyrian.utils.TextUtils;

/**
 * The available ship's front-guns.
 */
public enum FrontGun
    implements
        Item
{
    PULSE_CANNON( "Pulse-Cannon", 500, Shot.BULLET ),
    MISSILE_LAUNCHER( "Missile Launcher", 1000, Shot.MISSILE ),
    VULCAN_CANNON( "Vulcan Cannon", 2000, Shot.FIREBALL ),
    PROTON_LAUNCHER( "Proton Launcher", 3500, Shot.PROTON ),
    WAVE_CANNON( "Wave-Cannon", 5000, Shot.WAVE );

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

    @Override
    public String getSimpleName()
    {
        return "front-gun-" + name().replaceAll( "_", "-" ).toLowerCase();
    }

    public int getPrice()
    {
        return price;
    }

    @Override
    public String getPriceAsText()
    {
        return TextUtils.creditStyle( price );
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
        return String.format( Locale.US, "%s (%s) - Damage: %s", name, getPriceAsText(),
            shot.getDamage() );
    }
}
