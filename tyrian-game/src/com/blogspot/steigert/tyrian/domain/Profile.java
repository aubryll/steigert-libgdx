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
    private Map<Integer,Integer> highScores;
    private Ship ship;

    public Profile()
    {
        highScores = new HashMap<Integer,Integer>();
        highScores.put( 0, 1000 );
        highScores.put( 1, 2400 );
        highScores.put( 2, 5200 );
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
    public Map<Integer,Integer> getHighScores()
    {
        return highScores;
    }

    /**
     * Gets the current high score for the given level.
     */
    public int getHighScore(
        int levelId )
    {
        if( highScores == null ) return 0;
        Integer highScore = highScores.get( levelId );
        return ( highScore == null ? 0 : highScore );
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

        // libgdx handles the keys of JSON formatted HashMaps as Strings, but we
        // want it to be an integer instead (levelId)
        Map<String,Integer> highScores = json.readValue( "highScores", HashMap.class,
            Integer.class, jsonData );
        for( String levelIdAsString : highScores.keySet() ) {
            int levelId = Integer.valueOf( levelIdAsString );
            Integer highScore = highScores.get( levelId );
            this.highScores.put( levelId, highScore );
        }
    }

    @Override
    public void write(
        Json json )
    {
        json.writeValue( "currentLevelId", currentLevelId );
        json.writeValue( "credits", credits );
        json.writeValue( "highScores", highScores );
    }
}
