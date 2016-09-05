package com.a7m.endscom.isbot.Actividades;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.a7m.endscom.brain.ARTICULO;
import com.a7m.endscom.isbot.Adaptadores.CarviewAdapter;
import com.a7m.endscom.isbot.R;

import java.util.ArrayList;

public class LIstaProductoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_producto);

        setTitle("LISTA DE PRODUCTO");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        // Initializing list view with the custom adapter
        ArrayList<ARTICULO> itemList = new ArrayList<ARTICULO>();

        CarviewAdapter itemArrayAdapter = new CarviewAdapter(R.layout.list_item, itemList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.item_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemArrayAdapter);

        // Populating list items
        for(int i=0; i<100; i++) {
            //itemList.add(new ARTICULO("Item " + i,"",0));
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == 16908332){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
