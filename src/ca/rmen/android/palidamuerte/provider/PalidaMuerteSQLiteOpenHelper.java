/*
 * This file was generated by the Android ContentProvider Generator: https://github.com/BoD/android-contentprovider-generator
 */
package ca.rmen.android.palidamuerte.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import ca.rmen.android.palidamuerte.BuildConfig;
import ca.rmen.android.palidamuerte.provider.category.CategoryColumns;
import ca.rmen.android.palidamuerte.provider.poem.PoemColumns;
import ca.rmen.android.palidamuerte.provider.poem_type.PoemTypeColumns;
import ca.rmen.android.palidamuerte.provider.series.SeriesColumns;

public class PalidaMuerteSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = PalidaMuerteSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "palida_muerte.db";
    private static final int DATABASE_VERSION = 3;
    private final Context mContext;
    private final PalidaMuerteSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    private static final String SQL_CREATE_TABLE_CATEGORY = "CREATE TABLE IF NOT EXISTS "
            + CategoryColumns.TABLE_NAME + " ( "
            + CategoryColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CategoryColumns.CATEGORY_NAME + " TEXT NOT NULL "
            + ", CONSTRAINT UNIQUE_CATEGORY_NAME UNIQUE (CATEGORY_NAME) ON CONFLICT REPLACE"
            + " );";

    private static final String SQL_CREATE_TABLE_POEM = "CREATE TABLE IF NOT EXISTS "
            + PoemColumns.TABLE_NAME + " ( "
            + PoemColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PoemColumns.POEM_TYPE_ID + " INTEGER NOT NULL, "
            + PoemColumns.POEM_NUMBER + " INTEGER, "
            + PoemColumns.SERIES_ID + " INTEGER NOT NULL, "
            + PoemColumns.CATEGORY_ID + " INTEGER NOT NULL, "
            + PoemColumns.LOCATION + " TEXT NOT NULL, "
            + PoemColumns.YEAR + " INTEGER NOT NULL, "
            + PoemColumns.MONTH + " INTEGER NOT NULL, "
            + PoemColumns.DAY + " INTEGER NOT NULL, "
            + PoemColumns.TITLE + " TEXT NOT NULL, "
            + PoemColumns.PRE_CONTENT + " TEXT, "
            + PoemColumns.CONTENT + " TEXT NOT NULL, "
            + PoemColumns.IS_FAVORITE + " INTEGER NOT NULL "
            + ", CONSTRAINT FK_POEM_TYPE_ID FOREIGN KEY (POEM_TYPE_ID) REFERENCES POEM_TYPE (_ID) ON DELETE CASCADE"
            + ", CONSTRAINT FK_SERIES_ID FOREIGN KEY (SERIES_ID) REFERENCES SERIES (_ID) ON DELETE CASCADE"
            + ", CONSTRAINT FK_CATEGORY_ID FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY (_ID) ON DELETE CASCADE"
            + " );";

    private static final String SQL_CREATE_TABLE_POEM_TYPE = "CREATE TABLE IF NOT EXISTS "
            + PoemTypeColumns.TABLE_NAME + " ( "
            + PoemTypeColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PoemTypeColumns.POEM_TYPE_NAME + " TEXT NOT NULL "
            + ", CONSTRAINT UNIQUE_POEM_TYPE_NAME UNIQUE (POEM_TYPE_NAME) ON CONFLICT REPLACE"
            + " );";

    private static final String SQL_CREATE_TABLE_SERIES = "CREATE TABLE IF NOT EXISTS "
            + SeriesColumns.TABLE_NAME + " ( "
            + SeriesColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SeriesColumns.SERIES_NAME + " TEXT NOT NULL "
            + ", CONSTRAINT UNIQUE_SERIES_NAME UNIQUE (SERIES_NAME) ON CONFLICT REPLACE"
            + " );";

    // @formatter:on

    public static PalidaMuerteSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */

    private static PalidaMuerteSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new PalidaMuerteSQLiteOpenHelper(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
    }

    private PalidaMuerteSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
        mOpenHelperCallbacks = new PalidaMuerteSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static PalidaMuerteSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new PalidaMuerteSQLiteOpenHelper(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private PalidaMuerteSQLiteOpenHelper(Context context, String name, CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new PalidaMuerteSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_CATEGORY);
        db.execSQL(SQL_CREATE_TABLE_POEM);
        db.execSQL(SQL_CREATE_TABLE_POEM_TYPE);
        db.execSQL(SQL_CREATE_TABLE_SERIES);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
