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

import android.text.Html;
import android.text.TextUtils;
import ca.rmen.android.palidamuerte.provider.poem.PoemColumns;
import ca.rmen.android.palidamuerte.provider.poem.PoemSelection;

public class Search {

    private static final String ACCENTS = "����������";
    private static final String NO_ACCENTS = "aeiounaeioun";
    private static final int SEARCH_CONTEXT_SIZE = 100;
    private static final String[] SEARCH_COLUMNS = new String[] { PoemColumns.YEAR, PoemColumns.LOCATION, PoemColumns.TITLE, PoemColumns.PRE_CONTENT,
            PoemColumns.CONTENT };

    public static String[] getSearchTerms(String queryString) {
        queryString = queryString.trim();
        return TextUtils.split(queryString, " ");
    }

    public static PoemSelection buildSelection(String searchQuery) {
        String[] searchTerms = getSearchTerms(searchQuery);
        for (int i = 0; i < searchTerms.length; i++)
            searchTerms[i] = collateText(searchTerms[i]);

        // Surround searchTerms with % to use with the LIKE query
        for (int i = 0; i < searchTerms.length; i++)
            searchTerms[i] = "%" + searchTerms[i] + "%";

        PoemSelection poemSelection = new PoemSelection();

        for (int i = 0; i < SEARCH_COLUMNS.length; i++) {
            for (int j = 0; j < searchTerms.length; j++) {
                poemSelection.addRaw(collateColumn(SEARCH_COLUMNS[i]) + " LIKE ?", new String[] { searchTerms[j] });
                if (j < searchTerms.length - 1) poemSelection.or();
            }
            if (i < SEARCH_COLUMNS.length - 1) poemSelection.or();
        }

        return poemSelection;
    }

    private static String collateText(String text) {
        text = text.toLowerCase();
        for (int j = 0; j < ACCENTS.length(); j++)
            text = text.replace(ACCENTS.charAt(j), NO_ACCENTS.charAt(j));
        return text;
    }

    private static String collateColumn(String columnName) {
        String result = " LOWER(" + columnName + ")";
        for (int i = 0; i < ACCENTS.length(); i++)
            result = "replace(" + result + ",'" + ACCENTS.charAt(i) + "','" + NO_ACCENTS.charAt(i) + "')";
        return result;
    }

    public static CharSequence findContext(String content, String[] searchTerms) {
        String collatedContent = collateText(content);
        for (String searchTerm : searchTerms) {
            String collatedSearchTerm = collateText(searchTerm);
            CharSequence context = findContext(content, collatedContent, collatedSearchTerm);
            if (context != null) return context;
        }
        return null;
    }

    private static CharSequence findContext(String originalContent, String collatedContent, String collatedSearchTerm) {
        int i = collatedContent.indexOf(collatedSearchTerm);
        if (i < 0) return null;
        String result = "<b>" + originalContent.substring(i, i + collatedSearchTerm.length()) + "</b>";
        int begin = Math.max(0, i - SEARCH_CONTEXT_SIZE / 2);
        int end = Math.min(i + collatedSearchTerm.length() + SEARCH_CONTEXT_SIZE / 2, collatedContent.length());
        result = originalContent.substring(begin, i) + result + originalContent.substring(i + collatedSearchTerm.length(), end);
        if (begin > 0) result = "..." + result;
        if (end < collatedContent.length()) result = result + "...";
        result = result.replace("\n", " ");
        result = result.replace("\r", " ");
        result = result.replaceAll("  ", " ");
        return Html.fromHtml(result);
    }

}
