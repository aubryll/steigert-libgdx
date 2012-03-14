package com.blogspot.steigert.tyrian.screens;

import java.util.Locale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.ValueChangedListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.TableLayout;
import com.blogspot.steigert.tyrian.Tyrian;

/**
 * A simple options screen.
 */
public class OptionsScreen
    extends
        AbstractScreen
{
    private Table table;
    private Label volumeValue;

    public OptionsScreen(
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

        // create the labels widgets
        final CheckBox soundEffectsCheckbox = new CheckBox( skin );
        soundEffectsCheckbox.setChecked( game.getPreferences().isSoundEffectsEnabled() );
        soundEffectsCheckbox.setClickListener( new ClickListener() {
            @Override
            public void click(
                Actor actor,
                float x,
                float y )
            {
                boolean enabled = soundEffectsCheckbox.isChecked();
                game.getPreferences().setSoundEffectsEnabled( enabled );
            }
        } );
        layout.register( "soundEffectsCheckbox", soundEffectsCheckbox );

        final CheckBox musicCheckbox = new CheckBox( skin );
        musicCheckbox.setChecked( game.getPreferences().isMusicEnabled() );
        musicCheckbox.setClickListener( new ClickListener() {
            @Override
            public void click(
                Actor actor,
                float x,
                float y )
            {
                boolean enabled = musicCheckbox.isChecked();
                game.getPreferences().setMusicEnabled( enabled );
            }
        } );
        layout.register( "musicCheckbox", musicCheckbox );

        // range is [0.0,1.0]; step is 0.1f
        Slider volumeSlider = new Slider( 0f, 1f, 0.1f, skin );
        volumeSlider.setValue( game.getPreferences().getVolume() );
        volumeSlider.setValueChangedListener( new ValueChangedListener() {
            @Override
            public void changed(
                Slider slider,
                float value )
            {
                game.getPreferences().setVolume( value );
                game.getMusicManager().setVolume( value );
                updateVolumeLabel();
            }
        } );
        layout.register( "volumeSlider", volumeSlider );

        volumeValue = new Label( skin );
        updateVolumeLabel();
        layout.register( "volumeValue", volumeValue );

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
        layout.parse( Gdx.files.internal( "layout-descriptors/options-screen.txt" ).readString() );
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

    /**
     * Updates the volume label next to the slider.
     */
    private void updateVolumeLabel()
    {
        float volume = ( game.getPreferences().getVolume() * 100 );
        volumeValue.setText( String.format( Locale.US, "%1.0f%%", volume ) );
    }
}
