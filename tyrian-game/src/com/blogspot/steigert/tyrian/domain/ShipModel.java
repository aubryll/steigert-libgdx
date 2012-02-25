package com.blogspot.steigert.tyrian.domain;

public enum ShipModel
    implements
        Item
{
    USP_TALON( "USP Talon", 6000 ),
    USP_FANG( "USP Fang", 8000 ),
    GENCORE_PHOENIX( "Gencore Phoenix", 12000 ),
    GENCORE_MAELSTROM( "Gencore Maelstrom", 15000 ),
    MICROSOL_STALKER( "Microsol Stalker", 20000 );

    private final String name;
    private final int price;

    private ShipModel(
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
