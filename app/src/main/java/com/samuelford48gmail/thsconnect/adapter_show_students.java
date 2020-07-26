package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
//uses lock show students activities
public class adapter_show_students extends RecyclerView.Adapter<adapter_show_students.MyHolder>{

    ArrayList<Class_model> listdata;

    public adapter_show_students(ArrayList<Class_model> listdata) {
        this.listdata = listdata;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_class_model,parent,false);

        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }


    public void onBindViewHolder(MyHolder holder, final int position) {

        final Class_model data = listdata.get(position);
        holder.vdate_class.setText(data.getDate_clasname());
        holder.vteacher.setText(data.getTeacher());
        holder.vrnumber.setText(data.getRoom_number());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, admin_get_user_info.class);
                //intent.putExtra("date_class", listdata.get(position).getDate_class2());
                // intent.putExtra("teacher", listdata.get(position).getTeacher2());
                // intent.putExtra("room_number", listdata.get(position).getRnumber2());
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





