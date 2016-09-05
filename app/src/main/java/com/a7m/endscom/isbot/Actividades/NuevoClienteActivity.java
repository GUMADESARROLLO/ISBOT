package com.a7m.endscom.isbot.Actividades;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.a7m.endscom.brain.Clientes;
import com.a7m.endscom.isbot.R;

import java.io.File;
import java.util.List;
import java.util.StringTokenizer;

public class NuevoClienteActivity extends AppCompatActivity {
    Spinner spinner,spnnrMunicipio;
    String[] Departamentos = new String[0];
    EditText cNombre,cDireccion,cCedula,cTelefono;
    private String basedir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("NUEVO CLIENTE");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        basedir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                try {
                    crearClientes().guardarDB(basedir, NuevoClienteActivity.this);
                    finish();
                } catch (Exception e) {
                    Toast.makeText(NuevoClienteActivity.this, "Producto NO creado..", Toast.LENGTH_LONG).show();
                }
            }
        });

        cNombre = (EditText) findViewById(R.id.campo_nombre);
        cDireccion = (EditText) findViewById(R.id.campo_Direccion);
        cCedula = (EditText) findViewById(R.id.campo_cedu);
        cTelefono = (EditText) findViewById(R.id.campo_telefono);

        spinner = (Spinner) findViewById(R.id.ListDepartamentos);
        spnnrMunicipio = (Spinner) findViewById(R.id.spinnerMunicipio);

        int i=0;
        List<Clientes> cl = Clientes.getDepartamentos(basedir, this);
        Departamentos = new String[cl.size()];
        for(Clientes obj : cl) {
            Departamentos[i] = obj.getNombre();
            i++;
        }
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Departamentos));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(NuevoClienteActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spnnrMunicipio.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Departamentos));

    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == 16908332){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    private Clientes crearClientes(){
        Clientes obj = new Clientes();
        obj.setNombre(cNombre.getText().toString());
        obj.setDireccion(cDireccion.getText().toString());
        obj.setCedula(cCedula.getText().toString());
        obj.setTelefono(cTelefono.getText().toString());
        return obj;
    }

}
