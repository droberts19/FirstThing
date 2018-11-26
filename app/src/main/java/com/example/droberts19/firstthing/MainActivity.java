package com.example.droberts19.firstthing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Controller control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText et = findViewById(R.id.inputNew);
        ListView lv = findViewById(R.id.viewList);
        ArrayAdapter<CheckBox> arrayAdapter = new ArrayAdapter<CheckBox>(this, android.R.layout.simple_list_item_multiple_choice, new ArrayList<CheckBox>());
        lv.setAdapter(arrayAdapter);

        control = new Controller(et, lv, getApplicationContext());
    }

    @Override
    protected void onStop() {
        control.save();
        super.onStop();
    }
}
