package com.a7m.endscom.isbot.Actividades;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.a7m.endscom.brain.ARTICULO;
import com.a7m.endscom.isbot.Adaptadores.CarviewAdapter;
import com.a7m.endscom.isbot.R;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LIstaProductoActivity extends AppCompatActivity {
    private static ListView listView;
    EditText Inputcant;
    List<Map<String, Object>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_producto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("LISTA DE PRODUCTO");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        final ArrayList<String> strings = new ArrayList<String>();

        list = new ArrayList<Map<String, Object>>();
        for(ARTICULO obj : ARTICULO.getArticulos(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator, this)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ITEMNAME", obj.getNombre());
            map.put("ITEMPRECIO", obj.getPrecio());
            list.add(map);
        }
        listView = (ListView) findViewById(R.id.listViewSettingConnect);
        listView.setAdapter(new SimpleAdapter(this, list,R.layout.list_item_articulo, new String[] { "ITEMNAME", "ITEMPRECIO" }, new int[] {R.id.tvListItemName,R.id.tvListItemPrecio }));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {

                LayoutInflater li = LayoutInflater.from(LIstaProductoActivity.this);
                View promptsView = li.inflate(R.layout.input_cant, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LIstaProductoActivity.this);
                alertDialogBuilder.setView(promptsView);
                Inputcant = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        strings.add( list.get(i).get( "ITEMNAME").toString());
                                        strings.add( list.get(i).get("ITEMPRECIO").toString());
                                        strings.add( Inputcant.getText().toString());
                                        getIntent().putStringArrayListExtra("myItem",strings);
                                        setResult(RESULT_OK,getIntent());
                                        finish();
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                        }}).create().show();




            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == 16908332){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
