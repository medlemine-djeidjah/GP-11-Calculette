package com.g11.app.model;

import java.util.List;

public interface CalculatorModelInterface {
    double add();
    double subtract();
    double multiply();
    double divide();
    double opposite();
    void push(double value);
    double pop();
    void drop();
    void swap();
    void clear();
    double getAccumulator();
    void setAccumulator(double value);
    List<Double> getStackData();
    void changeAccu(String value);
    void changeStackData(List<Double> stackData);
}