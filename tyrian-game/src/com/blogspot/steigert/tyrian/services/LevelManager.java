package com.blogspot.steigert.tyrian.services;

import java.util.ArrayList;
import java.util.List;

import com.blogspot.steigert.tyrian.domain.Level;

/**
 * Manages the levels.
 */
public class LevelManager
{
    private final List<Level> levels;

    /**
     * Creates the level manager.
     */
    public LevelManager()
    {
        // create the level 2
        Level level2 = new Level( 2 );
        level2.setName( "Episode 3" );

        // create the level 1
        Level level1 = new Level( 1 );
        level1.setName( "Episode 2" );
        level1.setNextLevel( level2 );

        // create the level 0
        Level level0 = new Level( 0 );
        level0.setName( "Episode 1" );
        level0.setNextLevel( level0 );

        // register the levels
        levels = new ArrayList<Level>( 3 );
        levels.add( level0 );
        levels.add( level1 );
        levels.add( level2 );
    }

    /**
     * Retrieve all the available levels.
     */
    public List<Level> getLevels()
    {
        return levels;
    }

    /**
     * Retrieve the level with the given id, or <code>null</code> if no such
     * level exist.
     */
    public Level findLevelById(
        int id )
    {
        if( id < 0 || id >= levels.size() ) {
            return null;
        }
        return levels.get( id );
    }
}
