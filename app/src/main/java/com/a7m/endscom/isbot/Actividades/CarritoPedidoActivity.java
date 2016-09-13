package com.a7m.endscom.isbot.Actividades;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.a7m.endscom.brain.ARTICULO;
import com.a7m.endscom.isbot.R;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarritoPedidoActivity extends AppCompatActivity {
    private static ListView listView;
    TextView txtNameCliente;
    List<Map<String, Object>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_pedido);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        setTitle("CARRITO DE COMPRA");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        txtNameCliente = (TextView) findViewById(R.id.clsSale);
        txtNameCliente.setText(getIntent().getStringExtra("NombreCliente"));
        list = new ArrayList<Map<String, Object>>();
        listView = (ListView) findViewById(R.id.listViewSettingConnect);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.remove(i);
                Refresh();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == 16908332){
            finish();
        }
        switch (item.getItemId()){
            case R.id.action_pluss:
                startActivityForResult(new Intent(this,LIstaProductoActivity.class),0);
                break;
            case R.id.action_send:
                if (list.size()!=0){
                    Intent send = new Intent(this,ResumenActivity.class);
                    send.putExtra("LIST", (Serializable) list);
                    send.putExtra("NombreCliente",getIntent().getStringExtra("NombreCliente"));
                    startActivity(send);
                    finish();
                }else{
                    Toast.makeText(CarritoPedidoActivity.this, "VACIO", Toast.LENGTH_SHORT).show();
                }

                break;


        }
        return super.onOptionsItemSelected(item);
    }
    public void Refresh(){
        listView.setAdapter(
                new SimpleAdapter(
                        this,
                        list,
                        R.layout.list_item_articulo, new String[] { "ICON","ITEMNAME", "ITEMPRECIO" },
                        new int[] {R.id.btListItemIcon, R.id.tvListItemName,R.id.tvListItemPrecio }));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_carrito_compra,menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0 && resultCode==RESULT_OK){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ICON", android.R.drawable.stat_sys_data_bluetooth);
            map.put("ITEMNAME", data.getStringArrayListExtra("myItem").get(0));
            map.put("ITEMPRECIO", data.getStringArrayListExtra("myItem").get(1));
            list.add(map);
            Refresh();
        }
    }
}
