package com.a7m.endscom.brain;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by marangelo.php on 31/08/2016.
 */
public class Usuario {
    private static  String nombre;

    public Usuario() {
        super();
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        Usuario.nombre = nombre;
    }

    public static boolean leerDB(String Usuario, String PASSWORD, String basedir, Context context){
        boolean Correcto=false;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.rawQuery("select * from USUARIOS where CREDENCIAL='"+Usuario+"' and PASSWORD='"+PASSWORD+"' ", null);
            if(cursor.getCount() > 0) {
                Correcto = true;
                cursor.moveToFirst();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(myDataBase != null) {
                myDataBase.close();
            }
            if(myDbHelper != null) {
                myDbHelper.close();
            }
        }
        return Correcto;
    }
}
