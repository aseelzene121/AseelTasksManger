package com.zene.aseel.aseeltasksmanger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etpassword;
    private Button btnSignIn, btnNewAcount;
    private Button btnSignup;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //2.


        etEmail = (EditText) findViewById(R.id.etEmail);
        etpassword = (EditText) findViewById(R.id.etPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnNewAcount = (Button) findViewById(R.id.btnSignup);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        eventHandler();
        auth = FirebaseAuth.getInstance();

    }

    /**
     * 1.getting data from the ui from(edittext ,checkbox...)
     * 2.checking data (the email text is ok, the password...)
     * 3.dealing with the data
     */


    private void dataHandler() {
        //1.getting data
        String stEmail = etEmail.getText().toString();
        String stPassword = etpassword.getText().toString();
        boolean isOk = true;
        //2.checking
        if (stEmail.length() < 3) {
            etEmail.setError("Worng Email");
            isOk = false;
        }
        if (stPassword.length() < 3) {
            etpassword.setError("Wrong Password");
            isOk = false;
        }
        if (isOk)
            signin(stEmail,stPassword);


    }
    private void signin(String email,String passw){
        auth.signInWithEmailAndPassword(email, passw).addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(LogInActivity.this,"signIn Successful",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(LogInActivity.this,TaskslistActivity.class);
                    startActivity(i);
                    finish();

                }
                else
                {
                    Toast.makeText(LogInActivity.this,"signIn faild"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });

    }


    public void eventHandler() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             dataHandler();
                Intent i=new Intent(LogInActivity.this,TaskslistActivity.class);
            }

        });


        btnNewAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LogInActivity.this, SignupActivity.class);
                startActivity(i);

            }



        });
        //**FirebaseAuth.getInstance().signOut();

    }



}




