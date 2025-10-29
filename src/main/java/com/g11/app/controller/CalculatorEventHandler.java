package com.g11.app.controller;

public interface CalculatorEventHandler {
    void onDigitPressed(String digit);
    void onOperationPressed(String operation);
    void onEnterPressed();
    void onClearPressed();
    void onSwapPressed();
    void onDropPressed();
    void onOppositePressed();
}