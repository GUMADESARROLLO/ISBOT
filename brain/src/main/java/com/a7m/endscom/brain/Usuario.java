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
    private String nombre;
    private String Contrasenna;

    public Usuario() {
        super();
    }

    public Usuario(String nombre, String contrasenna) {
        this.nombre = nombre;
        Contrasenna = contrasenna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenna() {
        return Contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        Contrasenna = contrasenna;
    }
    public static boolean leerDB(String Usuario, String PASSWORD,String basedir, Context context){
        boolean Correcto=false;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.rawQuery("select * from USUARIOS where CREDENCIAL='"+Usuario+"' and PASSWORD='"+PASSWORD+"' ", null);
            Log.d("XQ","select * from USUARIOS where CREDENCIAL='"+Usuario+"' and PASSWORD='"+PASSWORD+"' ");
            if(cursor.getCount() > 0) {
                Correcto = true;
                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                   /* Usuario tmp = new Usuario();
                    tmp.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
                    tmp.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                    tmp.setPrecio(cursor.getFloat(cursor.getColumnIndex("precio")));
                    tmp.setCategoria(cursor.getInt(cursor.getColumnIndex("categoria")) == 1);
                    tmp.setExento(cursor.getInt(cursor.getColumnIndex("codigo")) == 1);
                    lista.add(tmp);*/
                    cursor.moveToNext();
                }
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