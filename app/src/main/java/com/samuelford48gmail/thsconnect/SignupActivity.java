package com.samuelford48gmail.thsconnect;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

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

              final   String email = inputEmail.getText().toString().trim();
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
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                              //  Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    final String create_uid = FirebaseAuth.getInstance().getUid();
                                    FirebaseUser user2 = auth.getCurrentUser();
                                    user2.sendEmailVerification();
                                    User user = new User(name, email, grade, create_uid, studentID, homeroom.toUpperCase());
                                   // OneSignal.setEmail(email);
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(create_uid).child("User_info")
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {


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
                                                DatabaseReference myref = FirebaseDatabase.getInstance().getReference(hr).child(b).child(create_uid);
                                                System.out.println(myref);
                                                myref.setValue(create_uid);
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(SignupActivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                                finish();
                                            } else {
                                                //display a failure message
                                            }
                                        }
                                    });

                                }

                                }
                            });
                        }

            });
        }


    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}