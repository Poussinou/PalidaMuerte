/**
 * Copyright 2014 Carmen Alvarez
 *
 * This file is part of Pálida Muerte.
 *
 * Pálida Muerte is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * Pálida Muerte is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Pálida Muerte. If not, see <http://www.gnu.org/licenses/>.
 */
package ca.rmen.android.palidamuerte.app.category.list;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import ca.rmen.android.palidamuerte.Constants;
import ca.rmen.android.palidamuerte.R;
import ca.rmen.android.palidamuerte.provider.category.CategoryCursor;
import ca.rmen.android.palidamuerte.ui.Font;
import ca.rmen.android.palidamuerte.ui.ViewHolder;

class CategoriesCursorAdapter extends CursorAdapter {
    private static final String TAG = Constants.TAG + CategoriesCursorAdapter.class.getSimpleName();

    private final Context mContext;

    CategoriesCursorAdapter(Context context) {
        super(context, null, false);
        Log.v(TAG, "Constructor");
        mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = View.inflate(context, R.layout.category_title, null);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        CategoryCursor cursorWrapper = (CategoryCursor) cursor;
        TextView title = ViewHolder.get(view, R.id.title);
        String categoryName = cursorWrapper.getCategoryName();
        int categoryResId = mContext.getResources().getIdentifier(categoryName, "string", R.class.getPackage().getName());
        title.setText(mContext.getString(categoryResId));
        title.setTypeface(Font.getTypeface(mContext));
    }
}
