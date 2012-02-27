package com.blogspot.steigert.tyrian.domain;

/**
 * A target that gives some credits to the player when captured.
 */
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

    /**
     * Retrieves the credits earned when capturing this reward.
     */
    public int getCredits()
    {
        return credits;
    }
}
