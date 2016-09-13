package com.a7m.endscom.brain;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A7M on 04/09/2016.
 */
public class ARTICULO extends ArrayList<Integer> {
    private String Nombre,Precio,Descripcion;
    private int IdArticulo;


    public ARTICULO(String nombre, String precio, String descripcion, int idArticulo) {
        Nombre = nombre;
        Precio = precio;
        Descripcion = descripcion;
        IdArticulo = idArticulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public ARTICULO() {
        super();
    }

    public int getIdArticulo() {
        return IdArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        IdArticulo = idArticulo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }
    public static List<ARTICULO> getArticulos(String basedir, Context context) {
        List<ARTICULO> lista = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;

        try {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.query(true, "CATALOGO", null, null, null, null, null, null, null);
            //Cursor cursor = myDataBase.rawQuery("SELECT * FROM DEPARTAMENTOS", null);

            if(cursor.getCount() > 0) {

                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    ARTICULO tmp = new ARTICULO();
                    tmp.setIdArticulo(cursor.getInt(cursor.getColumnIndex("IdArticulo")));
                    tmp.setNombre(cursor.getString(cursor.getColumnIndex("Nombre")));
                    tmp.setPrecio(cursor.getString(cursor.getColumnIndex("Precio")));
                    tmp.setDescripcion(cursor.getString(cursor.getColumnIndex("Descripcion")));
                    lista.add(tmp);
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
        return lista;
    }
}
