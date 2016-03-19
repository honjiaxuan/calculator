package com.calculator.calculator;

import android.content.Context;
import android.content.Intent;

/**
 * Main library class. This class has to be used to initialize or stop the sms interceptor service.
 *
 * @author Pedro Vcente Gómez Sánchez <pgomez@tuenti.com>
 * @author Manuel Peinado <mpeinado@tuenti.com>
 */
public class SmsRadar {

    static SmsListener smsListener;


    /**
     * Starts the service and store the listener to be notified when a new incoming or outgoing sms be processed
     * inside the SMS content provider
     *
     * @param context used to start the service
     * @param smsListener to notify when the sms content provider gets a new sms
     */
    public static void initializeSmsRadarService(Context context, SmsListener smsListener) {
        SmsRadar.smsListener = smsListener;
        Intent intent = new Intent(context, SmsRadarService.class);
        context.startService(intent);
    }

    /**
     * Stops the service and remove the SmsListener added when the SmsRadar was initialized
     *
     * @param context used to stop the service
     */
    public static void stopSmsRadarService(Context context) {
        SmsRadar.smsListener = null;
        Intent intent = new Intent(context, SmsRadarService.class);
        context.stopService(intent);
    }
}
