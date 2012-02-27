package com.blogspot.steigert.tyrian.domain;

/**
 * A shield for the ship.
 */
public enum Shield
    implements
        Item
{
    SIF( "Structural Integrity Field", 100, 1 ),
    AIF( "Advanced Integrity Field", 250, 2 ),
    GLES( "Gencore Low Energy Shield", 500, 3 ),
    GHEF( "Gencore High Energy Shield", 1000, 4 ),
    MLXS( "MicroCorp LXS Class A", 2000, 5 );

    private final String name;
    private final int price;
    private final int armor;

    private Shield(
        String name,
        int price,
        int armor )
    {
        this.name = name;
        this.price = price;
        this.armor = armor;
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
     * Retrieves the armor level for this shield (1-5).
     * <p>
     * 1 means 10% less damage received.
     */
    public int getArmor()
    {
        return armor;
    }
}
