package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3, rb4;
    TextView tvQuestion;
    Button btnNext;
    ArrayList<Question> list;
    private int scoure = 0;
    private int COUNTER = 0;
    private int radioButtonId;
    String radioText;
    ArrayList<String> trueAnsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Registeration of views

        registerViews();
//Adding Questions And Answer in the Array list through Question modole
        addQuestionInList();
        //Adding True Answer to the arraay list
        truAnsList();
//set the Question Answer in UI at index [COUNTER] that is zero initially
        replaceQuestion();
  // get The selected radio Button text and compare with true answer list
        getRadioTextAndCheck();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //First uncheck the selected button
                unchecked();
                // increace the arrayList indux by 1
                COUNTER++;
                //check ArrayList indux is less than list size than enter to block
                if (COUNTER < list.size()) {
               //Replace The Next Question and answer on every click
                    replaceQuestion();

                    // get Selected RadioButton Text And Check
                    getRadioTextAndCheck();

                } else {
                    //Go to next Activity to show result
                    Intent i = new Intent(getApplicationContext(), ResultActivity.class);
                    i.putExtra("correct", scoure + "");
                    startActivity(i);

                }
            }
        });

    }

    private void truAnsList() {
        trueAnsList = new ArrayList<String>();
        trueAnsList.add("Google");
        trueAnsList.add("Kotlin");
        trueAnsList.add("C");
        trueAnsList.add("Android");
        trueAnsList.add("Pie");

    }

    private void getRadioTextAndCheck() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton1 = radioGroup.findViewById(radioButtonId);
                if (radioButton1.isChecked()) {
                    radioText = radioButton1.getText().toString();

                    if (COUNTER < list.size()) {

                        if (trueAnsList.get(COUNTER).equals(radioText)) {

                            scoure++;
                          //  Toast.makeText(MainActivity.this, "Correct " + scoure, Toast.LENGTH_SHORT).show();

                        } else {
                          //  Toast.makeText(MainActivity.this, "not a correct choice", Toast.LENGTH_SHORT).show();
                        }

                    }

                } else {

                    Toast.makeText(MainActivity.this, "must select one", Toast.LENGTH_SHORT).show();


                }

            }
        });

    }


    private void addQuestionInList() {


        list = new ArrayList<Question>();
        list.add(new Question("Which company owned the Android ?", "Amazon", "Microsoft", "Google", "Facebook"));
        list.add(new Question("Which is official language for android ?", "Java", "C++", "Python","Kotlin"));
        list.add(new Question("Which is not an object oriented language?", "C#", "C++", "Ruby", "C"));
        list.add(new Question("The most widely used operating system for android is  ?", "Ios", "Blackberry","Android", "Window"));
        list.add(new Question("Which android latest version ?", "KitKat", "Pie", "Oreo", "Masmellow"));
    }

    private void registerViews() {
        tvQuestion = findViewById(R.id.tvQuestion);
        radioGroup = findViewById(R.id.radioGroup);
        rb1 = radioGroup.findViewById(R.id.rb1);
        rb2 = radioGroup.findViewById(R.id.rb2);
        rb3 = radioGroup.findViewById(R.id.rb3);
        rb4 = radioGroup.findViewById(R.id.rb4);
        btnNext = findViewById(R.id.btnNext);

    }

    public void replaceQuestion() {

        Question i = list.get(COUNTER);

        tvQuestion.setText(i.getQuestions());
        rb1.setText(i.getAns1());
        rb2.setText(i.getAns2());
        rb3.setText(i.getAns3());
        rb4.setText(i.getAns4());

    }


    public void unchecked() {
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(false);
    }


//    public void reset() {
//
//        rb1.setBackgroundColor(getResources().getColor(R.color.white));
//        rb2.setBackgroundColor(getResources().getColor(R.color.white));
//        rb3.setBackgroundColor(getResources().getColor(R.color.white));
//        rb3.setBackgroundColor(getResources().getColor(R.color.white));
//    }

}