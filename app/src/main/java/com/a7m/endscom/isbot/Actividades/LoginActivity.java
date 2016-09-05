package com.a7m.endscom.isbot.Actividades;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.a7m.endscom.brain.Usuario;
import com.a7m.endscom.isbot.R;

import java.io.File;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Usuario tmp = new Usuario();

        final TextView txtUsurio = (TextView) findViewById(R.id.edtAgente);
        final TextView txtPass = (TextView) findViewById(R.id.edtPass);

        findViewById(R.id.btnOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tmp.leerDB(
                        txtUsurio.getText().toString(),
                        txtPass.getText().toString(),
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator,
                        LoginActivity.this)){
                        startActivity(new Intent(LoginActivity.this,ClientesActivity.class));
                        finish();
                }else{
                    Toast.makeText(LoginActivity.this, "El Usuario no existe", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
