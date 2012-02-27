package com.blogspot.steigert.tyrian.domain;

/**
 * An static/moving enemy, capable of firing.
 * <p>
 * One shot is enough to kill the enemy.
 */
public enum Enemy
{
    MAGNET( null, 25 ),
    PURPLE_TRAIN( null, 50 ),
    WAR_TANK( Shot.BULLET, 75 );

    private final Shot shot;
    private final int credits;

    private Enemy(
        Shot shot,
        int score )
    {
        this.shot = shot;
        this.credits = score;
    }

    /**
     * Retrieves the shot fired by this enemy.
     */
    public Shot getShot()
    {
        return shot;
    }

    /**
     * Retrieves the credits earned when killing this enemy.
     */
    public int getCredits()
    {
        return credits;
    }
}
