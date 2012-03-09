package com.blogspot.steigert.tyrian.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class TextUtils
{
    private static final NumberFormat FORMATTER;

    static {
        FORMATTER = NumberFormat.getCurrencyInstance( Locale.US );
        FORMATTER.setMinimumFractionDigits( 0 );
        FORMATTER.setMaximumFractionDigits( 0 );
    }

    private TextUtils()
    {
    }

    public static String creditStyle(
        int credits )
    {
        return FORMATTER.format( credits );
    }
}
