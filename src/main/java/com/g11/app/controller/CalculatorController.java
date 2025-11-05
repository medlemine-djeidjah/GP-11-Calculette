package com.g11.app.controller;

import java.util.List;

import com.g11.app.model.CalculatorModelInterface;
import com.g11.app.view.CalculatorGUIInterface;

public class CalculatorController implements CalculatorControllerInterface, CalculatorEventHandler {
    private CalculatorModelInterface model;
    private CalculatorGUIInterface view;
    private StringBuilder inputBuffer;
    private boolean clearDisplayOnNextDigit;

    public CalculatorController(CalculatorModelInterface model, CalculatorGUIInterface view) {
        this.model = model;
        this.view = view;
        this.inputBuffer = new StringBuilder();
        this.clearDisplayOnNextDigit = false;
        view.setEventHandler(this);
        updateDisplay();
    }

    @Override
    public void onDigitPressed(String digit) {
        handleDigitInput(digit);
    }

    @Override
    public void onOperationPressed(String operation) {
        switch (operation) {
            case "add":
                handleOperation(model::add);
                break;
            case "subtract":
                handleOperation(model::subtract);
                break;
            case "multiply":
                handleOperation(model::multiply);
                break;
            case "divide":
                handleDivisionOperation();
                break;
        }
    }

    @Override
    public void onEnterPressed() {
        handleEnter();
    }

    @Override
    public void onClearPressed() {
        handleClear();
    }

    @Override
    public void onSwapPressed() {
        handleSwap();
    }

    @Override
    public void onDropPressed() {
        handleDrop();
    }

    @Override
    public void onOppositePressed() {
        handleOpposite();
    }

    @Override
    public void onPopPressed() {
        handlePop();
    }

    @Override
    public void onCommaPressed() {
        handleComma();
    }

    private void handleDigitInput(String digit) {
        if (clearDisplayOnNextDigit || inputBuffer.length() == 0) {
            inputBuffer.setLength(0);
            clearDisplayOnNextDigit = false;
        }
        inputBuffer.append(digit);
        updateAccumulatorDisplay();
    }

    private void handleEnter() {
        if (inputBuffer.length() > 0) {
            try {
                double value = Double.parseDouble(inputBuffer.toString());
                model.setAccumulator(value);
                inputBuffer.setLength(0);
            } catch (NumberFormatException e) {
                handleError("Invalid number");
                return;
            }
        }
        model.push();
        clearDisplayOnNextDigit = true;
        updateDisplay();
    }

    private void handleOperation(Runnable operation) {
        try {
            if (inputBuffer.length() > 0) {
                double value = Double.parseDouble(inputBuffer.toString());
                model.setAccumulator(value);
                inputBuffer.setLength(0);
            }
            operation.run();
            clearDisplayOnNextDigit = true;
            updateDisplay();
        } catch (Exception e) {
            handleError(e.getMessage());
        }
    }

    private void handleDivisionOperation() {
        try {
            if (inputBuffer.length() > 0) {
                double value = Double.parseDouble(inputBuffer.toString());
                model.setAccumulator(value);
                inputBuffer.setLength(0);
            }
            model.divide();
            clearDisplayOnNextDigit = true;
            updateDisplay();
        } catch (ArithmeticException e) {
            handleError("Division by zero");
        } catch (Exception e) {
            handleError(e.getMessage());
        }
    }

    private void handleClear() {
        inputBuffer.setLength(0);
        model.clear();
        clearDisplayOnNextDigit = false;
        updateDisplay();
    }

    private void handleSwap() {
        if (inputBuffer.length() > 0) {
            try {
                double value = Double.parseDouble(inputBuffer.toString());
                model.setAccumulator(value);
                inputBuffer.setLength(0);
            } catch (NumberFormatException e) {
                handleError("Invalid number");
                return;
            }
        }
        model.swap();
        clearDisplayOnNextDigit = true;
        updateDisplay();
    }

    private void handleDrop() {
        if (inputBuffer.length() > 0) {
            inputBuffer.setLength(0);
            clearDisplayOnNextDigit = false;
            updateAccumulatorDisplay();
        } else {
            model.drop();
            clearDisplayOnNextDigit = true;
            updateDisplay();
        }
    }

    private void handleOpposite() {
        if (inputBuffer.length() > 0) {
            if (inputBuffer.charAt(0) == '-') {
                inputBuffer.deleteCharAt(0);
            } else {
                inputBuffer.insert(0, '-');
            }
            updateAccumulatorDisplay();
        } else {
            model.opposite();
            clearDisplayOnNextDigit = true;
            updateDisplay();
        }
    }

    private void handlePop() {
        model.pop();
        clearDisplayOnNextDigit = true;
        updateDisplay();
    }

    private void handleComma() {
        if (clearDisplayOnNextDigit || inputBuffer.length() == 0) {
            inputBuffer.setLength(0);
            inputBuffer.append("0");
            clearDisplayOnNextDigit = false;
        }
        
        // Only add comma if one doesn't already exist
        if (inputBuffer.indexOf(".") == -1) {
            inputBuffer.append(".");
            updateAccumulatorDisplay();
        }
    }

    private void handleError(String message) {
        view.change("Error: " + message);
        inputBuffer.setLength(0);
    }

    private void updateDisplay() {
        updateAccumulatorDisplay();
        updateStackDisplay();
    }

    private void updateAccumulatorDisplay() {
        if (inputBuffer.length() > 0) {
            view.change(inputBuffer.toString());
        } else {
            double value = model.getAccumulator();
            view.change(formatNumber(value));
        }
    }

    private String formatNumber(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        } else {
            String formatted = String.format("%.10g", value);
            // Remove trailing zeros after decimal point
            if (formatted.contains(".")) {
                formatted = formatted.replaceAll("0+$", "").replaceAll("\\.$", "");
            }
            return formatted;
        }
    }

    private void updateStackDisplay() {
        view.change(model.getStackData());
    }

    @Override
    public void change(String accu) {
        try {
            double value = Double.parseDouble(accu);
            model.setAccumulator(value);
            updateDisplay();
        } catch (NumberFormatException e) {
            handleError("Invalid number format");
        }
    }

    @Override
    public void change(List<Double> stackData) {
        model.setStackData(stackData);
        updateDisplay();
    }
}
