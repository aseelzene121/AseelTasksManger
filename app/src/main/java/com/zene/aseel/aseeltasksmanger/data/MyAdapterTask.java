package com.zene.aseel.aseeltasksmanger.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.zene.aseel.aseeltasksmanger.R;

/**
 * Created by user on 10/30/2016.
 */
public class MyAdapterTask extends android.widget.ArrayAdapter {
    public MyAdapterTask(Context context, int resource) {
        super(context, resource);
    }
    private DatabaseReference reference;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_my_task, parent, false);
        CheckBox cbCheck=(CheckBox) convertView.findViewById(R.id.cbCheck);
        TextView tvItemText = (TextView) convertView.findViewById(R.id.tvAddress);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        ImageButton btncall = (ImageButton) convertView.findViewById(R.id.ibPhone);
        ImageButton btnDel=(ImageButton)    convertView.findViewById(R.id.btnDel);
        TextView tvDate= (TextView) convertView.findViewById(R.id.tvDate);
        RatingBar rtbItemPriority=(RatingBar)convertView.findViewById(R.id.rbPriority);
        final MyTask myTask = (MyTask) getItem(position);
        tvItemText.setText(myTask.getAddress());
        tvTitle.setText(myTask.getTitle());
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //**delete from the firebase sever
                reference.child(myTask.getId()).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError==null) //deleted
                        {
                            Toast.makeText(getContext(),"Deleted!" ,Toast.LENGTH_LONG).show();
                            //delete from this adapter
                            remove(myTask);
                            setNotifyOnChange(true);//to update the list view
                        }
                    }
                });
                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                email = email.replace(".", "_");
                reference= FirebaseDatabase.getInstance().getReference(email).child("MyTasks");
            }
        });
        //del 02
       // tvDate.setText(myTask.getPhone());
        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Call",Toast.LENGTH_LONG).show();
                //MAKE CALL

            }
        });
        rtbItemPriority.setRating(myTask.getPriority());
        cbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                myTask.setIsCompleted(b);
                Toast.makeText(getContext(),"Checked"+""+ b,Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }
}



