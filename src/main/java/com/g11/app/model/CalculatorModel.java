package com.g11.app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CalculatorModel implements CalculatorModelInterface {
    private double accumulator;
    private Stack<Double> memory;

    public CalculatorModel() {
        this.accumulator = 0.0;
        this.memory = new Stack<>();
    }

    @Override
    public double add() {
        if (!memory.isEmpty()) {
            accumulator = memory.pop() + accumulator;
        }
        return accumulator;
    }

    @Override
    public double subtract() {
        if (!memory.isEmpty()) {
            accumulator = memory.pop() - accumulator;
        }
        return accumulator;
    }

    @Override
    public double multiply() {
        if (!memory.isEmpty()) {
            accumulator = memory.pop() * accumulator;
        }
        return accumulator;
    }

    @Override
    public double divide() {
        if (!memory.isEmpty()) {
            double divisor = accumulator;
            if (divisor == 0) {
                throw new ArithmeticException("Cannot divide by zero");
            }
            accumulator = memory.pop() / divisor;
        }
        return accumulator;
    }

    @Override
    public double opposite() {
        accumulator = -accumulator;
        return accumulator;
    }

    @Override
    public void push(double value) {
        memory.push(value);
    }

    @Override
    public double pop() {
        if (!memory.isEmpty()) {
            accumulator = memory.pop();
        }
        return accumulator;
    }

    @Override
    public void drop() {
        if (!memory.isEmpty()) {
            memory.pop();
        }
    }

    @Override
    public void swap() {
        if (memory.size() >= 1) {
            double temp = accumulator;
            accumulator = memory.pop();
            memory.push(temp);
        }
    }

    @Override
    public void clear() {
        accumulator = 0.0;
        memory.clear();
    }

    @Override
    public double getAccumulator() {
        return accumulator;
    }

    @Override
    public void setAccumulator(double value) {
        this.accumulator = value;
    }

    @Override
    public List<Double> getStackData() {
        return new ArrayList<>(memory);
    }

    @Override
    public void changeAccu(String value) {
        try {
            this.accumulator = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + value);
        }
    }

    @Override
    public void changeStackData(List<Double> stackData) {
        memory.clear();
        memory.addAll(stackData);
    }
}
