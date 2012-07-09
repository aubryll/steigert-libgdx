package com.blogspot.steigert.tyrian.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ActorEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.blogspot.steigert.tyrian.Tyrian;
import com.blogspot.steigert.tyrian.domain.FrontGun;
import com.blogspot.steigert.tyrian.domain.Item;
import com.blogspot.steigert.tyrian.domain.Profile;
import com.blogspot.steigert.tyrian.domain.Shield;
import com.blogspot.steigert.tyrian.domain.Ship;
import com.blogspot.steigert.tyrian.domain.ShipModel;
import com.blogspot.steigert.tyrian.services.MusicManager.TyrianMusic;
import com.blogspot.steigert.tyrian.services.SoundManager.TyrianSound;
import com.blogspot.steigert.tyrian.utils.DefaultActorListener;

public class StartGameScreen
    extends
        AbstractScreen
{
    private Profile profile;
    private Ship ship;

    private TextButton episode1Button;
    private TextButton episode2Button;
    private TextButton episode3Button;
    private SelectBox shipModelSelectBox;
    private SelectBox frontGunSelectBox;
    private SelectBox shieldSelectBox;
    private Label creditsLabel;

    private Image shipModelImage;
    private Image frontGunImage;
    private Image shieldImage;

    private LevelClickListener levelClickListener;
    private ItemSelectionListener itemSelectionListener;

    public StartGameScreen(
        Tyrian game )
    {
        super( game );

        // create the listeners
        levelClickListener = new LevelClickListener();
        itemSelectionListener = new ItemSelectionListener();
    }

    @Override
    public void show()
    {
        super.show();

        // start playing the menu music (the player might be returning from the
        // level screen)
        game.getMusicManager().play( TyrianMusic.MENU );

        // retrieve the default table actor
        Table table = super.getTable();
        table.defaults().spaceBottom( 20 );
        table.columnDefaults( 0 ).padRight( 20 );
        table.columnDefaults( 4 ).padLeft( 10 );
        table.add( "Start Game" ).colspan( 5 );

        // retrieve the table's layout
        profile = game.getProfileManager().retrieveProfile();
        ship = profile.getShip();

        // create the level buttons
        table.row();
        table.add( "Episodes" );

        episode1Button = new TextButton( "Episode 1", getSkin() );
        episode1Button.addListener( levelClickListener );
        table.add( episode1Button ).fillX().padRight( 10 );

        episode2Button = new TextButton( "Episode 2", getSkin() );
        episode2Button.addListener( levelClickListener );
        table.add( episode2Button ).fillX().padRight( 10 );

        episode3Button = new TextButton( "Episode 3", getSkin() );
        episode3Button.addListener( levelClickListener );
        table.add( episode3Button ).fillX();

        // create the item select boxes
        shipModelSelectBox = new SelectBox( ShipModel.values(), getSkin() );
        shipModelSelectBox.addListener( itemSelectionListener );
        shipModelImage = new Image();

        table.row();
        table.add( "Ship model" );
        table.add( shipModelSelectBox ).fillX().colspan( 3 );
        table.add( shipModelImage );

        frontGunSelectBox = new SelectBox( FrontGun.values(), getSkin() );
        frontGunSelectBox.addListener( itemSelectionListener );
        frontGunImage = new Image();

        table.row();
        table.add( "Front gun" );
        table.add( frontGunSelectBox ).fillX().colspan( 3 );
        table.add( frontGunImage );

        shieldSelectBox = new SelectBox( Shield.values(), getSkin() );
        shieldSelectBox.addListener( itemSelectionListener );
        shieldImage = new Image();

        table.row();
        table.add( "Shield" );
        table.add( shieldSelectBox ).fillX().colspan( 3 );
        table.add( shieldImage );

        // create the credits label
        creditsLabel = new Label( profile.getCreditsAsText(), getSkin() );
        table.row();
        table.add( "Credits" );
        table.add( creditsLabel ).left().colspan( 4 );

        // register the back button
        TextButton backButton = new TextButton( "Back to main menu", getSkin() );
        backButton.addListener( new DefaultActorListener() {
            public void touchUp(
                ActorEvent event,
                float x,
                float y,
                int pointer,
                int button )
            {
                game.getSoundManager().play( TyrianSound.CLICK );
                game.setScreen( new MenuScreen( game ) );
            }
        } );
        table.row();
        table.add( backButton ).size( 250, 60 ).colspan( 5 );

        // set the select boxes' initial values
        updateValues();
    }

    private void updateValues()
    {
        // select boxes
        shipModelSelectBox.setSelection( ship.getShipModel().ordinal() );
        frontGunSelectBox.setSelection( ship.getFrontGun().ordinal() );
        shieldSelectBox.setSelection( ship.getShield().ordinal() );

        // drawables
        String prefix = "start-game-screen/";
        TextureRegion shipModel = getAtlas().findRegion(
            prefix + ship.getShipModel().getSimpleName() );
        TextureRegion frontGun = getAtlas().findRegion( prefix + ship.getFrontGun().getSimpleName() );
        TextureRegion shield = getAtlas().findRegion( prefix + ship.getShield().getSimpleName() );

        // images
        shipModelImage.setDrawable( new TextureRegionDrawable( shipModel ) );
        frontGunImage.setDrawable( new TextureRegionDrawable( frontGun ) );
        shieldImage.setDrawable( new TextureRegionDrawable( shield ) );
    }

    /**
     * Listener for all the level buttons.
     */
    private class LevelClickListener
        extends
            DefaultActorListener
    {
        @Override
        public void touchUp(
            ActorEvent event,
            float x,
            float y,
            int pointer,
            int button )
        {
            super.touchUp( event, x, y, pointer, button );
            game.getSoundManager().play( TyrianSound.CLICK );

            // find the target level ID
            int targetLevelId = - 1;
            Actor actor = event.getListenerActor();
            if( actor == episode1Button ) {
                targetLevelId = 0;
            } else if( actor == episode2Button ) {
                targetLevelId = 1;
            } else if( actor == episode3Button ) {
                targetLevelId = 2;
            } else {
                return;
            }

            // check the current level ID
            if( profile.getCurrentLevelId() >= targetLevelId ) {
                Gdx.app.log( Tyrian.LOG, "Starting level: " + targetLevelId );
                game.setScreen( new LevelScreen( game, targetLevelId ) );
            } else {
                Gdx.app.log( Tyrian.LOG, "Unable to start level: " + targetLevelId );
            }
        }
    }

    /**
     * Listener for all the item select boxes.
     */
    private class ItemSelectionListener
        extends
            ChangeListener
    {
        @Override
        public void changed(
            ChangeEvent event,
            Actor actor )
        {
            // find the selected item
            Item selectedItem = null;
            int selectedIndex = ( (SelectBox) actor ).getSelectionIndex();
            if( actor == shipModelSelectBox ) {
                selectedItem = ShipModel.values()[ selectedIndex ];
            } else if( actor == frontGunSelectBox ) {
                selectedItem = FrontGun.values()[ selectedIndex ];
            } else if( actor == shieldSelectBox ) {
                selectedItem = Shield.values()[ selectedIndex ];
            } else {
                return;
            }

            // if the ship already contains the item, there is no need to buy it
            if( ship.contains( selectedItem ) ) return;

            // try and buy the item
            if( profile.buy( selectedItem ) ) {
                creditsLabel.setText( profile.getCreditsAsText() );
            }

            // update the widgets
            updateValues();
        }
    }
}
