package net.makslist.list.db;

import android.provider.BaseColumns;

public final class ListsDBContract {

  public static class TableList implements BaseColumns {

    public static final String TABLE_NAME = "list";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_PRIORITY = "priority";
    public static final String COLUMN_DESCRIPTION = "description";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
        TABLE_NAME + " (" +
        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        COLUMN_NAME + " UNIQUE TEXT, " +
        COLUMN_TYPE + " TEXT, " +
        COLUMN_DESCRIPTION + " TEXT, " +
        COLUMN_PRIORITY + " INTEGER" + ")";

    static final String INSERT_SAMPLE_DATA = "INSERT INTO " +
        TABLE_NAME +
        " (TEST2 , ToDo, Testlist, 1) " +
        "VALUES (" +
        COLUMN_NAME + ", " +
        COLUMN_TYPE + ", " +
        COLUMN_DESCRIPTION + ", " +
        COLUMN_PRIORITY + ")";
  }

  private ListsDBContract() {
  }

}