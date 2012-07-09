package com.blogspot.steigert.tyrian.utils;

import com.badlogic.gdx.scenes.scene2d.ActorEvent;
import com.badlogic.gdx.scenes.scene2d.ActorListener;

/**
 * An utility {@link ActorListener} class.
 * <p>
 * Defines the {@link #touchDown(ActorEvent, float, float, int, int)} method
 * returning <code>true</code> by default, so the
 * {@link #touchDown(ActorEvent, float, float, int, int)} method gets invoked.
 */
public abstract class DefaultActorListener
    extends
        ActorListener
{
    @Override
    public boolean touchDown(
        ActorEvent event,
        float x,
        float y,
        int pointer,
        int button )
    {
        return true;
    }
}
