package com.example.mobidevelop.jpdistribution;

/**
 * Created by IZANK on 10/19/2016.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataRute extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "datarute.db";
    private static final int DATABASE_VERSION = 2;

    @SuppressLint("SdCardPath")
    private static String DB_PATH = "/data/data/com.example.mobidevelop.jpdistribution/databases/";

    private Context myContext;

    public DataRute(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
        myContext=context;
    }

    public void createDataBase() throws IOException {
        if(DataBaseisExist()){
            //Toast.makeText(myContext, "Database Sudah Ada", Toast.LENGTH_LONG).show();
        }
        else{
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }

    }

    private boolean DataBaseisExist(){
        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

        }
        if(checkDB != null){
            checkDB.close();
        }
        if(checkDB != null )
            return true ;
        else
            return false;
    }

    private void copyDataBase() throws IOException {
        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);
        // Path to the just created empty db
        String outFileName = DB_PATH + DATABASE_NAME;
        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    public void connect() {
        try {
            this.createDataBase();
        } catch (Exception ioe) {
            Toast.makeText(myContext,
                    "Gagal mengkoneksikan ke database!", Toast.LENGTH_LONG)
                    .show();
        }
    }
}
