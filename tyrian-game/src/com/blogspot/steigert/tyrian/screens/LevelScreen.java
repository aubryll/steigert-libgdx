package com.blogspot.steigert.tyrian.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.blogspot.steigert.tyrian.Tyrian;
import com.blogspot.steigert.tyrian.domain.Level;
import com.blogspot.steigert.tyrian.domain.Profile;
import com.blogspot.steigert.tyrian.screens.scene2d.Ship2D;
import com.blogspot.steigert.tyrian.services.MusicManager.TyrianMusic;

public class LevelScreen
    extends
        AbstractScreen
{
    private final Profile profile;
    private final Level level;

    private Ship2D ship2d;

    public LevelScreen(
        Tyrian game,
        int targetLevelId )
    {
        super( game );

        // set the basic attributes
        profile = game.getProfileManager().retrieveProfile();
        level = game.getLevelManager().findLevelById( targetLevelId );
    }

    @Override
    public void show()
    {
        super.show();

        // play the level music
        game.getMusicManager().play( TyrianMusic.LEVEL );

        // create the ship and add it to the stage
        ship2d = Ship2D.create( profile.getShip(), getAtlas() );
        stage.addActor( ship2d );
    }

    @Override
    public void render(
        float delta )
    {
        super.render( delta );

        // return to the menu if the ESC/BACK key is pressed
        if( Gdx.input.isKeyPressed( Input.Keys.ESCAPE ) || Gdx.input.isKeyPressed( Input.Keys.BACK ) ) {
            game.setScreen( game.getStartGameScreen() );
        }
    }
}
