package com.zene.aseel.aseeltasksmanger;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.zene.aseel.aseeltasksmanger.data.MyTask;

public class AddTasksActivity extends AppCompatActivity {

    private EditText etPhone;
    private EditText btnLocation;
    private Button btnContacts;
    private ImageButton idLocation;
    private RatingBar rtBarPriority;
    private Button btSave;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);

        editText = (EditText) findViewById(R.id.etText);
        etPhone = (EditText) findViewById(R.id.etPhone);
        btnLocation = (EditText) findViewById(R.id.btnLocation);
        btnContacts = (Button) findViewById(R.id.btnContacts);
        btSave = (Button) findViewById(R.id.btnSave);
        idLocation = (ImageButton) findViewById(R.id.idLocation);
        rtBarPriority =(RatingBar) findViewById(R.id.rtBarPriority);

        eventhander();

    }

    /**
     * 1.getting data from the ui from(edittext ,checkbox...)
     * 2.checking data (the email text is ok, the password...)
     * 3.dealing with the data
     */

    private void dataHandler() {

        String stText = editText.getText().toString();
        String stPhone = etPhone.getText().toString();
        String stLocatin = btnLocation.getText().toString();
        float stBarPriority = rtBarPriority.getRating();
        boolean isOk = true;

        if (stText.length() == 0) {
            editText.setError("Wrong Text");
            isOk = false;
        }
        if (stPhone.length() == 0) {
            etPhone.setError("Wrong Phone");
            isOk = false;
        }
        if (stLocatin.length() == 0) {
            btnLocation.setError("Wrong Location");
            isOk = false;
        }


        if (isOk)
        {
            //isOk
            MyTask myTask = new MyTask();
            myTask.setPhone(stPhone);
            myTask.setAddress(stLocatin);
            myTask.setTitle(stText);
            myTask.setPriority(stBarPriority);
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            //get current user email

            String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            email = email.replace(".", "_");
            //all my task will be under my email under the root MyTasks
            //child can not contain chars: $,#,.,...
           // MyTask m = new MyTask();

            reference.child(email).child("my Tasks").push().setValue(myTask, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                    if (databaseError == null) {
                        Toast.makeText(getBaseContext(), "save ok", Toast.LENGTH_LONG).show();
                        finish();// finish an exit this activity

                    } else {
                        Toast.makeText(getBaseContext(), "save Err" + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                        //reference.setValue("hello aseel");


                    }
                }


            });
        }

    }
            public void eventhander () {
            btnContacts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                }
            });
            btSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dataHandler();

                }
            });


            btnLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
    }



}














