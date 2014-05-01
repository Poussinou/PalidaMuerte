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
package ca.rmen.android.palidamuerte.app.poem.detail;

import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.widget.ShareActionProvider;
import ca.rmen.android.palidamuerte.R;
import ca.rmen.android.palidamuerte.provider.poem.PoemCursor;
import ca.rmen.android.palidamuerte.provider.poem.PoemSelection;

public class Poems {

    public static void updateShareIntent(final ShareActionProvider shareActionProvider, final Context context, final long poemId) {
        new AsyncTask<Void, Void, Intent>() {

            @Override
            protected Intent doInBackground(Void... params) {
                PoemCursor cursor = new PoemSelection().id(poemId).query(context.getContentResolver());
                try {
                    if (!cursor.moveToFirst()) return null;
                    String subject = cursor.getTitle();
                    StringBuilder bodyBuilder = new StringBuilder();
                    bodyBuilder.append(cursor.getTitle()).append("\n\n");
                    String preContent = cursor.getPreContent();
                    if (!TextUtils.isEmpty(preContent)) bodyBuilder.append(preContent).append("\n\n");
                    bodyBuilder.append(cursor.getContent()).append("\n\n");
                    String poemNumberString = getPoemNumberString(context, cursor);
                    if (!TextUtils.isEmpty(poemNumberString)) bodyBuilder.append(poemNumberString).append("\n\n");
                    bodyBuilder.append(context.getString(R.string.copyright)).append("\n\n");
                    bodyBuilder.append(getLocationDateString(context, cursor));
                    String body = bodyBuilder.toString();
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.setType("text/plain");
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, body);
                    return sendIntent;

                } finally {
                    cursor.close();
                }
            }

            @Override
            protected void onPostExecute(Intent result) {
                shareActionProvider.setShareIntent(result);
            }

        }.execute();
    }

    static String getLocationDateString(Context context, PoemCursor poemCursor) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, poemCursor.getYear());
        calendar.set(Calendar.MONTH, poemCursor.getMonth() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, poemCursor.getDay());
        String dateString = DateUtils.formatDateTime(context, calendar.getTimeInMillis(), DateUtils.FORMAT_SHOW_DATE);
        String locationDateString = String.format("%s, %s", poemCursor.getLocation(), dateString);
        return locationDateString;
    }

    static String getPoemNumberString(Context context, PoemCursor poemCursor) {
        Integer poemNumber = poemCursor.getPoemNumber();
        String poemTypeAndNumber = "";
        if (poemNumber != null) {
            String poemTypeName = PoemTypes.getPoemTypeName(context, poemCursor.getPoemTypeId());
            poemTypeAndNumber = context.getString(R.string.poem_type_and_number, poemTypeName, poemNumber);
        }
        return poemTypeAndNumber;
    }
}
