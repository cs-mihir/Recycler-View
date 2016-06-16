package com.example.mihir.recview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowSolutionActivity extends AppCompatActivity {

    private TextView solnTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_solution);
        Intent i = getIntent();

        String solnStr = i.getStringExtra("solution");
        solnTextView = (TextView) findViewById(R.id.soln_text_view);
        solnTextView.setText(solnStr);
        //solnTextView.setTextColor(0x000000);

    }
}
