package com.calculator.calculator;


 class CalculatorBrain {
    // 3+6 = 9
    // 3 & 6 are called the operand
    // + is operator
    // 9 is result of operation

    private double mOperand;
    private double mWaitingOperand;
    private String mWaitingOperator;

    // operator types
    private static final String ADD = "+";
    private static final String SUBTRACT = "\u2212";
    private static final String MULTIPLY = "\u00d7";
    private static final String DIVIDE = "\u00f7";
    private static final String CLEAR = "CLEAR";


    // constructor
    public CalculatorBrain() {
        // initialize variables upon start
        mOperand = 0;
        mWaitingOperand = 0;
        mWaitingOperator = "";
    }

    public void setOperand(double operand) {
        mOperand = operand;
    }

    public double getResult() {
        return mOperand;
    }

    public String toString() {
        return Double.toString(mOperand);
    }

     double performOperation(String operator) {
        if (operator.equals(CLEAR)) {
            mOperand = 0;
            mWaitingOperator = "";
            mWaitingOperand = 0;
        } else {
            {
            if (mWaitingOperator.equals(ADD)) {
                mOperand = mWaitingOperand + mOperand;
            } else if (mWaitingOperator.equals(SUBTRACT)) {
                mOperand = mWaitingOperand - mOperand;
            } else if (mWaitingOperator.equals(MULTIPLY)) {
                mOperand = mWaitingOperand * mOperand;
            } else if (mWaitingOperator.equals(DIVIDE)) {
                if (mOperand != 0) {
                    mOperand = mWaitingOperand / mOperand;
                }
            }
            }
            mWaitingOperator = operator;
            mWaitingOperand = mOperand;
        }
        return mOperand;}
    }


