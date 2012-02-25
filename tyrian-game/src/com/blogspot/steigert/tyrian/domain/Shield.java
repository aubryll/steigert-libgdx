package com.blogspot.steigert.tyrian.domain;

public enum Shield
    implements
        Item
{
    SIF( "Structural Integrity Field", 100 ),
    AIF( "Advanced Integrity Field", 250 ),
    GLES( "Gencore Low Energy Shield", 500 ),
    GHEF( "Gencore High Energy Shield", 1000 ),
    MLXS( "MicroCorp LXS Class A", 2000 );

    private final String name;
    private final int price;

    private Shield(
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
