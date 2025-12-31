package org.example.stockcalc;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import org.w3c.dom.Text;

import java.util.function.UnaryOperator;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField numberField;
    @FXML
    public void initialize() {
//        System.out.println("rofl");

        numberField.setTextFormatter(new CurrencyFormatter());
    }
    @FXML
    protected void onHelloButtonClick() {
        var price = numberField.getCharacters();
        welcomeText.setText("Welcome to JavaFX Application!"+ price);
    }
}
