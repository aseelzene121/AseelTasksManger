package com.zene.aseel.aseeltasksmanger.data;

import android.content.Context;
import android.media.Rating;
import android.text.method.CharacterPickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zene.aseel.aseeltasksmanger.R;

/**
 * Created by user on 10/30/2016.
 */
public class MyAdapterTask extends android.widget.ArrayAdapter {
    public MyAdapterTask(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_my_task, parent, false);
        TextView tvItemText = (TextView) convertView.findViewById(R.id.tvItemText);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        ImageButton btncall = (ImageButton) convertView.findViewById(R.id.btnItemCall);
        TextView tvItempone = (TextView) convertView.findViewById(R.id.tvItempone);
        final MyTask myTask = (MyTask) getItem(position);
        tvItemText.setText(myTask.getPhone());
        tvTitle.setText(myTask.getTitle());
        tvItempone.setText(myTask.getAddress());
        
        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MAKE CALL

            }
        });
        return convertView;
    }
}



