package com.blogspot.steigert.tyrian.domain;

public enum Shot
{
    BULLET( 1 ),
    MISSILE( 2 ),
    FIREBALL( 3 );

    private final int damage;

    private Shot(
        int damage )
    {
        this.damage = damage;
    }

    public int getDamage()
    {
        return damage;
    }
}
