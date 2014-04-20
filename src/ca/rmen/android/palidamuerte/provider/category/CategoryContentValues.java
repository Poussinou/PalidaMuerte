/*
 * This file was generated by the Android ContentProvider Generator: https://github.com/BoD/android-contentprovider-generator
 */
package ca.rmen.android.palidamuerte.provider.category;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;

import ca.rmen.android.palidamuerte.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code category} table.
 */
public class CategoryContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return CategoryColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     * 
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, CategorySelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public CategoryContentValues putCategoryName(String value) {
        if (value == null) throw new IllegalArgumentException("value for categoryName must not be null");
        mContentValues.put(CategoryColumns.CATEGORY_NAME, value);
        return this;
    }


}
