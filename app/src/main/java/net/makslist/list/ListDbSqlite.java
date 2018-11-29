package net.makslist.list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import java.text.ParseException;

public class ListDbSqlite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "makslist";

    public static class TableList implements BaseColumns {
        public static final String TABLE_NAME = "list";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_ORDER = "order";
        public static final String COLUMN_DESCRIPTION = "description";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + "(" +
                _ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TYPE + "TEXT," +
                COLUMN_ORDER + "INTEGER, " +
                COLUMN_DESCRIPTION + " TEXT " +
                ")";
    }

    public ListDbSqlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TableList.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TableList.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    private void saveToDB(String desc, String type, double order) throws ParseException {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TableList.COLUMN_DESCRIPTION, desc);
        values.put(TableList.COLUMN_TYPE, type);
        if (order == 0)
            order = Math.random();
        values.put(TableList.COLUMN_ORDER, order);
        long newRowId = database.insert(TableList.TABLE_NAME, null, values);
    }

    private void readAllFromDB() {

        SQLiteDatabase database = this.getReadableDatabase();

        String[] projection = {
                TableList._ID,
                TableList.COLUMN_TYPE,
                TableList.COLUMN_ORDER,
                TableList.COLUMN_DESCRIPTION,
        };

        String selection = "";

        String[] selectionArgs = {};

        Cursor cursor = database.query(
                TableList.TABLE_NAME,     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // don't sort
        );

        //Log.d(TAG, "The total cursor count is " + cursor.getCount());
    }
    private void readFromDB (double id, String type, double order, String desc) {

    SQLiteDatabase database = this.getReadableDatabase();

    String selection = TableList._ID + " like ? and " +
            TableList.COLUMN_TYPE + " like ? and " +
            TableList.COLUMN_ORDER + " = ? and " +
            TableList.COLUMN_DESCRIPTION + " like ?";

    String[] projection = {
            TableList._ID,
            TableList.COLUMN_TYPE,
            TableList.COLUMN_ORDER,
            TableList.COLUMN_DESCRIPTION,
    };
    String[] selectionArgs = {"%" + id + "%", type + "%", + order + "", "%" + desc + "%"};

        Cursor cursor = database.query(
                TableList.TABLE_NAME,     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // don't sort
        );

        //Log.d(TAG, "The total cursor count is " + cursor.getCount());
    }
}

