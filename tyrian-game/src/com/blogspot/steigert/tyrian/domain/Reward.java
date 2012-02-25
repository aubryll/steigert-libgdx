package com.blogspot.steigert.tyrian.domain;

public enum Reward
{
    GRAY_COIN( 10 ),
    RED_COIN( 25 ),
    PURPLE_COIN( 50 );

    private final int credits;

    private Reward(
        int score )
    {
        this.credits = score;
    }

    public int getCredits()
    {
        return credits;
    }
}
