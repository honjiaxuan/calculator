package com.calculator.calculator;

/**
 * Represents the SmsType.
 * <p/>
 * RECEIVED SmsType is the equivalent to MT in a telco terminology.
 * SENT SmsType is the equivalent to MO in a telco terminology.
 * <p/>
 * Review GSM short message service to get more information: http://en.wikipedia.org/wiki/Short_Message_Service
 *
 * @author Pedro Vcente Gómez Sánchez <pgomez@tuenti.com>
 * @author Manuel Peinado <mpeinado@tuenti.com>
 */
public enum SmsType {

    UNKNOWN(-1),
    RECEIVED(1),
    SENT(2),;

    private final int value;

    private SmsType(int value) {
        this.value = value;
    }

    /**
     * Create a new SmsType using the sms type value represented with integers in the Sms content provider.
     *
     * @param value used to translate into SmsType
     * @return new SmsType associated to the value passed as parameter
     */
    public static SmsType fromValue(int value) {
        for (SmsType smsType : values()) {
            if (smsType.value == value) {
                return smsType;
            }
        }
        throw new IllegalArgumentException("Invalid sms type: " + value);
    }

}
