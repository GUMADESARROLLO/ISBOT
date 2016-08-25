package com.a7m.endscom.isbot.Actividades;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.a7m.endscom.isbot.Adaptadores.MyExpandableListAdapter;
import com.a7m.endscom.isbot.Clases.ChildRow;
import com.a7m.endscom.isbot.Clases.ParentRow;
import com.a7m.endscom.isbot.R;

import java.util.ArrayList;

public class ClientesActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,SearchView.OnCloseListener{
    private SearchManager searchManager;
    private android.widget.SearchView searchView;
    private MyExpandableListAdapter listAdapter;
    private ExpandableListView myList;
    private ArrayList<ParentRow> parentList = new ArrayList<ParentRow>();
    private ArrayList<ParentRow> showTheseParentList = new ArrayList<ParentRow>();
    private MenuItem searchItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setTitle("Lista de Clientes");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewCarrito = new Intent(ClientesActivity.this,NuevoClienteActivity.class);
                startActivity(viewCarrito);
            }
        });

        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        parentList = new ArrayList<ParentRow>();
        showTheseParentList = new ArrayList<ParentRow>();
        displayList();
       expandAll();
    }
    private void CargarPLan(){
        ArrayList<ChildRow> childRows = new ArrayList<ChildRow>();
        ParentRow parentRow = null;

        childRows.add(new ChildRow("Cliente 1"));
        childRows.add(new ChildRow("Cliente 2"));

        childRows.add(new ChildRow("Cliente 3"));
        childRows.add(new ChildRow("Cliente 4"));

        childRows.add(new ChildRow("Cliente 5"));
        childRows.add(new ChildRow("Cliente 6"));

        childRows.add(new ChildRow("Cliente 7"));
        childRows.add(new ChildRow("Cliente 8"));

        childRows.add(new ChildRow("Cliente 9"));
        childRows.add(new ChildRow("Cliente 10"));

        childRows.add(new ChildRow("Cliente 11"));
        childRows.add(new ChildRow("Cliente 12"));

        parentRow = new ParentRow("Base de Clientes", childRows);
        parentList.add(parentRow);


    }
    private void displayList(){
        CargarPLan();
        myList = (ExpandableListView) findViewById(R.id.ExpPlan);
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

}
