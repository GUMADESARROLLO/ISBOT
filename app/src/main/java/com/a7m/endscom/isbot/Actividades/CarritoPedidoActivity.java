package com.a7m.endscom.isbot.Actividades;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    TextView txtNameCliente,SubTotal,ivaTotal,Total;
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

        SubTotal = (TextView) findViewById(R.id.SubTotal);
        ivaTotal = (TextView) findViewById(R.id.ivaTotal);
        Total = (TextView) findViewById(R.id.Total);

        txtNameCliente.setText(getIntent().getStringExtra("NombreCliente"));
        list = new ArrayList<Map<String, Object>>();
        listView = (ListView) findViewById(R.id.listViewSettingConnect);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CarritoPedidoActivity.this);
                builder.setMessage("¿Desea Eliminar el Registro?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                list.remove(i);
                                Refresh();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                }).create().show();
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(CarritoPedidoActivity.this);
                    builder.setMessage("¿Confirma la transaccion?")
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent send = new Intent(CarritoPedidoActivity.this,ResumenActivity.class);
                                    send.putExtra("LIST", (Serializable) list);
                                    send.putExtra("NombreCliente",getIntent().getStringExtra("NombreCliente"));
                                    startActivity(send);
                                    finish();
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    }).create().show();

                }else{
                    Toast.makeText(CarritoPedidoActivity.this, "VACIO", Toast.LENGTH_SHORT).show();
                }

                break;


        }
        return super.onOptionsItemSelected(item);
    }
    public void Refresh(){
        float vLine = 0,subValor=0,vFinal=0;
        listView.setAdapter(
                new SimpleAdapter(
                        this,
                        list,
                        R.layout.list_item_carrito, new String[] {"ITEMNAME", "ITEMCANTI","ITEMPRECIO","ITEMVALOR" },
                        new int[] {R.id.tvListItemName,R.id.Item_cant,R.id.tvListItemPrecio,R.id.Item_valor }));


        for (Map<String, Object> obj : list){
            vLine     += Float.parseFloat(obj.get("ITEMVALOR").toString());
            subValor  += Float.parseFloat(obj.get("ITEMSUBTOTAL").toString());
            vFinal    += Float.parseFloat(obj.get("ITEMVALORTOTAL").toString());
        }
        SubTotal.setText("SubTotal C$ " + String.valueOf(vLine));
        ivaTotal.setText("IVA C$ " + String.valueOf(subValor));
        Total.setText("TOTAL C$ "+ String.valueOf(vFinal));
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
            map.put("ITEMNAME", data.getStringArrayListExtra("myItem").get(0));
            map.put("ITEMPRECIO", data.getStringArrayListExtra("myItem").get(1));
            map.put("ITEMCANTI",  data.getStringArrayListExtra("myItem").get(2));

            map.put("ITEMVALOR", data.getStringArrayListExtra("myItem").get(3));
            map.put("ITEMSUBTOTAL", data.getStringArrayListExtra("myItem").get(4));
            map.put("ITEMVALORTOTAL", data.getStringArrayListExtra("myItem").get(5));
            list.add(map);
            Refresh();
        }
    }
}
