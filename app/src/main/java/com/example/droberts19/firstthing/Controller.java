package com.example.droberts19.firstthing;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import java.io.PrintWriter;
import java.util.ArrayList;
import android.support.v7.widget.AppCompatCheckBox;

public class Controller {
    private EditText inputNew;
    private ListView viewList;
    private ArrayAdapter<CheckBox> arrayAdapter;
    private Context context;

    private Model model;

    Controller(EditText InputNew, ListView ViewList, final Context c) {
        inputNew = InputNew;
        viewList = ViewList;
        arrayAdapter = (ArrayAdapter<CheckBox>)viewList.getAdapter();
        context = c;

        model = new Model(context);
        inputNew.setText(model.getInputNewText());
        inputNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNewReady();
            }
        });

        ArrayList viewListTexts = model.getViewListTexts();
        for (int i = 0; i < viewListTexts.size(); i++) {
            final MyCheckBox newCheckBox1 = new MyCheckBox(context);
            if (newCheckBox1.isChecked()) {
                arrayAdapter.remove(newCheckBox1);
            }

        }
        inputNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MyCheckBox newCheckBox1 = new MyCheckBox(context);
                arrayAdapter.add(newCheckBox1);
                System.out.println(inputNew.getText());
                newCheckBox1.setText(inputNew.getText());
                System.out.println(newCheckBox1);
                arrayAdapter.notifyDataSetChanged();
                newCheckBox1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrayAdapter.remove(newCheckBox1);
                    }
                });
                inputNew.setText("");
            }
        });
    }

    void save() {
        model.setAllData(inputNew.getText().toString(), arrayAdapter);
        model.save();
    }

    public void inputNewReady() {
        final MyCheckBox newCheckBox1 = new MyCheckBox(context);
        arrayAdapter.add(newCheckBox1);
        System.out.println(inputNew.getText());
        newCheckBox1.setText(inputNew.getText());
        System.out.println(newCheckBox1);
        arrayAdapter.notifyDataSetChanged();
        newCheckBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayAdapter.remove(newCheckBox1);
            }
        });
        inputNew.setText("");
    }

    public void resetViewList() {
        arrayAdapter.clear();
        inputNew.setText("");
        try {
            new PrintWriter("NewThing.txt").close();
        } catch (Exception e) {
        }
    }
}
