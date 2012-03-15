package com.blogspot.steigert.tyrian.domain;

/**
 * A playable level.
 */
public class Level
{
    private final int id;
    private String name;
    private boolean completed;
    private Level nextLevel;

    public Level(
        int id )
    {
        this.id = id;
    }

    /**
     * Retrieves the ID of this object.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Retrieves the level's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the level's name.
     */
    public void setName(
        String name )
    {
        this.name = name;
    }

    /**
     * Retrieves whether this level is completed.
     */
    public boolean isCompleted()
    {
        return completed;
    }

    /**
     * Sets the level status.
     */
    public void setCompleted(
        boolean completed )
    {
        this.completed = completed;
    }

    /**
     * Retrieves the next level.
     */
    public Level getNextLevel()
    {
        return nextLevel;
    }

    /**
     * Sets the next level.
     */
    public void setNextLevel(
        Level nextLevel )
    {
        this.nextLevel = nextLevel;
    }

    /**
     * Retrieves whether there is a next level.
     */
    public boolean hasNextLevel()
    {
        return ( nextLevel != null );
    }
}
