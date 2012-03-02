package com.blogspot.steigert.tyrian.domain;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

/**
 * The player's profile.
 */
public class Profile
    implements
        Serializable
{
    private int currentLevelId;
    private int credits;
    private Map<Integer,Integer> levelsHighScores;
    private Ship ship;

    public Profile()
    {
        levelsHighScores = new HashMap<Integer,Integer>();
        levelsHighScores.put( 0, 1000 );
        levelsHighScores.put( 1, 2400 );
        levelsHighScores.put( 2, 5200 );
    }

    /**
     * Retrieves the ID of the next playable level.
     */
    public int getCurrentLevelId()
    {
        return currentLevelId;
    }

    /**
     * Retrieves the high scores for each level (Level-ID -> High score).
     */
    public Map<Integer,Integer> getLevelsHighScores()
    {
        return levelsHighScores;
    }

    /**
     * Retrieves the amount of credits the player has.
     */
    public int getCredits()
    {
        return credits;
    }

    /**
     * Retrieves the current ship configuration.
     */
    public Ship getShip()
    {
        return ship;
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

    // Serializable implementation

    @SuppressWarnings( "unchecked" )
    @Override
    public void read(
        Json json,
        OrderedMap<String,Object> jsonData )
    {
        currentLevelId = json.readValue( "currentLevelId", Integer.class, jsonData );
        credits = json.readValue( "credits", Integer.class, jsonData );
        levelsHighScores = json.readValue( "levelsHighScores", HashMap.class, jsonData );
    }

    @Override
    public void write(
        Json json )
    {
        json.writeValue( "currentLevelId", currentLevelId );
        json.writeValue( "credits", credits );
        json.writeValue( "levelsHighScores", levelsHighScores );
    }
}
