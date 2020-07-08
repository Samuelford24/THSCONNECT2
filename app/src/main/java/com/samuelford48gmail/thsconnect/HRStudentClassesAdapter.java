package com.samuelford48gmail.thsconnect;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HRStudentClassesAdapter extends RecyclerView.Adapter<HRStudentClassesAdapter.MyHolder> {

    List<Listdata> listdata;

    public HRStudentClassesAdapter(List<Listdata> listdata) {
        this.listdata = listdata;
    }

    @Override
    public HRStudentClassesAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_class_model, parent, false);

        HRStudentClassesAdapter.MyHolder myHolder = new HRStudentClassesAdapter.MyHolder(view);
        return myHolder;
    }


    public void onBindViewHolder(HRStudentClassesAdapter.MyHolder holder, final int position) {
        final Listdata data = listdata.get(position);
        holder.vdate_class.setText(data.getDate_class());
        holder.vteacher.setText(data.getTeacher());
        holder.vrnumber.setText(data.getRnumber());
        //System.out.println(data.getDate_class2());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                Context context = view.getContext();
               /* Intent intent = new Intent(context, Add_class_to_user.class);
                intent.putExtra("date_class", listdata.get(position).getDate_class());
                intent.putExtra("teacher", listdata.get(position).getTeacher());
                intent.putExtra("room_number", listdata.get(position).getRnumber());
                intent.putExtra("post_key", listdata.get(position).getUid());

               context.startActivity(intent);*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView vdate_class, vteacher, vrnumber;

        public MyHolder(View itemView) {
            super(itemView);
            vdate_class = itemView.findViewById(R.id.date_class_name);
            vteacher = itemView.findViewById(R.id.teacher);
            vrnumber = itemView.findViewById(R.id.room_number);

        }
    }

}

