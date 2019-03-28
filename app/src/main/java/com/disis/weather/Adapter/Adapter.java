package com.disis.weather.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.disis.weather.MOdel.Model;
import com.disis.weather.R;
import com.disis.weather.detailed_activity;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private List<Model> modelList;
    public Adapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }
    public Context getContext(){
        return context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.listitem,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Model model = modelList.get(i);
        viewHolder.tvcity.setText(model.getCity());
        viewHolder.tvcode.setText(model.getWoeid());

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),model.getWoeid(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), detailed_activity.class);
                intent.putExtra("woeid",model.getWoeid());
                getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvcity;
        public TextView tvcode;
        public LinearLayout linearLayout;

        ViewHolder(View view){
            super(view);

            tvcity = view.findViewById(R.id.tvtext);
            tvcode = view.findViewById(R.id.code);
            linearLayout = view.findViewById(R.id.linearl);
        }
    }
}
