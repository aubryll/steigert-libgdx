package com.blogspot.steigert.tyrian.domain;

public class Profile
{
    private Ship ship;
    private Level currentLevel;
    private int credits;

    public Profile()
    {
    }

    public Ship getShip()
    {
        return ship;
    }

    public Level getCurrentLevel()
    {
        return currentLevel;
    }

    public int getCredits()
    {
        return credits;
    }

    public boolean canBuy(
        Item item )
    {
        if( ship.contains( item ) ) {
            return false;
        }
        if( item.getPrice() > credits ) {
            return false;
        }
        return true;
    }

    public void buy(
        Item item )
    {
        if( canBuy( item ) ) {
            credits -= item.getPrice();
            ship.install( item );
        }
    }
}
