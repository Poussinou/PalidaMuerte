/*
 * This file was generated by the Android ContentProvider Generator: https://github.com/BoD/android-contentprovider-generator
 */
package ca.rmen.android.palidamuerte.provider.series;

import java.util.Date;

import android.database.Cursor;

import ca.rmen.android.palidamuerte.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code series} table.
 */
public class SeriesCursor extends AbstractCursor {
    public SeriesCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code series_name} value.
     * Cannot be {@code null}.
     */
    public String getSeriesName() {
        Integer index = getCachedColumnIndexOrThrow(SeriesColumns.SERIES_NAME);
        return getString(index);
    }
}
