package com.samuelford48gmail.thsconnect;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class page_fragment3 extends Fragment {
    public page_fragment3() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting, container, false);
        ListView lv = (ListView) view.findViewById(R.id.listview);
        List<String> arrayList = new ArrayList<String>();
        arrayList.add("Help?");
        arrayList.add("Problems with the app?");
        arrayList.add("Questions?");
        arrayList.add("Signout");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                arrayList);

        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String item = ((TextView) view).getText().toString();
                if (item.equals("Help?")) {
                    Toast.makeText(getContext(), "fgdg", Toast.LENGTH_SHORT).show();
                }
                if (item.equals("Problems with the app?")) {
                    Toast.makeText(getContext(), "fgdg", Toast.LENGTH_SHORT).show();
                }
                if (item.equals("Questions?")) {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(getContext());
                    // builder.setIcon(R.drawable.open_browser);
                    builder.setTitle("          Email svhsdev@vigoschools.org!");
                  builder.setCancelable(true);
                  builder.show();


                    }


                if (item.equals("Signout")) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);

                }
            }
        });
        return view;

    }
}
