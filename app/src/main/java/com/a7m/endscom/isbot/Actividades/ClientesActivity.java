package com.a7m.endscom.isbot.Actividades;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.a7m.endscom.brain.ARTICULO;
import com.a7m.endscom.brain.Clientes;
import com.a7m.endscom.isbot.Adaptadores.MyExpandableListAdapter;
import com.a7m.endscom.isbot.Clases.ChildRow;
import com.a7m.endscom.isbot.Clases.ParentRow;
import com.a7m.endscom.isbot.R;

import java.io.File;
import java.util.ArrayList;

public class ClientesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,SearchView.OnCloseListener{
    private SearchManager searchManager;
    private android.widget.SearchView searchView;
    private MyExpandableListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<ParentRow> parentList = new ArrayList<ParentRow>();
    private ArrayList<ParentRow> showTheseParentList = new ArrayList<ParentRow>();
    private MenuItem searchItem;
    ArrayList<ChildRow> childRows;
    ParentRow parentRow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        myList = (ExpandableListView) findViewById(R.id.ExpPlan);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ClientesActivity.this, "OOK", Toast.LENGTH_SHORT).show();
            }
        });

        setTitle("Lista de Clientes");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(ClientesActivity.this,NuevoClienteActivity.class));

                
            }
        });

        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        parentList = new ArrayList<ParentRow>();
        showTheseParentList = new ArrayList<ParentRow>();
        displayList();
        expandAll();
    }
    private void CargarPLan(){
        childRows = new ArrayList<ChildRow>();
        parentRow = null;
        for(Clientes obj : Clientes.getCliente(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + File.separator, this)) {
            childRows.add(new ChildRow(obj.getNombre()));
        }
        parentRow = new ParentRow("Base de Clientes", childRows);
        parentList.add(parentRow);


    }
    private void displayList(){
        CargarPLan();
        listAdapter = new MyExpandableListAdapter(ClientesActivity.this,parentList);
        myList.setAdapter(listAdapter);
    }

    private void expandAll(){
        int count = listAdapter.getGroupCount();
        for (int i=0;i<count;i++){
            myList.expandGroup(i);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_plan,menu);
        searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        searchView.requestFocus();
        return true;
    }

    @Override
    public boolean onClose() {
        listAdapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        listAdapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listAdapter.filterData(newText);
        expandAll();
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == 16908332){
            finish();
        }
        switch (item.getItemId()){
            case R.id.action_catalogo:
                startActivity(new Intent(this, CatalogoArticuloActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }
}
