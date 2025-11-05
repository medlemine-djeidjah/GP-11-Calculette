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
    private Button enterButton, clearButton, swapButton, dropButton, oppositeButton;
    private CalculatorEventHandler eventHandler;

    public CalculatorScene(VBox root, double width, double height) {
        super(root, width, height);
        initializeComponents();
        setupLayout(root);
    }

    private void initializeComponents() {
        accuDisplay = new TextField("0");
        accuDisplay.setEditable(false);
        accuDisplay.setStyle("-fx-font-size: 18; -fx-alignment: center-right; -fx-padding: 5 10 5 10;");

        stackDisplay = new ListView<>();
        stackDisplay.setPrefHeight(150);

        numberButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new Button(String.valueOf(i));
            numberButtons[i].setPrefSize(60, 40);
        }

        addButton = new Button("+");
        subtractButton = new Button("-");
        multiplyButton = new Button("*");
        divideButton = new Button("/");
        enterButton = new Button("Enter");
        clearButton = new Button("Clear");
        swapButton = new Button("Swap");
        dropButton = new Button("Drop");
        oppositeButton = new Button("+/-");

        Button[] operationButtons = {addButton, subtractButton, multiplyButton, divideButton,
                                   enterButton, clearButton, swapButton, dropButton, oppositeButton};
        for (Button button : operationButtons) {
            button.setPrefSize(60, 40);
        }
    }

    private void setupLayout(VBox root) {
        root.setPadding(new Insets(10));
        root.getChildren().addAll(accuDisplay, stackDisplay);

        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(5);
        buttonGrid.setVgap(5);

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
    }

    public TextField getAccuDisplay() {
        return accuDisplay;
    }

    public ListView<Double> getStackDisplay() {
        return stackDisplay;
    }
}
