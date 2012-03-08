package com.blogspot.steigert.tyrian.domain;

import java.util.Locale;

/**
 * The available ship's models.
 */
public enum ShipModel
    implements
        Item
{
    USP_TALON( "USP Talon", 6000, 1 ),
    USP_FANG( "USP Fang", 8000, 2 ),
    GENCORE_PHOENIX( "Gencore Phoenix", 12000, 3 ),
    GENCORE_MAELSTROM( "Gencore Maelstrom", 15000, 4 ),
    MICROSOL_STALKER( "Microsol Stalker", 20000, 5 );

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
        return String.format( Locale.US, "%s ($%d) - Firing: %d", name, price, firingCapacity );
    }
}
