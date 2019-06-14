package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerviewAdapter2 extends RecyclerView.Adapter<RecyclerviewAdapter2.MyHolder>{

    List<Listdata2> listdata2;

    public RecyclerviewAdapter2(List<Listdata2> listdata) {
        this.listdata2 = listdata;
    }

    @Override
    public RecyclerviewAdapter2.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_class_model,parent,false);

        RecyclerviewAdapter2.MyHolder myHolder = new RecyclerviewAdapter2.MyHolder(view);
        return myHolder;
    }


    public void onBindViewHolder(RecyclerviewAdapter2.MyHolder holder, final int position) {
        final Listdata2 data = listdata2.get(position);
        holder.vdate_class.setText(data.getDate_class2());
        holder.vteacher.setText(data.getTeacher2());
        holder.vrnumber.setText(data.getRnumber2());
        System.out.println(data.getDate_class2());
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick( final View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, test_for_science_add_class.class);
                intent.putExtra("date_class", listdata2.get(position).getDate_class2());
                intent.putExtra("teacher", listdata2.get(position).getTeacher2());
                intent.putExtra("room_number", listdata2.get(position).getRnumber2());
                intent.putExtra("post_key", listdata2.get(position).getUid2());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listdata2.size();
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


