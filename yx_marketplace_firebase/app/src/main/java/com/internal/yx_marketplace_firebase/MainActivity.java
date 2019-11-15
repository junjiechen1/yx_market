package com.internal.yx_marketplace_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    public EditText emailId,password;
    Button loginbutton;
    Button signupbutton;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthstateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.EmailText);
        password = findViewById(R.id.PasswordText);
        signupbutton = findViewById(R.id.button2);
        loginbutton = findViewById(R.id.button);
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if (email.isEmpty()) {
                    emailId.setError("please enter email");
                    emailId.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("please enter password");
                    password.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "email and password is empty", Toast.LENGTH_LONG).show();
                } else if (!email.isEmpty() && !pwd.isEmpty()) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Sign up unsuccessful, please try again ", Toast.LENGTH_LONG).show();
                            } else {
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "error occured", Toast.LENGTH_LONG).show();
                }
            }
        });
        mAuthstateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseuser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseuser!=null ) {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));

                }
                else{
                    Toast.makeText(MainActivity.this, "sign up unsuccessful, please try again ", Toast.LENGTH_LONG).show();
                }
            }
        };
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if (email.isEmpty()) {
                    emailId.setError("please enter email");
                    emailId.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("please enter password");
                    password.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "email and password is empty", Toast.LENGTH_LONG).show();
                } else if (!email.isEmpty() && !pwd.isEmpty()) {
                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "log in unsuccessful, please try again ", Toast.LENGTH_LONG).show();
                            }
                            else{
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                            }
                        }

                    });

                } else {
                    Toast.makeText(MainActivity.this, "error occured", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

}
