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
package ca.rmen.android.palidamuerte.app;

import android.content.Context;
import ca.rmen.android.palidamuerte.R;
import ca.rmen.android.palidamuerte.provider.category.CategoryColumns;
import ca.rmen.android.palidamuerte.provider.category.CategoryCursor;
import ca.rmen.android.palidamuerte.provider.category.CategorySelection;

public class Categories {

    public static String getCategoryName(Context context, long categoryId) {
        CategoryCursor cursor = new CategorySelection().id(categoryId).query(context.getContentResolver(), new String[] { CategoryColumns.CATEGORY_NAME });
        try {
            if (!cursor.moveToFirst()) return null;
            String categoryResIdName = cursor.getCategoryName();
            int categoryResId = context.getResources().getIdentifier(categoryResIdName, "string", R.class.getPackage().getName());
            return context.getString(categoryResId);
        } finally {
            cursor.close();
        }
    }

}
