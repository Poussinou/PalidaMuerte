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

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import ca.rmen.android.palidamuerte.Constants;
import ca.rmen.android.palidamuerte.MusicPlayer;
import ca.rmen.android.palidamuerte.R;
import ca.rmen.android.palidamuerte.app.about.AboutActivity;
import ca.rmen.android.palidamuerte.ui.ActionBar;

public class CategoriesActivity extends FragmentActivity { // NO_UCD (use default)

    private static final String TAG = Constants.TAG + CategoriesActivity.class.getSimpleName();
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        setContentView(R.layout.activity_categories);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        getActionBar().setCustomView(R.layout.actionbar_title);
        getActionBar().setDisplayShowTitleEnabled(false);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setDisplayShowCustomEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.v(TAG, "onCreateOptionsMenu");
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_categories, menu);
        ActionBar.updateMusicMenuItem(this, menu.findItem(R.id.action_music));
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.v(TAG, "onOptionsItemSelected");
        if (item.getItemId() == R.id.action_music) {
            MusicPlayer.getInstance(this).toggle();
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    invalidateOptionsMenu();
                }
            }, 200);
            return true;
        } else if (item.getItemId() == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.v(TAG, "onPrepareOptionsMenu");
        return super.onPrepareOptionsMenu(menu);
    }

}
