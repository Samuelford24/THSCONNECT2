package com.samuelford48gmail.thsconnect.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.onesignal.OneSignal;
import com.samuelford48gmail.thsconnect.R;
import com.samuelford48gmail.thsconnect.SignupActivity;
import com.samuelford48gmail.thsconnect.User;
import com.samuelford48gmail.thsconnect.UtilMethods;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity2 extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputGrade, inputName, inputID, inputHR;     //hit option + enter if you on mac , for windows hit ctrl + enter
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private String hr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignIn = findViewById(R.id.sign_in_button);
        btnSignUp = findViewById(R.id.sign_up_button);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        btnResetPassword = findViewById(R.id.btn_reset_password);
        inputGrade = findViewById(R.id.grade);
        inputName = findViewById(R.id.name);
        inputID = findViewById(R.id.studentID);
        inputHR = findViewById(R.id.homeroom);

      /*  btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
            }
        });
*/
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();
                final String name = inputName.getText().toString().trim();
                final String grade = inputGrade.getText().toString().trim();
                final String studentID = inputID.getText().toString().trim();
                final String homeroom = inputHR.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter a email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter a password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(homeroom)) {
                    Toast.makeText(getApplicationContext(), "Enter a homeroom!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(studentID)) {
                    Toast.makeText(getApplicationContext(), "Enter a StudentID!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity2.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //  Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity2.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    final String create_uid = FirebaseAuth.getInstance().getUid();
                                    final FirebaseUser user2 = auth.getCurrentUser();
                                    user2.sendEmailVerification();
                                    User user = new User(name, email, grade, create_uid, studentID, homeroom.toUpperCase());
                                    OneSignal.setEmail(email);
                                  /*  Map<String, Object> user = new HashMap<>();
                                    user.put("name", name);
                                    user.put("grade", grade);
                                    user.put("email", email);
                                    user.put("homeroom", homeroom.toUpperCase());
                                    user.put("studentID", studentID);
                                    user.put("uid", create_uid);*/
                                    FirebaseFirestore.getInstance().collection("Users")
                                            .document(create_uid)
                                            .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {


                                            user2.sendEmailVerification();

                                            String b = homeroom.toUpperCase();
                                            int value = b.charAt(0);
                                            //int asciiValue = (int) value;
                                            System.out.println(value);
                                            if (value >= 65 && value < 67) {
                                                hr = "HR1";
                                                Toast.makeText(getApplicationContext(), homeroom,
                                                        Toast.LENGTH_SHORT).show();
                                            } else if (value <= 70) {
                                                hr = "HR2";
                                                Toast.makeText(getApplicationContext(), homeroom,
                                                        Toast.LENGTH_SHORT).show();
                                            } else if (value <= 76) {
                                                hr = "HR3";
                                                Toast.makeText(getApplicationContext(), homeroom,
                                                        Toast.LENGTH_SHORT).show();
                                            } else if (value <= 79) {
                                                hr = "HR4";
                                                Toast.makeText(getApplicationContext(), homeroom,
                                                        Toast.LENGTH_SHORT).show();
                                            } else if (value <= 83) {
                                                hr = "HR5";
                                                Toast.makeText(getApplicationContext(), homeroom,
                                                        Toast.LENGTH_SHORT).show();
                                            } else if (value <= 90) {
                                                hr = "HR6";
                                                Toast.makeText(getApplicationContext(), homeroom,
                                                        Toast.LENGTH_SHORT).show();
                                            } else {
                                                hr = "Incorrect Format";
                                            }
                                            System.out.println(hr);
                                            addHomeroom(b, create_uid);

                                            // progressBar.setVisibility(View.GONE);
                                            //   Toast.makeText(SignupActivity2.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                            //   startActivity(new Intent(getApplicationContext(), com.samuelford48gmail.thsconnect.teacher.LoginActivity.class));
                                            finish();
//
                                        }
                                    })
                                            .addOnFailureListener(new OnFailureListener() {

                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    auth.getCurrentUser().delete();
                                                    UtilMethods.showErrorMessage(getApplicationContext(), "Error", e.getMessage());
                                                }
                                            });


                                }

                            }
                        });
            }
        });
    }

    private void addHomeroom(String b, String create_uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", create_uid);
        FirebaseFirestore.getInstance().collection(hr).document(b).collection("Students").document(create_uid).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {


                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignupActivity2.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                } else {
                    Log.i("Signup", "Error adding user to homeroom");
                    UtilMethods.showErrorMessage(getApplicationContext(), "Error", "Check your connection and try again later");
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}