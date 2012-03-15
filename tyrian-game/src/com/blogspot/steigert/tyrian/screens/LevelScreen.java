package com.blogspot.steigert.tyrian.screens;

import com.blogspot.steigert.tyrian.Tyrian;
import com.blogspot.steigert.tyrian.domain.Level;
import com.blogspot.steigert.tyrian.domain.Profile;
import com.blogspot.steigert.tyrian.screens.scene2d.Ship2D;

public class LevelScreen
    extends
        AbstractScreen
{
    private final Level level;

    private final Profile profile;

    private Ship2D ship2d;

    public LevelScreen(
        Tyrian game,
        int targetLevelId )
    {
        super( game );

        profile = game.getProfileManager().retrieveProfile();
        level = game.getLevelManager().findLevelById( targetLevelId );
    }

    @Override
    public void show()
    {
        super.show();

        ship2d = new Ship2D( profile.getShip(), getAtlas() );
    }
}
