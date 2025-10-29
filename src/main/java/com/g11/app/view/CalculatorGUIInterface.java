package com.g11.app.view;

import com.g11.app.controller.CalculatorEventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.util.List;

public interface CalculatorGUIInterface {
    void start(Stage primaryStage);
    void affiche();
    void change(String accu);
    void change(List<Double> stackData);
    void setEventHandler(CalculatorEventHandler handler);
    
    Button[] getNumberButtons();
    Button getAddButton();
    Button getSubtractButton();
    Button getMultiplyButton();
    Button getDivideButton();
    Button getEnterButton();
    Button getClearButton();
    Button getSwapButton();
    Button getDropButton();
    Button getOppositeButton();
}