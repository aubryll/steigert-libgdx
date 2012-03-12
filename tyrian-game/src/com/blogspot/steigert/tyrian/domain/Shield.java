package com.blogspot.steigert.tyrian.domain;

import java.util.Locale;

import com.blogspot.steigert.tyrian.utils.TextUtils;

/**
 * A shield for the ship.
 */
public enum Shield
    implements
        Item
{
    SIF( "Structural Field", 100, 1, "shield-sig" ),
    AIF( "Advanced Field", 250, 2, "shield-aif" ),
    GLES( "Gencore LE Shield", 500, 3, "shield-gles" ),
    GHEF( "Gencore HE Shield", 1000, 4, "shield-ghef" ),
    MLXS( "MicroCorp LXS-A", 2000, 5, "shield-mlxs" );

    private final String name;
    private final int price;
    private final int armor;
    private final String previewImage;

    private Shield(
        String name,
        int price,
        int armor,
        String previewImage )
    {
        this.name = name;
        this.price = price;
        this.armor = armor;
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
     * Retrieves the armor level for this shield (1-5).
     * <p>
     * 1 means 10% less damage received.
     */
    public int getArmor()
    {
        return armor;
    }

    public String getPreviewImage()
    {
        return previewImage;
    }

    @Override
    public String toString()
    {
        return String.format( Locale.US, "%s (%s) - Armor: %d", name, getPriceAsText(), armor );
    }
}
