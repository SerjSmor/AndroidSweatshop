package com.workshop1.android.serj.homework;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



/**
 * This Activity binds 3 TextViews and 3 Buttons settings each with a
 *  counter. I've set the button onClickListener differently this time,
 *  but the previous method in HelloWorld should have worked here too.
 */

public class MainActivity extends Activity implements View.OnClickListener {

    private int counter1 = 0;
    private int counter2 = 0;
    private int counter3 = 0;

    private TextView textCounter1;
    private TextView textCounter2;
    private TextView textCounter3;

    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textCounter1 = (TextView) findViewById(R.id.text_counter_1);
        textCounter2 = (TextView) findViewById(R.id.text_counter_2);
        textCounter3 = (TextView) findViewById(R.id.text_counter_3);

        button1 = (Button) findViewById(R.id.button_counter_1);
        button2 = (Button) findViewById(R.id.button_counter_2);
        button3 = (Button) findViewById(R.id.button_counter_3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_counter_1:
                counter1++;
                textCounter1.setText(String.valueOf(counter1));
                break;
            case R.id.button_counter_2:
                counter2++;
                textCounter2.setText(String.valueOf(counter2));
                break;

            case R.id.button_counter_3:
                textCounter3.setText(String.valueOf(counter3));
                counter3++;
                break;
        }
    }
}
