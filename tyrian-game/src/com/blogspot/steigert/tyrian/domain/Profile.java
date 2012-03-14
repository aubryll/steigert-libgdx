package com.blogspot.steigert.tyrian.domain;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;
import com.blogspot.steigert.tyrian.Tyrian;
import com.blogspot.steigert.tyrian.services.ProfileService;
import com.blogspot.steigert.tyrian.utils.TextUtils;

/**
 * The player's profile.
 * <p>
 * This class is used to store the game progress, and is persisted to the file
 * system when the game exists.
 * 
 * @see ProfileService
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
        credits = 1000;
        highScores = new HashMap<Integer,Integer>();
        ship = new Ship();
        ship.install( ShipModel.USP_TALON );
        ship.install( FrontGun.PULSE_CANNON );
        ship.install( Shield.SIF );
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
     * Notifies the score on the given level. Returns <code>true</code> if its a
     * high score.
     */
    public boolean notifyScore(
        int levelId,
        int score )
    {
        if( score > getHighScore( levelId ) ) {
            highScores.put( levelId, score );
            return true;
        }
        return false;
    }

    /**
     * Retrieves the amount of credits the player has.
     */
    public int getCredits()
    {
        return credits;
    }

    /**
     * Retrieves the amount of credits as text.
     */
    public String getCreditsAsText()
    {
        return TextUtils.creditStyle( credits );
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
    public boolean buy(
        Item item )
    {
        if( canBuy( item ) ) {
            Gdx.app.log( Tyrian.LOG, "Buying item: " + item );
            ship.install( item );
            credits -= item.getPrice();
            Gdx.app.log( Tyrian.LOG, "Credits available: " + credits );
            return true;
        } else {
            Gdx.app.log( Tyrian.LOG, "No credits to buy item: " + item );
            return false;
        }
    }

    // Serializable implementation

    @SuppressWarnings( "unchecked" )
    @Override
    public void read(
        Json json,
        OrderedMap<String,Object> jsonData )
    {
        // read the some basic properties
        currentLevelId = json.readValue( "currentLevelId", Integer.class, jsonData );
        credits = json.readValue( "credits", Integer.class, jsonData );

        // libgdx handles the keys of JSON formatted HashMaps as Strings, but we
        // want it to be an integer instead (levelId)
        Map<String,Integer> highScores = json.readValue( "highScores", HashMap.class,
            Integer.class, jsonData );
        for( String levelIdAsString : highScores.keySet() ) {
            int levelId = Integer.valueOf( levelIdAsString );
            Integer highScore = highScores.get( levelIdAsString );
            this.highScores.put( levelId, highScore );
        }

        // finally, read the ship
        ship = json.readValue( "ship", Ship.class, jsonData );
    }

    @Override
    public void write(
        Json json )
    {
        json.writeValue( "currentLevelId", currentLevelId );
        json.writeValue( "credits", credits );
        json.writeValue( "highScores", highScores );
        json.writeValue( "ship", ship );
    }
}
