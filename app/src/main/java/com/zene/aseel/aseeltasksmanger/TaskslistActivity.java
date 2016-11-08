package com.zene.aseel.aseeltasksmanger;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zene.aseel.aseeltasksmanger.data.MyAdapterTask;
import com.zene.aseel.aseeltasksmanger.data.MyTask;

public class TaskslistActivity extends AppCompatActivity {

    private EditText etAdd;
    private Button btnAdd;
    private MyAdapterTask adapterTask;
    private ListView ivTasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskslist);


        etAdd = (EditText) findViewById(R.id.etText);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        ivTasks = (ListView) findViewById(R.id.ivTasks);
        adapterTask = new MyAdapterTask(this, R.layout.item_my_task);
        ivTasks.setAdapter(adapterTask);
        initListView();
        eventHandler();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logOut:
                FirebaseAuth.getInstance().signOut();

                Intent i = new Intent(this, LogInActivity.class);
                startActivity(i);
                break;
            case R.id.setting:
                Toast.makeText(getBaseContext(), "Settings", Toast.LENGTH_LONG).show();

        }
        return true;
    }



    private void dataHandler() {
        String stAdd = etAdd.getText().toString();
        boolean isok=true;


        if (stAdd.length() == 0) {
            etAdd.setError("Wrong Add");
            isok = false;
        }
        if (isok){
            Intent i = new Intent(TaskslistActivity.this, AddTasksActivity.class);
             startActivity(i);


        }

    }

    private void eventHandler() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TaskslistActivity.this, AddTasksActivity.class);
               startActivity(i);

                dataHandler();


            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        initListView();
    }

    private void initListView(){
        String email= FirebaseAuth.getInstance().getCurrentUser().getEmail().replace('.','_');
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference(email);
        reference.child("my Tasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapterTask.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren());
                {
                    MyTask myTask= dataSnapshot.getValue(MyTask.class);
                    myTask.setAddress(dataSnapshot.getKey());
                    adapterTask.add(myTask);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
