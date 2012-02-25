package com.blogspot.steigert.tyrian.domain;

public enum Enemy
{
    MAGNET( null, 25 ),
    PURPLE_TRAIN( null, 50 ),
    WAR_TANK( Shot.BULLET, 75 );

    private final Shot shot;
    private final int score;

    private Enemy(
        Shot shot,
        int score )
    {
        this.shot = shot;
        this.score = score;
    }

    public Shot getShot()
    {
        return shot;
    }

    public int getScore()
    {
        return score;
    }
}
