package com.blogsport.steigert.tyrian;

import android.os.Bundle;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.blogspot.steigert.tyrian.Tyrian;

/**
 * This class simply defines an Android activity for our game.
 */
public class TyrianAndroidLauncher
    extends
        AndroidApplication
{
    @Override
    public void onCreate(
        Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        // prevent the screen from dimming/sleeping
        getWindow().addFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );

        // whether to use OpenGL ES 2.0
        boolean useOpenGLES2 = false;

        // create the game
        initialize( new Tyrian(), useOpenGLES2 );
    }
}