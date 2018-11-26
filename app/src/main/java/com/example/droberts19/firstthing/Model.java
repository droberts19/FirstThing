package com.example.droberts19.firstthing;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Model {
    private Context context;

    private String inputNewText;
    private ArrayList<String> viewListTexts;

    Model(Context context1) {
        context = context1;

        inputNewText = "";
        viewListTexts = new ArrayList();

        try {
            File savedText = new File(context.getFilesDir(), "NewThing.txt");
            if (savedText.exists()) {
                BufferedReader input = new BufferedReader(new FileReader(savedText));

                inputNewText = input.readLine();
                System.out.println("Model() read inputNewText:" + inputNewText);
                String newViewListTexts1 = input.readLine();
                int i = 1;
                System.out.println("Model() read viewListTexts " + i++ + ":" + newViewListTexts1);
                while (newViewListTexts1 != null) {
                    viewListTexts.add(newViewListTexts1);
                    newViewListTexts1 = input.readLine();
                    System.out.println("Model() read viewListTexts " + i++ + ":" + newViewListTexts1);
                }
                input.close();
            }
        } catch (Exception e) {
            System.out.println("Model() threw exception");
            e.printStackTrace();
        }
    }

    void save() {
        try {
            File savedText = new File(context.getFilesDir(), "NewThing.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(savedText));
            if (writer != null) {
                if (inputNewText != null) {
                    writer.write(inputNewText);
                }
                writer.newLine();
                int length1 = viewListTexts.size();
                if (length1 > 0) {
                    for (int i = 0; i < length1; i++) {
                        writer.write(viewListTexts.get(i));
                        writer.newLine();
                    }
                }
            }
            writer.close();
        } catch (Exception e) {
        }
    }

    String getInputNewText() {
        return inputNewText;
    }

    ArrayList<String> getViewListTexts() {
        return viewListTexts;
    }

    void setAllData(String updatedInputNewText, ArrayAdapter<   CheckBox> viewListCBs) {

        inputNewText = updatedInputNewText;

        int length1 = viewListCBs.getCount();
        viewListTexts.clear();
        for (int i = 0; i < length1; i++) {
            viewListTexts.add((viewListCBs.getItem(i)).getText().toString());
        }
    }
}