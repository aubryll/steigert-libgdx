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
    PULSE_CANNON( "Pulse-Cannon", 500, Shot.BULLET, "front-gun-pulse" ),
    MISSILE_LAUNCHER( "Missile Launcher", 1000, Shot.MISSILE, "front-gun-missile" ),
    VULCAN_CANNON( "Vulcan Cannon", 2000, Shot.FIREBALL, "front-gun-vulcan" ),
    PROTRON_LAUNCHER( "Protron", 3500, Shot.PROTON, "front-gun-proton" ),
    WAVE_CANNON( "Wave-Cannon", 5000, Shot.WAVE, "front-gun-wave" );

    private final String name;
    private final int price;
    private final Shot shot;
    private final String previewImage;

    private FrontGun(
        String name,
        int price,
        Shot shot,
        String previewImage )
    {
        this.name = name;
        this.price = price;
        this.shot = shot;
        this.previewImage = previewImage;
    }

    public String getName()
    {
        return name;
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

    public String getPreviewImage()
    {
        return previewImage;
    }

    @Override
    public String toString()
    {
        return String.format( Locale.US, "%s (%s) - Damage: %s", name, getPriceAsText(),
            shot.getDamage() );
    }
}
