package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class adapter_user_remove_class extends RecyclerView.Adapter<adapter_user_remove_class.MyHolder>{

    List<Class_model> listdata;

    public adapter_user_remove_class(List<Class_model> listdata) {
        this.listdata = listdata;
    }

    @Override
    public adapter_user_remove_class.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_class_model,parent,false);

        adapter_user_remove_class.MyHolder myHolder = new adapter_user_remove_class.MyHolder(view);
        return myHolder;
    }


    public void onBindViewHolder(adapter_user_remove_class.MyHolder holder, final int position) {

        final Class_model data = listdata.get(position);
        holder.vdate_class.setText(data.getDate_clasname());
        holder.vteacher.setText(data.getTeacher());
        holder.vrnumber.setText(data.getRoom_number());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, user_remove_class.class);
                intent.putExtra("date_class", listdata.get(position).getDate_clasname());
                intent.putExtra("teacher", listdata.get(position).getTeacher());
                intent.putExtra("room_number", listdata.get(position).getRoom_number());
                intent.putExtra("post_key", listdata.get(position).getUid());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
        TextView vdate_class , vteacher,vrnumber;

        public MyHolder(View itemView) {
            super(itemView);
            vdate_class = itemView.findViewById(R.id.date_class_name);
            vteacher = itemView.findViewById(R.id.teacher);
            vrnumber = itemView.findViewById(R.id.room_number);

        }
    }

}



