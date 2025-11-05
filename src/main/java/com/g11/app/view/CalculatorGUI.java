package com.g11.app.view;

import com.g11.app.controller.CalculatorEventHandler;
import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class CalculatorGUI extends Application implements CalculatorGUIInterface {
    private CalculatorScene calculatorScene;
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        VBox root = new VBox(10);
        calculatorScene = new CalculatorScene(root, 300, 400);

        stage.setTitle("RPN Calculator");
        stage.setScene(calculatorScene);
        stage.setResizable(false);
        affiche();
    }

    @Override
    public void setEventHandler(CalculatorEventHandler handler) {
        calculatorScene.setEventHandler(handler);
    }

    @Override
    public void affiche() {
        if (primaryStage != null) {
            primaryStage.show();
        }
    }

    @Override
    public void change(String accu) {
        calculatorScene.getAccuDisplay().setText(accu);
    }

    @Override
    public void change(List<Double> stackData) {
        calculatorScene.getStackDisplay().getItems().clear();
        for (int i = stackData.size() - 1; i >= 0; i--) {
            calculatorScene.getStackDisplay().getItems().add(stackData.get(i));
        }
    }
}
