package com.samuelford48gmail.thsconnect;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Settings extends Fragment {
    private FirebaseDatabase database;
    private  DatabaseReference myRef;
    public Settings() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting, container, false);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
        ;

        ListView lv = (ListView) view.findViewById(R.id.listview);
        final List<String> arrayList = new ArrayList<String>();


        arrayList.add("Report a bug/Support");
        arrayList.add("Questions?");
        arrayList.add("Signout");
        arrayList.add("Your info");
        arrayList.add("Delete Account");
        arrayList.add("Privacy Policy");
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

                if (item.equals("Report a bug/Support")) {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(getContext());
                    //builder.setIcon(R.drawable.open_browser);
                    builder.setTitle("Report a problem/Support");
                    builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            goToUrl("https://forms.gle/kqBEcPDD8axhevcj8");
                        }
                    });
                    builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    builder.setCancelable(true);
                    builder.show();
                }

                if (item.equals("Questions?")) {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(getContext());
                    //builder.setIcon(R.drawable.open_browser);
                    builder.setTitle("Email us at svhsdev@vigoschools.org");
                    builder.setNegativeButton("Ok",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    builder.setCancelable(true);
                    builder.show();
                }


                if (item.equals("Signout")) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);

                }
                if (item.equals("Your info")){
                    Intent intent = new Intent(getContext(), your_info.class);
                    startActivity(intent);
                }
                if (item.equals("Privacy Policy/ Terms and Conditions")){
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(getContext());
                    //builder.setIcon(R.drawable.open_browser);
                    builder.setTitle("Report a problem/Support");
                    builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            goToUrl("https://forms.gle/8B4Vs2LHqrsriSkv7");
                        }
                    });
                    builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    builder.setCancelable(true);
                    builder.show();

                }
                if (item.equals("Delete Account")){
                    final String key = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    myRef = database.getReference("Users").child(key);
                    myRef.removeValue();
                    final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    AuthCredential authCredential = EmailAuthProvider.getCredential("user@example.com", "password1234");

                    firebaseUser.reauthenticate(authCredential).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        Intent intent = new Intent(getContext(), LoginActivity.class);
                                        startActivity(intent);
                                        Log.d("setting", "User account deleted!");
                                    }
                                }
                            });
                        }
                    });

                }
            }
        });
        return view;

    }
    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
        Toast.makeText(getContext(),"Loading...",
                Toast.LENGTH_LONG).show();
//                Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();

    }
}
