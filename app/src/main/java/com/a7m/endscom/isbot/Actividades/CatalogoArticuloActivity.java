package com.a7m.endscom.isbot.Actividades;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.a7m.endscom.brain.ARTICULO;
import com.a7m.endscom.isbot.Adaptadores.CarviewAdapter;
import com.a7m.endscom.isbot.R;

import java.io.File;
import java.util.ArrayList;

public class CatalogoArticuloActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_articulo);
        setTitle("LISTA DE PRODUCTO");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ArrayList<ARTICULO> itemList = new ArrayList<ARTICULO>();
        for(ARTICULO obj : ARTICULO.getArticulos(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator, this)) {
            itemList.add(new ARTICULO(obj.getNombre(),obj.getPrecio(),obj.getDescripcion(),0));
        }
        CarviewAdapter itemArrayAdapter = new CarviewAdapter(R.layout.list_item, itemList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.item_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemArrayAdapter);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == 16908332){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
