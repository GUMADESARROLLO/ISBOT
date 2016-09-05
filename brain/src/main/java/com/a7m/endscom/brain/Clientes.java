package com.a7m.endscom.brain;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A7M on 04/09/2016.
 */
public class Clientes {
    private String Nombre,Direccion,Cedula,Telefono;
    private int IdDepartamento;


    public Clientes(String nombre, String direccion, String cedula, String telefono, int idDepartamento) {
        Nombre = nombre;
        Direccion = direccion;
        Cedula = cedula;
        Telefono = telefono;
        IdDepartamento = idDepartamento;
    }
    public Clientes() {
        super();
    }
    public int getIdDepartamento() {
        return IdDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        IdDepartamento = idDepartamento;
    }
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String cedula) {
        Cedula = cedula;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
    public static List<Clientes> getDepartamentos(String basedir, Context context) {
        List<Clientes> lista = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;

        try {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.query(true, "DEPARTAMENTOS", null, null, null, null, null, null, null);
            //Cursor cursor = myDataBase.rawQuery("SELECT * FROM DEPARTAMENTOS", null);

            if(cursor.getCount() > 0) {

                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    Clientes tmp = new Clientes();
                    tmp.setIdDepartamento(cursor.getInt(cursor.getColumnIndex("IdDepartamento")));
                    tmp.setNombre(cursor.getString(cursor.getColumnIndex("Nombre")));
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
    public boolean guardarDB(String basedir, Context context) {
        boolean guardo = false;
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;
        try {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Nombre", Nombre);
            values.put("Direccion", Direccion);
            values.put("Cedula", Cedula);
            values.put("Telefono", Telefono );
            guardo = myDataBase.insert("CLIENTES", null, values) != -1;
            //myDataBase.execSQL("Insert into Producto (sfsdfs)");
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
        return guardo;
    }
    public static List<Clientes> getCliente(String basedir, Context context) {
        List<Clientes> lista = new ArrayList<>();
        SQLiteDatabase myDataBase = null;
        SQLiteHelper myDbHelper = null;

        try {
            myDbHelper = new SQLiteHelper(basedir, context);
            myDataBase = myDbHelper.getReadableDatabase();
            Cursor cursor = myDataBase.query(true, "CLIENTES", null, null, null, null, null, null, null);
            //Cursor cursor = myDataBase.rawQuery("SELECT * FROM DEPARTAMENTOS", null);

            if(cursor.getCount() > 0) {

                cursor.moveToFirst();
                while(!cursor.isAfterLast()) {
                    Clientes tmp = new Clientes();
                    tmp.setNombre(cursor.getString(cursor.getColumnIndex("Nombre")));
                    tmp.setDireccion(cursor.getString(cursor.getColumnIndex("Direccion")));
                    tmp.setCedula(cursor.getString(cursor.getColumnIndex("Cedula")));
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
