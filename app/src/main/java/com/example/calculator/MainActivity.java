package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView resultTv, solutionTv;
    Button one, two, three, four, five, six, seven, eight, nine, zero, add, sub, mul, div, equal, clear ,allClear, dot, bracketOpen , bracketClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.ResultTv);
        solutionTv = findViewById(R.id.SolutionTv);

        assignId(zero,R.id.button0);
        assignId(one,R.id.button1);
        assignId(two,R.id.button2);
        assignId(three,R.id.button3);
        assignId(four,R.id.button4);
        assignId(five,R.id.button5);
        assignId(six,R.id.button6);
        assignId(seven,R.id.button7);
        assignId(eight,R.id.button8);
        assignId(nine,R.id.button9);
        assignId(add,R.id.buttonAdd);
        assignId(sub,R.id.buttonMinus);
        assignId(mul,R.id.buttonX);
        assignId(div,R.id.buttonDivide);
        assignId(equal,R.id.buttonEqual);
        assignId(clear,R.id.buttonC);
        assignId(allClear,R.id.buttonAC);
        assignId(dot,R.id.buttonDecimal);
        assignId(bracketOpen,R.id.buttonBracketOpen);
        assignId(bracketClose,R.id.buttonBracketClose);

    }

    void assignId(Button btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        Button button = (Button) v;
        String data = button.getText().toString();
//        System.out.println(data);
        String dataToCalculate = solutionTv.getText().toString();

        if(data.equals("AC")){
            solutionTv.setText("0");
            resultTv.setText("0");
            return;
        }
        if(data.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (data.equals("C")){
            if(dataToCalculate.length() > 0){
                dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
                solutionTv.setText(dataToCalculate);
            }
            return;
        }else {
            dataToCalculate = dataToCalculate + data;
        }

        solutionTv.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }

    }

    String getResult(String data){
        try{
            Context context  = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}