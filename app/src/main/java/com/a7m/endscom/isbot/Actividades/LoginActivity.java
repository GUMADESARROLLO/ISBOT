package com.a7m.endscom.isbot.Actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.a7m.endscom.isbot.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.btnOK).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewPlan = new Intent(LoginActivity.this,ClientesActivity.class);
                startActivity(viewPlan);
                finish();
            }
        });

    }
}
