package com.g11.app.view;

import com.g11.app.controller.CalculatorEventHandler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class CalculatorGUI extends Application implements CalculatorGUIInterface {
    private TextField accuDisplay;
    private ListView<Double> stackDisplay;
    private Button[] numberButtons;
    private Button addButton, subtractButton, multiplyButton, divideButton;
    private Button enterButton, clearButton, swapButton, dropButton, oppositeButton;
    private Scene scene;
    private Stage primaryStage;
    private CalculatorEventHandler eventHandler;

    public CalculatorGUI() {
        initializeComponents();
        setupLayout();
    }
    
    @Override
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

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        affiche();
        
        stage.setTitle("RPN Calculator");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void initializeComponents() {
        accuDisplay = new TextField("0");
        accuDisplay.setEditable(false);
        accuDisplay.setStyle("-fx-font-size: 18; -fx-alignment: center-right;");

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

    private void setupLayout() {
        VBox root = new VBox(10);
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
        scene = new Scene(root, 300, 400);
    }

    @Override
    public void affiche() {
        if (primaryStage != null) {
            primaryStage.show();
        }
    }

    @Override
    public void change(String accu) {
        accuDisplay.setText(accu);
    }

    @Override
    public void change(List<Double> stackData) {
        stackDisplay.getItems().clear();
        stackDisplay.getItems().addAll(stackData);
    }


    public TextField getAccuDisplay() {
        return accuDisplay;
    }

    public ListView<Double> getStackDisplay() {
        return stackDisplay;
    }

    public Button[] getNumberButtons() {
        return numberButtons;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getSubtractButton() {
        return subtractButton;
    }

    public Button getMultiplyButton() {
        return multiplyButton;
    }

    public Button getDivideButton() {
        return divideButton;
    }

    public Button getEnterButton() {
        return enterButton;
    }

    public Button getClearButton() {
        return clearButton;
    }

    public Button getSwapButton() {
        return swapButton;
    }

    public Button getDropButton() {
        return dropButton;
    }

    public Button getOppositeButton() {
        return oppositeButton;
    }
}
