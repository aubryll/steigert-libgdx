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
     * Retrieves a simple representation of the item's name, useful for creating
     * a convention for file names.
     */
    String getSimpleName();

    /**
     * Retrieves the price to acquire this item.
     */
    int getPrice();

    /**
     * Retrieves the price as text.
     */
    String getPriceAsText();
}
