package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyHolder>{

    List<Listdata2> listdata;

    public RecyclerviewAdapter(List<Listdata2> listdata) {
        this.listdata = listdata;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_class_model,parent,false);

        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }


    public void onBindViewHolder(MyHolder holder, final int position) {
        final Listdata2 data = listdata.get(position);
        holder.vdate_class.setText(data.getDate_class2());
        holder.vteacher.setText(data.getTeacher2());
        holder.vrnumber.setText(data.getRnumber2());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( final View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, Add_class_to_user.class);
                intent.putExtra("date_class", listdata.get(position).getDate_class2());
                intent.putExtra("teacher", listdata.get(position).getTeacher2());
                intent.putExtra("room_number", listdata.get(position).getRnumber2());
                intent.putExtra("post_key", listdata.get(position).getUid2());
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
            vdate_class = (TextView) itemView.findViewById(R.id.date_class_name);
            vteacher = (TextView) itemView.findViewById(R.id.teacher);
           vrnumber = (TextView) itemView.findViewById(R.id.room_number);

               }
           }

        }




