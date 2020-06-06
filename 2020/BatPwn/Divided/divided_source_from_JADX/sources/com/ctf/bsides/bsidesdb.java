package com.ctf.bsides;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class bsidesdb extends SQLiteOpenHelper {
    private static String DB_NAME = "admin";
    String DB_PATH = null;
    private final Context myContext;
    private SQLiteDatabase myDataBase;

    public bsidesdb(Context context) {
        super(context, DB_NAME, null, 10);
        this.myContext = context;
        StringBuilder sb = new StringBuilder();
        sb.append("/data/data/");
        sb.append(context.getPackageName());
        sb.append("/databases/");
        String sb2 = sb.toString();
        this.DB_PATH = sb2;
        Log.e("Path 1", sb2);
    }

    public void createDataBase() throws IOException {
        if (!checkDataBase()) {
            getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(this.DB_PATH);
            sb.append(DB_NAME);
            checkDB = SQLiteDatabase.openDatabase(sb.toString(), null, 1);
        } catch (SQLiteException e) {
        }
        if (checkDB != null) {
            checkDB.close();
        }
        if (checkDB != null) {
            return true;
        }
        return false;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = this.myContext.getAssets().open(DB_NAME);
        StringBuilder sb = new StringBuilder();
        sb.append(this.DB_PATH);
        sb.append(DB_NAME);
        OutputStream myOutput = new FileOutputStream(sb.toString());
        byte[] buffer = new byte[10];
        while (true) {
            int read = myInput.read(buffer);
            int length = read;
            if (read > 0) {
                myOutput.write(buffer, 0, length);
            } else {
                myOutput.flush();
                myOutput.close();
                myInput.close();
                return;
            }
        }
    }

    public void openDataBase() throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append(this.DB_PATH);
        sb.append(DB_NAME);
        this.myDataBase = SQLiteDatabase.openDatabase(sb.toString(), null, 1);
    }

    public synchronized void close() {
        if (this.myDataBase != null) {
            this.myDataBase.close();
        }
        super.close();
    }

    public void onCreate(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            try {
                copyDataBase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return this.myDataBase.query("login", null, null, null, null, null, null);
    }
}
