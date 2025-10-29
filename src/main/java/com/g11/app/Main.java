package com.g11.app;

import com.g11.app.controller.CalculatorController;
import com.g11.app.model.CalculatorModel;
import com.g11.app.model.CalculatorModelInterface;
import com.g11.app.view.CalculatorGUI;
import com.g11.app.view.CalculatorGUIInterface;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        CalculatorModelInterface model = new CalculatorModel();
        CalculatorGUIInterface view = new CalculatorGUI();

        view.start(primaryStage);

        new CalculatorController(model, view);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
