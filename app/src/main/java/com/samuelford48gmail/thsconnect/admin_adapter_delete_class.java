package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class admin_adapter_delete_class extends RecyclerView.Adapter<admin_adapter_delete_class.MyHolder>{

    List<Class_model> listdata;

    public admin_adapter_delete_class(List<Class_model> listdata) {
        this.listdata = listdata;
    }

    @Override
    public admin_adapter_delete_class.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_class_model,parent,false);

        admin_adapter_delete_class.MyHolder myHolder = new admin_adapter_delete_class.MyHolder(view);
        return myHolder;
    }


    public void onBindViewHolder(admin_adapter_delete_class.MyHolder holder, final int position) {
        final Class_model data = listdata.get(position);
        holder.vdate_class.setText(data.getClassname());
        holder.vteacher.setText(data.getTeacher());
        holder.vrnumber.setText(data.getRoom_number());
        //System.out.println(data.getDate_class2());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                Context context = view.getContext();
                Intent intent = new Intent(context, admin_show_class_detail.class);
                intent.putExtra("date_class", listdata.get(position).getClassname());
                intent.putExtra("teacher", listdata.get(position).getTeacher());
                intent.putExtra("room_number", listdata.get(position).getRoom_number());
                intent.putExtra("post_key", listdata.get(position).getid());

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

