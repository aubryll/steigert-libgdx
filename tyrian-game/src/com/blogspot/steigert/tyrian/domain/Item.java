package com.blogspot.steigert.tyrian.domain;

/**
 * An item that can be added to the ship.
 */
public interface Item
{
    /**
     * Retrieves the name of this item.
     */
    String getName();

    /**
     * Retrieves the price to acquire this item.
     */
    int getPrice();

    /**
     * Retrieves the price as text.
     */
    String getPriceAsText();
}
