package com.calculator.calculator;

/**
 * This interface has to be implemented to be notified when an sms be received or sent.
 *
 * @author Pedro Vcente Gómez Sánchez <pgomez@tuenti.com>
 * @author Manuel Peinado <mpeinado@tuenti.com>
 */
public interface SmsListener {

    /**
     * Invoked when an incoming sms is intercepted.
     *
     * @param sms intercepted.
     */
    public void onSmsSent(Sms sms);

    /**
     * Invoked when an outgoing sms is intercepted.
     *
     * @param sms
     */
    public void onSmsReceived(Sms sms);

}
