package br.com.gustavo.luiz.trabalhopratico2_lddm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by luiz on 09/05/17.
 */

public class DbHelper extends SQLiteOpenHelper
{
    // definir dados
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "coordenadas.db";
    public static final String TABLE_NAME = "coordenadas";
    public static final String ID = "_id";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String FOTO = "foto";
    private static final String DATABASE_CREATE = "create table " + TABLE_NAME + "( " + ID +
                                                  " integer primary key autoincrement, " +
                                                  LATITUDE + " text not null, " + LONGITUDE +
                                                  " text not null, " + FOTO + " BLOB);";

    public DbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }// end constructor

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DATABASE_CREATE);
    }// end onCreate( )

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(DbHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion +
                                        ", whick will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }// end onUpgrade( )
}// end class
