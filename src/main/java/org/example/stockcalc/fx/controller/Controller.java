package org.example.stockcalc.fx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class Controller {

        @FXML
        private DatePicker datePicker1;
        @FXML private DatePicker datePicker2;
        @FXML private TextField textField;
        @FXML private Label resultLabel;
        @FXML private void updateText() {
            LocalDate d1 = datePicker1.getValue();
            LocalDate d2 = datePicker2.getValue();
            String text = textField.getText();
            resultLabel.setText("Дата 1: " + d1 + ", Дата 2: " + d2 + ", Текст: " + text);
        }
}
