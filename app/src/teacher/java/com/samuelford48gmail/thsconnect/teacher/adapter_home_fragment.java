package com.samuelford48gmail.thsconnect.teacher;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.samuelford48gmail.thsconnect.Class_model;
import com.samuelford48gmail.thsconnect.R;
import com.samuelford48gmail.thsconnect.UtilMethods;
import com.samuelford48gmail.thsconnect.adapter_user_remove_class;
import com.samuelford48gmail.thsconnect.user_remove_class;

import java.util.ArrayList;

class adapter_home_fragment extends RecyclerView.Adapter<adapter_home_fragment.MyHolder> {

    ArrayList<Class_model> listdata;
    ArrayList<String> userdates;

    public adapter_home_fragment(ArrayList<Class_model> listdata, ArrayList<String> userdates) {
        this.listdata = listdata;
        this.userdates = userdates;
    }

    public adapter_home_fragment(ArrayList<Class_model> listdata) {
        this.listdata = listdata;
    }

    public void setUserdates(ArrayList<String> userdates) {
        this.userdates = userdates;
    }

    @Override
    public adapter_home_fragment.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_class_model, parent, false);

        adapter_home_fragment.MyHolder myHolder = new adapter_home_fragment.MyHolder(view);
        return myHolder;
    }


    public void onBindViewHolder(adapter_home_fragment.MyHolder holder, final int position) {

        final Class_model data = listdata.get(position);
        System.out.println(data.getid());
        System.out.println(data.getTeacher());
        System.out.println(data.getSubject());
        System.out.println(data.getRoom_number());
        holder.className.setText(data.getClassname());
        holder.teacher.setText(data.getTeacher());
        holder.rnumber.setText(data.getRoom_number());
        holder.dates.setText(UtilMethods.convertToString(userdates));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //   Log.i("OnBindViewHolder",listdata.get(position).getid());
                Context context = view.getContext();

                Intent intent = new Intent(context, edit_class_page.class);
                intent.putExtra("class", data);
                intent.putExtra("usersDates", userdates);
                //  intent.putExtra("date_class", listdata.get(position).getClassname());
                // intent.putExtra("teacher", listdata.get(position).getTeacher());
                //intent.putExtra("room_number", listdata.get(position).getRoom_number());
                //intent.putExtra("post_key", listdata.get(position).getid());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        if (listdata == null) {
            return 0;
        }

        return listdata.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView className, teacher, rnumber, dates;

        public MyHolder(View itemView) {
            super(itemView);
            className = itemView.findViewById(R.id.date_class_name);
            teacher = itemView.findViewById(R.id.teacher);
            rnumber = itemView.findViewById(R.id.room_number);
            dates = itemView.findViewById(R.id.dates);

        }
    }

}



