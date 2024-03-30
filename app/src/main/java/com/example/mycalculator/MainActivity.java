package com.example.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView result_textview,solution_textview;
    MaterialButton
            button_c,button_open_breacket, button_close_bracket,button_divide,
            button_7,button_8,button_9,button_x,
            button_4,button_5,button_6,button_sum,
            button_1,button_2,button_3,button_minus,
            button_0,button_dot,button_ac,button_equal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result_textview = findViewById(R.id.result_textview);
        solution_textview = findViewById(R.id.solution_textview);
        assignId(button_c,R.id.button_C);
        assignId(button_open_breacket,R.id.button_open_bracket);
        assignId(button_close_bracket,R.id.button_close_bracket);
        assignId(button_divide,R.id.button_divide);
        assignId(button_7,R.id.button_7);
        assignId(button_8,R.id.button_8);
        assignId(button_9,R.id.button_9);
        assignId(button_x,R.id.button_x);
        assignId(button_4,R.id.button_4);
        assignId(button_5,R.id.button_5);
        assignId(button_6,R.id.button_6);
        assignId(button_sum,R.id.button_sum);
        assignId(button_1,R.id.button_1);
        assignId(button_2,R.id.button_2);
        assignId(button_3,R.id.button_3);
        assignId(button_minus,R.id.button_minus);
        assignId(button_0,R.id.button_0);
        assignId(button_dot,R.id.button_dot);
        assignId(button_ac,R.id.button_ac);
        assignId(button_equal,R.id.button_equal);


    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution_textview.getText().toString();
        if(buttonText.equals("AC")){
            solution_textview.setText("0");
            result_textview.setText("0");
            return;
        }
        if (buttonText.equals("=")){
            solution_textview.setText(result_textview.getText());
            return;
        }
        if (buttonText.equals("C") && dataToCalculate.length() > 1){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);

        } else if (dataToCalculate.length() == 1 && buttonText.equals("C")) {
            solution_textview.setText("0");

        } else{
            dataToCalculate = dataToCalculate + buttonText;


        }
        solution_textview.setText(dataToCalculate);
        String finalReslt = getResults(dataToCalculate);
        if (!finalReslt.equals("Err")){
            result_textview.setText(finalReslt);
        }
    }
    String getResults(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            return context.evaluateString(scriptable,data,"Javascript",1,null).toString();
        }catch (Exception e){
            return "Err";
        }
    }
}