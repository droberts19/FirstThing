package com.example.droberts19.firstthing;

import android.content.Context;
import android.widget.CheckBox;

public class MyCheckBox extends CheckBox {
    MyCheckBox(Context context) {
        super(context);
    }

    @Override
    public String toString() {
        return this.getText().toString();
    }
}
