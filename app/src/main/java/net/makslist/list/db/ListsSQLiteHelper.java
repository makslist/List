package net.makslist.list.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ListsSQLiteHelper extends SQLiteOpenHelper {

  private static final int DATABASE_VERSION = 1;
  private static final String DATABASE_NAME = "makslist";

  public ListsSQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(ListsDBContract.TableList.CREATE_TABLE);
    sqLiteDatabase.execSQL(ListsDBContract.TableList.INSERT_SAMPLE_DATA);
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ListsDBContract.TableList.TABLE_NAME);
    onCreate(sqLiteDatabase);
  }

  public Cursor readAllKistsFromDB() {
    SQLiteDatabase database = this.getReadableDatabase();

    String[] projection = {
        ListsDBContract.TableList._ID,
        ListsDBContract.TableList.COLUMN_NAME,
        ListsDBContract.TableList.COLUMN_TYPE,
        ListsDBContract.TableList.COLUMN_PRIORITY,
        ListsDBContract.TableList.COLUMN_DESCRIPTION,
    };

    String selection = "";

    String[] selectionArgs = {};

    Cursor cursor = database.query(
        ListsDBContract.TableList.TABLE_NAME,     // The table to query
        projection,                               // The columns to return
        selection,                                // The columns for the WHERE clause
        selectionArgs,                            // The values for the WHERE clause
        null,                                     // don't group the rows
        null,                                     // don't filter by row groups
        null                                      // don't sort
    );
    return cursor;
  }

  public long saveListToDB(String name, String description, String type) {
    SQLiteDatabase database = getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(ListsDBContract.TableList.COLUMN_NAME, name);
    values.put(ListsDBContract.TableList.COLUMN_DESCRIPTION, description);
    values.put(ListsDBContract.TableList.COLUMN_TYPE, type);

    return database.insert(ListsDBContract.TableList.TABLE_NAME, null, values);
  }
//
//  private void readFromDB(double id, String type, double order, String desc) {
//    SQLiteDatabase database = this.getReadableDatabase();
//
//    String selection = TableList._ID + " like ? and " +
//        TableList.COLUMN_TYPE + " like ? and " +
//        TableList.COLUMN_PRIORITY + " = ? and " +
//        TableList.COLUMN_DESCRIPTION + " like ?";
//
//    String[] projection = {
//        TableList._ID,
//        TableList.COLUMN_TYPE,
//        TableList.COLUMN_PRIORITY,
//        TableList.COLUMN_DESCRIPTION,
//    };
//    String[] selectionArgs = {"%" + id + "%", type + "%", +order + "", "%" + desc + "%"};
//
//    Cursor cursor = database.query(
//        TableList.TABLE_NAME,     // The table to query
//        projection,                               // The columns to return
//        selection,                                // The columns for the WHERE clause
//        selectionArgs,                            // The values for the WHERE clause
//        null,                                     // don't group the rows
//        null,                                     // don't filter by row groups
//        null                                      // don't sort
//    );
//  }

}

