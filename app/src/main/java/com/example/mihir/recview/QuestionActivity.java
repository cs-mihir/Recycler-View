package com.example.mihir.recview;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import android.support.design.widget.Snackbar;
//import android.support.design.widget.CoordinatorLayout;

public class QuestionActivity extends AppCompatActivity {

    private Snackbar s;
    //In this String we will store our whole JSON file
    private String jsonObject="";
    //Reader to read JSON File from assets folder
    private BufferedReader reader;
    //Counter to decide on which question currently user is on
    private int counterOfQuestion=0;

    /*
     *This is for our JSON Objcts and Arrays
     */

    //Main JSON Object
    private JSONObject mainObject;
    //Main Array of practice sets
    private JSONArray quizzes;
    //Array of questions in a particular practice set
    private JSONArray questionArrayInQuiz;
    //Particular Question Object in a particular Array of Questions
    private JSONObject questionToBeShown;

    //TextView for displaying Question
    private TextView questionInfo;
    //TextView for displaying a Question's label
    private TextView questionID;

    //Button Objects for all our buttons
    private Button optA;
    private Button optB;
    private Button optC;
    private Button optD;

    //Answer of any Question
    public String answer="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Intent i = getIntent();
        int quizNo = i.getIntExtra("position",0);
        questionInfo = (TextView) findViewById(R.id.info_question_text_view);
        TextView t2 = (TextView) findViewById(R.id.info_parent_quiz_text_view);

        questionInfo.setText("Not successful");


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

            mainObject = new JSONObject(jsonObject);
            quizzes = mainObject.getJSONArray("quiz");
            questionArrayInQuiz = quizzes.getJSONObject(quizNo-1).getJSONArray("questions");
            questionToBeShown = questionArrayInQuiz.getJSONObject(counterOfQuestion);

            questionInfo.setText(questionToBeShown.getString("question"));
            questionID = (TextView) findViewById(R.id.question_id_text_view);
            int temp=counterOfQuestion;
            temp+=1;
            questionID.setText("Question "+temp+":");

            t2.setText("Practice Set "+quizNo);

            optA = (Button) findViewById(R.id.optionA);
            optB = (Button) findViewById(R.id.optionB);
            optC =(Button) findViewById(R.id.optionC);
            optD=(Button) findViewById(R.id.optionD);
            answer = questionToBeShown.getString("answer");

            optA.setText(questionToBeShown.getString("optionA"));
            optB.setText(questionToBeShown.getString("optionB"));
            optC.setText(questionToBeShown.getString("optionC"));
            optD.setText(questionToBeShown.getString("optionD"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void nextQuestion(View v) {
        int temp = counterOfQuestion;
        if(this.counterOfQuestion == (questionArrayInQuiz.length()-1)) {
            s = Snackbar.make(v,"You have reached last question", Snackbar.LENGTH_INDEFINITE);

            s.setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s.dismiss();
                }
            });
            View sbView = s.getView();
            sbView.setBackgroundColor(Color.parseColor("#3F51B5"));
            s.show();
        }
        else {
            counterOfQuestion+=1;
            temp=counterOfQuestion+1;
            try {
                questionToBeShown=questionArrayInQuiz.getJSONObject(counterOfQuestion);
                questionInfo.setText(questionToBeShown.getString("question"));
                questionID.setText("Question "+temp+":");
                answer = questionToBeShown.getString("answer");
                optA.setText(questionToBeShown.getString("optionA"));
                optB.setText(questionToBeShown.getString("optionB"));
                optC.setText(questionToBeShown.getString("optionC"));
                optD.setText(questionToBeShown.getString("optionD"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public void previousQuestion(View v) {
        int temp = counterOfQuestion;
        if(this.counterOfQuestion == 0) {
            s = Snackbar.make(v,"You are on the first question", Snackbar.LENGTH_INDEFINITE);

            s.setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s.dismiss();
                }
            });
            View sbView = s.getView();
            sbView.setBackgroundColor(Color.parseColor("#3F51B5"));
            s.show();
        }
        else {
            counterOfQuestion-=1;
            temp=counterOfQuestion+1;
            try {
                questionToBeShown=questionArrayInQuiz.getJSONObject(counterOfQuestion);
                questionInfo.setText(questionToBeShown.getString("question"));
                questionID.setText("Question "+temp+":");
                answer = questionToBeShown.getString("answer");
                optA.setText(questionToBeShown.getString("optionA"));
                optB.setText(questionToBeShown.getString("optionB"));
                optC.setText(questionToBeShown.getString("optionC"));
                optD.setText(questionToBeShown.getString("optionD"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    public void showSolution(View v) {
        try {

            String solution = questionToBeShown.getString("solution");
            Intent i = new Intent(QuestionActivity.this,ShowSolutionActivity.class);
            i.putExtra("solution",solution);
            startActivity(i);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void checkAnswerA(View V) {
        if(answer.equals("Option A")) {
            this.nextQuestion(V);
        }
        else {
            s = Snackbar.make(V,"You have given Wrong answer", Snackbar.LENGTH_INDEFINITE);

            s.setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s.dismiss();
                }
            });
            View sbView = s.getView();
            sbView.setBackgroundColor(Color.parseColor("#3F51B5"));
            s.show();
        }

    }

    public void checkAnswerB(View V) {
        if(answer.equals("Option B")) {
            this.nextQuestion(V);
        }
        else {
            s = Snackbar.make(V,"You have given Wrong answer", Snackbar.LENGTH_INDEFINITE);

            s.setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s.dismiss();
                }
            });
            View sbView = s.getView();
            sbView.setBackgroundColor(Color.parseColor("#3F51B5"));
            s.show();
        }
    }

    public void checkAnswerC(View V) {
        if(answer.equals("Option C")) {
            this.nextQuestion(V);
        }
        else {
            s = Snackbar.make(V,"You have given Wrong answer", Snackbar.LENGTH_INDEFINITE);

            s.setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s.dismiss();
                }
            });
            View sbView = s.getView();
            sbView.setBackgroundColor(Color.parseColor("#3F51B5"));
            s.show();
        }
    }

    public void checkAnswerD(View V) {
        if(answer.equals("Option D")) {
            this.nextQuestion(V);
        }
        else {
            s = Snackbar.make(V,"You have given Wrong answer", Snackbar.LENGTH_INDEFINITE);

            s.setAction("Dismiss", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    s.dismiss();
                }
            });
            View sbView = s.getView();
            sbView.setBackgroundColor(Color.parseColor("#3F51B5"));
            s.show();
        }
    }
}
