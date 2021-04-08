package com.example.mybooks;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManipulator {

    private static final String DATABASE_NAME = "mydatabase.db";
    private static int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "book";
    private static Context context;
    static SQLiteDatabase db;
    private SQLiteStatement insertStmt;

    private static final String INSERT = "insert into " + TABLE_NAME + " (title, authors, subtitle, description, collection) values (?,?,?,?,?)";

    public DatabaseManipulator (Context context) {
        DatabaseManipulator.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        DatabaseManipulator.db = openHelper.getWritableDatabase();
        this.insertStmt = DatabaseManipulator.db.compileStatement(INSERT);
    }

    /**
     *
     * @param title
     * @param authors
     * @param subtitle
     * @param description
     * @param collection
     * @return
     */
    public long insert (String title, String authors, String subtitle, String description, String collection)
    {
        this.insertStmt.bindString(1, title);
        this.insertStmt.bindString(2, authors);
        this.insertStmt.bindString(3, subtitle);
        this.insertStmt.bindString(4, description);
        this.insertStmt.bindString(5, collection);
        return this.insertStmt.executeInsert();
    }

    public void deleteAll ()
    {
        db.delete(TABLE_NAME, null, null);
    }

    /**
     * Delete a collection
     * @param currentCollection
     */
    public void deleteCollection (String currentCollection)
    {
        db.delete(TABLE_NAME, "collection=?", new String[]{currentCollection});
    }

    /*
    public List<String[]> selectAll ()
    {
        List<String[]> list = new ArrayList<String[]>();
        Cursor cursor = db.query(TABLE_NAME, new String [] {"id", "title", "authors"}, null, null, null, null, null);
        int x = 0;
        if (cursor.moveToFirst()) {
            do {
                String [] b1 = new String []{cursor.getString(0), cursor.getString(1), cursor.getString(2)};
                list.add(b1);
                x++;
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        cursor.close();
        return  list;
    }
    */

    public List<String[]> selectFavourites ()
    {
        List<String[]> list = new ArrayList<String[]>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE collection='Favourites'", null);
        int x = 0;
        if (cursor.moveToFirst()) {
            do {
                String[] b1 = new String[]{cursor.getString(0),
                        cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5)};
                list.add(b1);
                x++;
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        cursor.close();
        return list;
    }

    public List<String[]> selectWantToRead ()
    {
        List<String[]> list = new ArrayList<String[]>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE collection='Want to Read'", null);
        int x = 0;
        if (cursor.moveToFirst()) {
            do {
                String[] b1 = new String[]{cursor.getString(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5)};
                list.add(b1);
                x++;
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        cursor.close();
        return list;
    }

    public List<String[]> selectCurrentlyReading ()
    {
        List<String[]> list = new ArrayList<String[]>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE collection='Currently Reading'", null);
        int x = 0;
        if (cursor.moveToFirst()) {
            do {
                String[] b1 = new String[]{cursor.getString(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5)};
                list.add(b1);
                x++;
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        cursor.close();
        return list;
    }

    public List<String[]> selectRead ()
    {
        List<String[]> list = new ArrayList<String[]>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE collection='Read'", null);
        int x = 0;
        if (cursor.moveToFirst()) {
            do {
                String[] b1 = new String[]{cursor.getString(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5)};
                list.add(b1);
                x++;
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        cursor.close();
        return list;
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        OpenHelper (Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate (SQLiteDatabase db)
        {
            db.execSQL("CREATE TABLE "
                    + TABLE_NAME + " (id INTEGER PRIMARY KEY, title TEXT, authors TEXT, subtitle TEXT, description TEXT, collection TEXT)");
        }

        public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion)
        {
            DATABASE_VERSION = newVersion;
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }


}
