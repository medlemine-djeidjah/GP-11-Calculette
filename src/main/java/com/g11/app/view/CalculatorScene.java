package com.g11.app.view;

import com.g11.app.controller.CalculatorEventHandler;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CalculatorScene extends Scene {
    private TextField accuDisplay;
    private ListView<Double> stackDisplay;
    private Button[] numberButtons;
    private Button addButton, subtractButton, multiplyButton, divideButton;
    private Button enterButton, clearButton, swapButton, dropButton, oppositeButton, popButton;
    private CalculatorEventHandler eventHandler;

    public CalculatorScene(VBox root, double width, double height) {
        super(root, width, height);
        initializeComponents();
        setupLayout(root);
    }

    private void initializeComponents() {
        accuDisplay = new TextField("0");
        accuDisplay.setEditable(false);
        accuDisplay.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-alignment: center-right; " +
                           "-fx-padding: 15 20 15 20; -fx-background-color: #2a2a2a; -fx-text-fill: #ffffff; " +
                           "-fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #444444; " +
                           "-fx-border-width: 2; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 5, 0, 0, 2);");

        stackDisplay = new ListView<>();
        stackDisplay.setPrefHeight(180);
        stackDisplay.setStyle("-fx-background-color: #1e1e1e; -fx-control-inner-background: #1e1e1e; " +
                            "-fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #444444; " +
                            "-fx-border-width: 2; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 5, 0, 0, 2); " +
                            "-fx-selection-bar: #ff9500; -fx-selection-bar-non-focused: #cc7700; " +
                            "-fx-font-size: 16; -fx-text-fill: #ffffff;");
        stackDisplay.setCellFactory(listView -> {
            return new javafx.scene.control.ListCell<Double>() {
                @Override
                protected void updateItem(Double item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(String.format("%.6g", item));
                        setStyle("-fx-background-color: transparent; -fx-text-fill: #ffffff; " +
                               "-fx-font-size: 16; -fx-padding: 8 12 8 12;");
                    }
                }
            };
        });

        numberButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new Button(String.valueOf(i));
            numberButtons[i].setPrefSize(70, 50);
            styleNumberButton(numberButtons[i]);
        }

        addButton = new Button("+");
        subtractButton = new Button("-");
        multiplyButton = new Button("×");
        divideButton = new Button("÷");
        enterButton = new Button("Enter");
        clearButton = new Button("Clear");
        swapButton = new Button("Swap");
        dropButton = new Button("Drop");
        oppositeButton = new Button("±");
        popButton = new Button("Pop");

        Button[] operationButtons = {addButton, subtractButton, multiplyButton, divideButton};
        for (Button button : operationButtons) {
            button.setPrefSize(70, 50);
            styleOperatorButton(button);
        }

        Button[] functionButtons = {enterButton, clearButton, swapButton, dropButton, oppositeButton, popButton};
        for (Button button : functionButtons) {
            button.setPrefSize(70, 50);
            styleFunctionButton(button);
        }
    }

    private void setupLayout(VBox root) {
        root.setPadding(new Insets(20));
        root.setSpacing(15);
        root.setStyle("-fx-background-color: #1a1a1a;");
        
        root.getChildren().addAll(accuDisplay, stackDisplay);

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(8);
        buttonGrid.setVgap(8);
        buttonGrid.setPadding(new Insets(10, 0, 0, 0));

        buttonGrid.add(numberButtons[7], 0, 0);
        buttonGrid.add(numberButtons[8], 1, 0);
        buttonGrid.add(numberButtons[9], 2, 0);
        buttonGrid.add(divideButton, 3, 0);

        buttonGrid.add(numberButtons[4], 0, 1);
        buttonGrid.add(numberButtons[5], 1, 1);
        buttonGrid.add(numberButtons[6], 2, 1);
        buttonGrid.add(multiplyButton, 3, 1);

        buttonGrid.add(numberButtons[1], 0, 2);
        buttonGrid.add(numberButtons[2], 1, 2);
        buttonGrid.add(numberButtons[3], 2, 2);
        buttonGrid.add(subtractButton, 3, 2);

        buttonGrid.add(numberButtons[0], 0, 3);
        buttonGrid.add(oppositeButton, 1, 3);
        buttonGrid.add(enterButton, 2, 3);
        buttonGrid.add(addButton, 3, 3);

        buttonGrid.add(clearButton, 0, 4);
        buttonGrid.add(dropButton, 1, 4);
        buttonGrid.add(swapButton, 2, 4);
        buttonGrid.add(popButton, 3, 4);

        root.getChildren().add(buttonGrid);
    }

    public void setEventHandler(CalculatorEventHandler handler) {
        this.eventHandler = handler;
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        if (eventHandler == null) return;

        for (int i = 0; i < 10; i++) {
            final String digit = String.valueOf(i);
            numberButtons[i].setOnAction(e -> eventHandler.onDigitPressed(digit));
        }

        addButton.setOnAction(e -> eventHandler.onOperationPressed("add"));
        subtractButton.setOnAction(e -> eventHandler.onOperationPressed("subtract"));
        multiplyButton.setOnAction(e -> eventHandler.onOperationPressed("multiply"));
        divideButton.setOnAction(e -> eventHandler.onOperationPressed("divide"));
        enterButton.setOnAction(e -> eventHandler.onEnterPressed());
        clearButton.setOnAction(e -> eventHandler.onClearPressed());
        swapButton.setOnAction(e -> eventHandler.onSwapPressed());
        dropButton.setOnAction(e -> eventHandler.onDropPressed());
        oppositeButton.setOnAction(e -> eventHandler.onOppositePressed());
        popButton.setOnAction(e -> eventHandler.onPopPressed());
    }

    public TextField getAccuDisplay() {
        return accuDisplay;
    }

    public ListView<Double> getStackDisplay() {
        return stackDisplay;
    }

    private void styleNumberButton(Button button) {
        button.setStyle("-fx-background-color: #4a4a4a; -fx-text-fill: white; -fx-font-size: 18; " +
                       "-fx-font-weight: bold; -fx-background-radius: 8; -fx-border-radius: 8; " +
                       "-fx-border-color: #666666; -fx-border-width: 1; " +
                       "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 3, 0, 0, 1);");
        
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #5a5a5a; -fx-text-fill: white; " +
                                                     "-fx-font-size: 18; -fx-font-weight: bold; -fx-background-radius: 8; " +
                                                     "-fx-border-radius: 8; -fx-border-color: #777777; -fx-border-width: 1; " +
                                                     "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 5, 0, 0, 2);"));
        
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #4a4a4a; -fx-text-fill: white; " +
                                                    "-fx-font-size: 18; -fx-font-weight: bold; -fx-background-radius: 8; " +
                                                    "-fx-border-radius: 8; -fx-border-color: #666666; -fx-border-width: 1; " +
                                                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 3, 0, 0, 1);"));
    }

    private void styleOperatorButton(Button button) {
        button.setStyle("-fx-background-color: #ff9500; -fx-text-fill: white; -fx-font-size: 20; " +
                       "-fx-font-weight: bold; -fx-background-radius: 8; -fx-border-radius: 8; " +
                       "-fx-border-color: #cc7700; -fx-border-width: 1; " +
                       "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 3, 0, 0, 1);");
        
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #ffad33; -fx-text-fill: white; " +
                                                     "-fx-font-size: 20; -fx-font-weight: bold; -fx-background-radius: 8; " +
                                                     "-fx-border-radius: 8; -fx-border-color: #e68a00; -fx-border-width: 1; " +
                                                     "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 5, 0, 0, 2);"));
        
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #ff9500; -fx-text-fill: white; " +
                                                    "-fx-font-size: 20; -fx-font-weight: bold; -fx-background-radius: 8; " +
                                                    "-fx-border-radius: 8; -fx-border-color: #cc7700; -fx-border-width: 1; " +
                                                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 3, 0, 0, 1);"));
    }

    private void styleFunctionButton(Button button) {
        button.setStyle("-fx-background-color: #666666; -fx-text-fill: white; -fx-font-size: 14; " +
                       "-fx-font-weight: bold; -fx-background-radius: 8; -fx-border-radius: 8; " +
                       "-fx-border-color: #888888; -fx-border-width: 1; " +
                       "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 3, 0, 0, 1);");
        
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #777777; -fx-text-fill: white; " +
                                                     "-fx-font-size: 14; -fx-font-weight: bold; -fx-background-radius: 8; " +
                                                     "-fx-border-radius: 8; -fx-border-color: #999999; -fx-border-width: 1; " +
                                                     "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.6), 5, 0, 0, 2);"));
        
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #666666; -fx-text-fill: white; " +
                                                    "-fx-font-size: 14; -fx-font-weight: bold; -fx-background-radius: 8; " +
                                                    "-fx-border-radius: 8; -fx-border-color: #888888; -fx-border-width: 1; " +
                                                    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 3, 0, 0, 1);"));
    }
}
