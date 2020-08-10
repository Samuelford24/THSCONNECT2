package com.samuelford48gmail.thsconnect;

import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UtilMethods extends AppCompatActivity {
    private static final String DATE_FORMAT = "MM-dd";

    //using a method from this class with context passed as  a parameter causes an error
    public static boolean isDateValid(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static void showErrorMessage(Context context, String title, String message) {
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        //builder.setIcon(R.drawable.open_browser);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(true);
        builder.show();

    }

    public static String convertToString(List<String> dates) {
        String dateString = "";
        if (dates != null) {
            for (String s : dates) {
                dateString += s + ",";

            }
            return dateString.substring(0, dateString.length() - 1);
        }
        return "";
    }

    public static Class_model getClassInfo(final String class_id) {
        final Class_model[] class_model = new Class_model[1];
        FirebaseFirestore.getInstance().collection("Classes").document(class_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                System.out.println(task.getResult());

                if (task.isSuccessful()) {
                    System.out.println("Execute");
                    // class_model[0] = new Class_model(value.get("date_clasname").toString(), value.get("teacher").toString(), value.get("room_number").toString(), value.get("id").toString(), value.get("subject").toString());
                    Class_model class_model2 = task.getResult().toObject(Class_model.class);

                } else {
                    FirebaseFirestore.getInstance().collection("Classes").document(class_id).delete();
                }


            }

        });
        System.out.println(class_model[0].getTeacher());
        return class_model[0];

    }

    public static User getUserInfo(final String uid) {
        final User[] user = new User[1];
        FirebaseFirestore.getInstance().collection("Users").document(uid).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.exists()) {
                    user[0] = value.toObject(User.class);
                    System.out.println(user[0].getName());
                } else {
                    FirebaseFirestore.getInstance().collection("Users").document(uid).delete();
                }
            }
        });
        if (user[0] != null) {
            return user[0];
        } else {
            return null;
        }
    }

    public static void removeStudentFromClass(CollectionReference collectionReference, String uid) {
        collectionReference.document(uid).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
    }

    public static void removeClassFromStudent(String uid, String classID) {
        FirebaseFirestore.getInstance().collection("Users").document(uid).collection("Classes").document(classID).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                }
            }
        });
    }

    public static void removeClass(String id) {
        FirebaseFirestore.getInstance().collection("Classes").document(id).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                }
            }
        });
    }
}
