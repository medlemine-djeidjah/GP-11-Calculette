package com.g11.app.controller;

import com.g11.app.model.CalculatorModelInterface;
import com.g11.app.view.CalculatorGUIInterface;

import java.util.List;

public class CalculatorController implements CalculatorControllerInterface, CalculatorEventHandler {
    private CalculatorModelInterface model;
    private CalculatorGUIInterface view;
    private StringBuilder inputBuffer;

    public CalculatorController(CalculatorModelInterface model, CalculatorGUIInterface view) {
        this.model = model;
        this.view = view;
        this.inputBuffer = new StringBuilder();
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

    private void handleDigitInput(String digit) {
        inputBuffer.append(digit);
        updateAccumulatorDisplay();
    }

    private void handleEnter() {
        if (inputBuffer.length() > 0) {
            try {
                double value = Double.parseDouble(inputBuffer.toString());
                model.push(model.getAccumulator());
                model.setAccumulator(value);
                inputBuffer.setLength(0);
                updateDisplay();
            } catch (NumberFormatException e) {
                handleError("Invalid number");
            }
        }
    }

    private void handleOperation(Runnable operation) {
        try {
            if (inputBuffer.length() > 0) {
                handleEnter();
            }
            operation.run();
            updateDisplay();
        } catch (Exception e) {
            handleError(e.getMessage());
        }
    }

    private void handleDivisionOperation() {
        try {
            if (inputBuffer.length() > 0) {
                handleEnter();
            }
            model.divide();
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
        updateDisplay();
    }

    private void handleSwap() {
        if (inputBuffer.length() > 0) {
            handleEnter();
        }
        model.swap();
        updateDisplay();
    }

    private void handleDrop() {
        if (inputBuffer.length() > 0) {
            inputBuffer.setLength(0);
            updateAccumulatorDisplay();
        } else {
            model.drop();
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
            updateDisplay();
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
            view.change(String.valueOf(model.getAccumulator()));
        }
    }

    private void updateStackDisplay() {
        view.change(model.getStackData());
    }

    @Override
    public void changeAccu(String value) {
        model.changeAccu(value);
        updateDisplay();
    }

    @Override
    public void changeStackData(List<Double> stackData) {
        model.changeStackData(stackData);
        updateDisplay();
    }
}
