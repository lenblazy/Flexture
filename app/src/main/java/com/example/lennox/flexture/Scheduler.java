package com.example.lennox.flexture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.List;

public class Scheduler extends AppCompatActivity {

    TableLayout table_classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
        init();
    }

    private void init() {
        table_classes = findViewById(R.id.title_classes);
    }

    @Override
    protected void onStart() {
        super.onStart();
        addDataToTable(table_classes);
    }

    private void addDataToTable(TableLayout tableClasses) {

        List<Classes> classes = DBHandler.getClasses(this);
        // a list to retrieve classes from the database

        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        // for arranging the values in the table



    }
}
