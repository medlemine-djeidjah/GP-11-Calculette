package com.g11.app.model;

import java.util.List;

public interface CalculatorModelInterface {
    double add();
    double subtract();
    double multiply();
    double divide();
    double opposite();
    void push();
    void pop();
    void drop();
    void dropAll();
    void swap();
    void clear();
    double getAccumulator();
    void setAccumulator(double value);
    List<Double> getStackData();
    void setStackData(List<Double> stackData);
}
