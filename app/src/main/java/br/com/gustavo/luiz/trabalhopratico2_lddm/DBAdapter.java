package br.com.gustavo.luiz.trabalhopratico2_lddm;

/**
 * Created by luiz on 09/05/17.
 */

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DBAdapter
{
    private SQLiteDatabase database;
    private DbHelper dbHelper;
    private String[] allColumns = {dbHelper.ID, dbHelper.LATITUDE, dbHelper.LONGITUDE, dbHelper.FOTO, DbHelper.FOTO};


}
