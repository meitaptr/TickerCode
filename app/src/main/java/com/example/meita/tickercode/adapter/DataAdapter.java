package com.example.meita.tickercode.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.meita.tickercode.R;
import com.example.meita.tickercode.model.DataModel;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private List<DataModel> dataModels;
    private Context context;

    public DataAdapter(List<DataModel> dataModels, Context context) {
        this.dataModels = dataModels;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_data, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DataModel dataModel=dataModels.get(position);
        holder.textView_name.setText(dataModel.getName());
        holder.textView_symbol.setText(dataModel.getSymbol());
        holder.textView_matchscore.setText(dataModel.getMatchScore());
        holder.textView_region.setText(dataModel.getRegion());
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView_name, textView_symbol, textView_region, textView_matchscore;

        public ViewHolder(View itemView) {
            super(itemView);
            textView_name = (TextView)itemView.findViewById(R.id.textView_name);
            textView_symbol = (TextView)itemView.findViewById(R.id.textView_symbol);
            textView_region = (TextView)itemView.findViewById(R.id.textView_region);
            textView_matchscore = (TextView)itemView.findViewById(R.id.textView_matchscore);
        }
    }
}
