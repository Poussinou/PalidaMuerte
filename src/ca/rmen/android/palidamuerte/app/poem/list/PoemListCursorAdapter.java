/**
 * Copyright 2014 Carmen Alvarez
 *
 * This file is part of P�lida Muerte.
 *
 * P�lida Muerte is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * P�lida Muerte is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with P�lida Muerte. If not, see <http://www.gnu.org/licenses/>.
 */
package ca.rmen.android.palidamuerte.app.poem.list;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import ca.rmen.android.palidamuerte.Constants;
import ca.rmen.android.palidamuerte.R;
import ca.rmen.android.palidamuerte.provider.poem.PoemCursor;

class PoemListCursorAdapter extends CursorAdapter {

    private static final String TAG = Constants.TAG + PoemListCursorAdapter.class.getSimpleName();
    private final Typeface mFont;

    PoemListCursorAdapter(Context context) {
        super(context, null, false);
        Log.v(TAG, "Constructor");
        mFont = Typeface.createFromAsset(context.getAssets(), "dancing_script.ttf");
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = View.inflate(context, R.layout.poem_title, null);
        fillView(view, cursor);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        fillView(view, cursor);
    }

    private void fillView(View view, Cursor cursor) {
        PoemCursor cursorWrapper = (PoemCursor) cursor;
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(cursorWrapper.getTitle());
        title.setTypeface(mFont);
    }

}