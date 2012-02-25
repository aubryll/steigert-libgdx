package com.blogspot.steigert.tyrian.domain;

/**
 * An item that can be added to the ship.
 */
public interface Item
{
    /**
     * Retrieves the name of the item.
     */
    String getName();

    /**
     * Retrieves the price of the item.
     */
    int getPrice();
}
