package com.samuelford48gmail.thsconnect.teacher;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.samuelford48gmail.thsconnect.Admin_fragment;
import com.samuelford48gmail.thsconnect.Classes_fragment;
import com.samuelford48gmail.thsconnect.HR_fragment;
import com.samuelford48gmail.thsconnect.R;
import com.samuelford48gmail.thsconnect.Settings;
import com.samuelford48gmail.thsconnect.admin_activity;
import com.samuelford48gmail.thsconnect.home_fragment;

public class MainActivity2 extends AppCompatActivity {
    private Button signout2;
    private TextView mTextMessage;
    private FirebaseAuth auth;
    final Fragment fragment1 = new home_fragment2();
    final Fragment fragment2 = new Classes_fragment();
    final Fragment fragment3 = new Settings2();
    final Fragment fragment4 = new admin_activity();
    final Fragment fragment5 = new HR_fragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fm.beginTransaction().add(R.id.main_container, fragment5, "5").hide(fragment5).commit();
        fm.beginTransaction().add(R.id.main_container, fragment4, "4").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.main_container, fragment1, "1").commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   // mTextMessage.setText(R.string.title_home);
                    fm.beginTransaction().hide(active).show(fragment1).commit();

                    active = fragment1;
                    fm.popBackStack();
                    return true;
                case R.id.navigation_dashboard:
                   // mTextMessage.setText(R.string.title_dashboard);
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    fm.popBackStack();
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    fm.beginTransaction().hide(active).show(fragment3).commit();
                    active = fragment3;
                    fm.popBackStack();
                    return true;
                case R.id.admin:
                    fm.beginTransaction().hide(active).show(fragment4).commit();
                    active = fragment4;
                    fm.popBackStack();
                    return true;
                case R.id.Homeroom:
                    fm.beginTransaction().hide(active).show(fragment5).commit();
                    active = fragment5;
                    fm.popBackStack();
                    return true;
            }
            return false;
        }
    };

  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signout2 = (Button) findViewById(R.id.sign_out);

        signout2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(com.samuelford48gmail.thsconnect.teacher.MainActivity.this, LoginActivity.class));
                finish();
            }
        });


        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user == null) {
                // user auth state is changed - user is null
                // launch login activity
                startActivity(new Intent(com.samuelford48gmail.thsconnect.teacher.MainActivity.this, LoginActivity.class));
                finish();
            }
        }


    };
    // public void signout (View v){
    //    signOut();
    //}
/*
    public void signOut() {
        auth.signOut();


// this listener will be called when there is change in firebase user session
       FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(com.samuelford48gmail.thsconnect.teacher.MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

    }


    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    */
  /*@Override
  public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu., menu);
      return super.onCreateOptionsMenu(menu);
  }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(com.samuelford48gmail.thsconnect.teacher.MainActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
}


