package com.blogspot.steigert.tyrian.domain;

import java.util.Locale;

import com.blogspot.steigert.tyrian.utils.TextUtils;

/**
 * The available ship's models.
 */
public enum ShipModel
    implements
        Item
{
    USP_TALON( "USP Talon", 6000, 1 ),
    GENCORE_PHOENIX( "Gencore Phoenix", 12000, 2 ),
    GENCORE_II( "Gencore II", 17000, 3 ),
    MICROSOL_STALKER( "Microsol Stalker", 20000, 4 ),
    SUPER_CARROT( "Super Carrot", 50000, 5 );

    private final String name;
    private final int price;
    private final int firingCapacity;

    private ShipModel(
        String name,
        int price,
        int firingCapacity )
    {
        this.name = name;
        this.price = price;
        this.firingCapacity = firingCapacity;
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
     * Retrieves the firing capacity for this ship model.
     * <p>
     * 1 means 1 shot each 1/4 sec.
     */
    public int getFiringCapacity()
    {
        return firingCapacity;
    }

    @Override
    public String toString()
    {
        return String.format( Locale.US, "%s (%s) - Firing: %d", name, getPriceAsText(),
            firingCapacity );
    }
}
