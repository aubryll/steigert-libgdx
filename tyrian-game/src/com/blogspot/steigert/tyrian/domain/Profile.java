package com.blogspot.steigert.tyrian.domain;

/**
 * The player's profile.
 */
public class Profile
{
    private Ship ship;
    private Level currentLevel;
    private int credits;

    public Profile()
    {
    }

    /**
     * Retrieves the current ship configuration.
     */
    public Ship getShip()
    {
        return ship;
    }

    /**
     * Retrieves the next playable level.
     */
    public Level getCurrentLevel()
    {
        return currentLevel;
    }

    /**
     * Retrieves the amount of credits the player has.
     */
    public int getCredits()
    {
        return credits;
    }

    /**
     * Checks whether the given item can be bought.
     */
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

    /**
     * Buys the given item.
     */
    public void buy(
        Item item )
    {
        if( canBuy( item ) ) {
            credits -= item.getPrice();
            ship.install( item );
        }
    }
}
