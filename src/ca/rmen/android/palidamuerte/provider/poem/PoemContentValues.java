/*
 * This file was generated by the Android ContentProvider Generator: https://github.com/BoD/android-contentprovider-generator
 */
package ca.rmen.android.palidamuerte.provider.poem;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;

import ca.rmen.android.palidamuerte.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code poem} table.
 */
public class PoemContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return PoemColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, PoemSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public PoemContentValues putPoemTypeId(long value) {
        mContentValues.put(PoemColumns.POEM_TYPE_ID, value);
        return this;
    }



    public PoemContentValues putPoemNumber(Integer value) {
        mContentValues.put(PoemColumns.POEM_NUMBER, value);
        return this;
    }

    public PoemContentValues putPoemNumberNull() {
        mContentValues.putNull(PoemColumns.POEM_NUMBER);
        return this;
    }


    public PoemContentValues putSeriesId(long value) {
        mContentValues.put(PoemColumns.SERIES_ID, value);
        return this;
    }



    public PoemContentValues putCategoryId(long value) {
        mContentValues.put(PoemColumns.CATEGORY_ID, value);
        return this;
    }



    public PoemContentValues putLocation(String value) {
        if (value == null) throw new IllegalArgumentException("value for location must not be null");
        mContentValues.put(PoemColumns.LOCATION, value);
        return this;
    }



    public PoemContentValues putYear(int value) {
        mContentValues.put(PoemColumns.YEAR, value);
        return this;
    }



    public PoemContentValues putMonth(int value) {
        mContentValues.put(PoemColumns.MONTH, value);
        return this;
    }



    public PoemContentValues putDay(int value) {
        mContentValues.put(PoemColumns.DAY, value);
        return this;
    }



    public PoemContentValues putTitle(String value) {
        if (value == null) throw new IllegalArgumentException("value for title must not be null");
        mContentValues.put(PoemColumns.TITLE, value);
        return this;
    }



    public PoemContentValues putPreContent(String value) {
        mContentValues.put(PoemColumns.PRE_CONTENT, value);
        return this;
    }

    public PoemContentValues putPreContentNull() {
        mContentValues.putNull(PoemColumns.PRE_CONTENT);
        return this;
    }


    public PoemContentValues putContent(String value) {
        if (value == null) throw new IllegalArgumentException("value for content must not be null");
        mContentValues.put(PoemColumns.CONTENT, value);
        return this;
    }


}
