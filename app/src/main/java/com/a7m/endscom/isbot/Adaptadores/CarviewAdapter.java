package com.a7m.endscom.isbot.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a7m.endscom.brain.ARTICULO;
import com.a7m.endscom.isbot.R;
import java.util.ArrayList;

/**
 * Created by A7M on 04/09/2016.
 */
public class CarviewAdapter extends RecyclerView.Adapter<CarviewAdapter.ViewHolder> {
    private int listItemLayout;
    private ArrayList<ARTICULO> itemList;
    public CarviewAdapter(int layoutId, ArrayList<ARTICULO> itemList) {
        listItemLayout = layoutId;
        this.itemList = itemList;
    }
    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView itemNombre = holder.itemNombre;
        TextView itemPrecio = holder.itemPrecio;
        TextView itemDescr = holder.itemDescrip;

        itemNombre.setText(itemList.get(listPosition).getNombre());
        itemPrecio.setText("C$ " + itemList.get(listPosition).getPrecio());
        itemDescr.setText(itemList.get(listPosition).getDescripcion());
    }
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView itemNombre,itemPrecio,itemDescrip;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemNombre = (TextView) itemView.findViewById(R.id.row_item);
            itemPrecio = (TextView) itemView.findViewById(R.id.precio);
            itemDescrip = (TextView) itemView.findViewById(R.id.descripcion);
        }
        @Override
        public void onClick(View view) {
            Log.d("onclick", "onClick " + getLayoutPosition() + " " + itemNombre.getText());
        }
    }
}