package com.g11.app.view;

import java.util.List;

import com.g11.app.controller.CalculatorEventHandler;

import javafx.stage.Stage;

public interface CalculatorGUIInterface {
    void start(Stage primaryStage);
    void affiche();
    void change(String accu);
    void change(List<Double> stackData); 
    void setEventHandler(CalculatorEventHandler handler);

}
