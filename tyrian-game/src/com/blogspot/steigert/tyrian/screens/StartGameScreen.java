package com.blogspot.steigert.tyrian.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.TableLayout;
import com.blogspot.steigert.tyrian.Tyrian;
import com.blogspot.steigert.tyrian.domain.FrontGun;
import com.blogspot.steigert.tyrian.domain.Level;
import com.blogspot.steigert.tyrian.domain.Profile;
import com.blogspot.steigert.tyrian.domain.Shield;
import com.blogspot.steigert.tyrian.domain.ShipModel;

public class StartGameScreen
    extends
        AbstractScreen
{
    private Table table;

    public StartGameScreen(
        Tyrian game )
    {
        super( game );
    }

    @Override
    public void show()
    {
        super.show();

        // retrieve the custom skin for our 2D widgets
        Skin skin = super.getSkin();

        // create the table actor and add it to the stage
        table = new Table( skin );
        stage.addActor( table );

        // retrieve the table's layout
        TableLayout layout = table.getTableLayout();
        final Profile profile = game.getProfileService().retrieveProfile();
        final Level[] levels = Level.retrieve();

        // create the labels widgets
        TextButton episode1Button = new TextButton( "Episode 1", skin );
        episode1Button.setClickListener( new ClickListener() {
            @Override
            public void click(
                Actor actor,
                float x,
                float y )
            {
                if( profile.getCurrentLevelId() == levels[ 0 ].getId() ) {
                    // start level 0
                } else {
                }
            }
        } );
        layout.register( "episode1Button", episode1Button );

        TextButton episode2Button = new TextButton( "Episode 2", skin );
        episode1Button.setClickListener( new ClickListener() {
            @Override
            public void click(
                Actor actor,
                float x,
                float y )
            {
            }
        } );
        layout.register( "episode2Button", episode2Button );

        TextButton episode3Button = new TextButton( "Episode 3", skin );
        episode1Button.setClickListener( new ClickListener() {
            @Override
            public void click(
                Actor actor,
                float x,
                float y )
            {
            }
        } );
        layout.register( "episode3Button", episode3Button );

        SelectBox shipModelSelectBox = new SelectBox( skin );
        shipModelSelectBox.setItems( ShipModel.values() );
        layout.register( "shipModelSelectBox", shipModelSelectBox );

        SelectBox frontGunSelectBox = new SelectBox( skin );
        frontGunSelectBox.setItems( FrontGun.values() );
        layout.register( "frontGunSelectBox", frontGunSelectBox );

        SelectBox shieldModelSelectBox = new SelectBox( skin );
        shieldModelSelectBox.setItems( Shield.values() );
        layout.register( "shieldModelSelectBox", shieldModelSelectBox );

        String credits = String.valueOf( profile.getCredits() );
        Label creditsLabel = new Label( credits, skin );
        layout.register( "creditsLabel", creditsLabel );

        // register the back button
        TextButton backButton = new TextButton( "Back to main menu", skin );
        backButton.setClickListener( new ClickListener() {
            @Override
            public void click(
                Actor actor,
                float x,
                float y )
            {
                game.setScreen( game.getMenuScreen() );
            }
        } );
        layout.register( "backButton", backButton );

        // finally, parse the layout descriptor
        layout.parse( Gdx.files.internal( "layout-descriptors/start-game-screen.txt" ).readString() );
    }

    @Override
    public void resize(
        int width,
        int height )
    {
        super.resize( width, height );

        // resize the table
        table.width = width;
        table.height = height;

        // we need a complete redraw
        table.invalidateHierarchy();
    }
}
