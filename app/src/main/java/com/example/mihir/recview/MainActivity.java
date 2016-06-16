package com.example.mihir.recview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String jsonObject="";
    private BufferedReader reader;
    private int totalQuizzes;
    private JSONArray quizzes;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open("practiceSet.json")));
            String temp="";
            while((temp=reader.readLine()) != null) {
                jsonObject += temp;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {

            JSONObject mainObject = new JSONObject(jsonObject);
            quizzes = mainObject.getJSONArray("quiz");
            totalQuizzes = quizzes.length();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        RecyclerView recview = (RecyclerView) findViewById(R.id.rec_view);
        RecyclerView.Adapter mData = new MyRecyclerViewAdapter(this.setArrayOfAdapter(totalQuizzes),this.getApplicationContext());
        recview.setAdapter(mData);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recview.setLayoutManager(mLayoutManager);

    }

    public ArrayList<ListDataObject> setArrayOfAdapter(int totalNumberOfQuizzes) {

        ArrayList<ListDataObject> list = new ArrayList<ListDataObject>();

        for(int i = 0;i < totalNumberOfQuizzes; i++){
            JSONObject currentPracticeSet;
            String description="Description: ";
            try {
                currentPracticeSet = quizzes.getJSONObject(i);
                description += currentPracticeSet.getString("description");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String str="Practice Set "+(i+1);
            ListDataObject object = new ListDataObject(str,i+1,description);
            list.add(object);
        }

        return list;
    }
}
