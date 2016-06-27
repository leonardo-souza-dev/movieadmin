package com.example.leo.movieadmin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rm48306 on 27/06/2016.
 */
public class MovieDAO {

    private static final String DATABASE_NAME = "clientedb.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "TBCLIENTE";

    private Context context;
    private SQLiteDatabase db;

    private SQLiteStatement insertStmt;
    private static final String INSERT = "insert into " + TABLE_NAME + " (nome, cpf) values (?,?)";

    public MovieDAO (Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT);
    }

    public long insert(Movie cliente) {
        this.insertStmt.bindString(1, cliente.getName());
        this.insertStmt.bindString(2, cliente.getPlot());
        return this.insertStmt.executeInsert();
    }

    public void deleteAll() {
        this.db.delete(TABLE_NAME, null, null);
    }

    public List<Movie> selectAll() {
        List<Movie> list = new ArrayList<Movie>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[] { "id", "nome", "cpf" },
                null, null, null, null, "id");

        if (cursor.moveToFirst()) {
            do {
                Movie cliente = new Movie();
                cliente.setId  (cursor.getInt(0));
                cliente.setName(cursor.getString(1));
                cliente.setPlot(cursor.getString(2));
                list.add(cliente);

            } while (cursor.moveToNext());
        }

        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }


        return list;


    }

    public void encerrarDB() {
        this.db.close();
    }

    private static class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, cpf TEXT)");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("Example", "*** Upgrading database, this will drop tables and recreate.");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

}
