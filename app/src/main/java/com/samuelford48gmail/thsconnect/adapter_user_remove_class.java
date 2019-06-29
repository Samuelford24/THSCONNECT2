package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class adapter_user_remove_class extends RecyclerView.Adapter<adapter_user_remove_class.MyHolder>{

    List<Listdata> listdata;

    public adapter_user_remove_class(List<Listdata> listdata) {
        this.listdata = listdata;
    }

    @Override
    public adapter_user_remove_class.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_class_model,parent,false);

        adapter_user_remove_class.MyHolder myHolder = new adapter_user_remove_class.MyHolder(view);
        return myHolder;
    }


    public void onBindViewHolder(adapter_user_remove_class.MyHolder holder, final int position) {

        final Listdata data = listdata.get(position);
        holder.vdate_class.setText(data.getDate_class());
        holder.vteacher.setText(data.getTeacher());
        holder.vrnumber.setText(data.getRnumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( final View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, user_remove_class.class);
                intent.putExtra("date_class", listdata.get(position).getDate_class());
                 intent.putExtra("teacher", listdata.get(position).getTeacher());
                 intent.putExtra("room_number", listdata.get(position).getRnumber());
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
            vdate_class = (TextView) itemView.findViewById(R.id.date_class_name);
            vteacher = (TextView) itemView.findViewById(R.id.teacher);
            vrnumber = (TextView) itemView.findViewById(R.id.room_number);

        }
    }

}



