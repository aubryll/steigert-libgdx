package com.blogspot.steigert.tyrian.utils;

import com.badlogic.gdx.math.Vector2;

public class VectorUtils
{
    /**
     * Checks if the vector's X coordinate is inside the range [min,max],
     * adjusting it if needed.
     * <p>
     * Returns <code>true</code> if the value was adjusted, <code>false</code>
     * otherwise.
     */
    public static boolean checkX(
        Vector2 vector,
        float min,
        float max )
    {
        if( vector.x < min ) {
            vector.x = min;
            return true;
        } else if( vector.x > max ) {
            vector.x = max;
            return true;
        }
        return false;
    }

    /**
     * Checks if the vector's Y coordinate is inside the range [min,max],
     * adjusting it if needed.
     * <p>
     * Returns <code>true</code> if the value was adjusted, <code>false</code>
     * otherwise.
     */
    public static boolean checkY(
        Vector2 vector,
        float min,
        float max )
    {
        if( vector.y < min ) {
            vector.y = min;
            return true;
        } else if( vector.y > max ) {
            vector.y = max;
            return true;
        }
        return false;
    }

    /**
     * Checks if the vector's coordinates are inside the range [xMin,xMax] and
     * [yMin,yMax], adjusting them if needed.
     * <p>
     * Returns <code>true</code> if at least one of the values was adjusted,
     * <code>false</code> otherwise.
     */
    public static boolean checkXY(
        Vector2 vector,
        float xMin,
        float xMax,
        float yMin,
        float yMax )
    {
        boolean modified = false;
        if( checkX( vector, xMin, xMax ) ) modified = true;
        if( checkY( vector, yMin, yMax ) ) modified = true;
        return modified;
    }

    /**
     * Checks if both the vector's coordinates are inside the range [min,max],
     * adjusting them if needed.
     * <p>
     * Returns <code>true</code> if at least one of the values was adjusted,
     * <code>false</code> otherwise.
     */
    public static boolean checkXY(
        Vector2 vector,
        float min,
        float max )
    {
        return checkXY( vector, min, max, min, max );
    }

    static final String TAG = VectorUtils.class.getSimpleName();
}
