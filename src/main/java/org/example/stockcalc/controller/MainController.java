package org.example.stockcalc.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import org.example.stockcalc.entity.PositionFromSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.function.UnaryOperator;

@Component
public class MainController {

    private CalculationController calculationController;

    @Autowired
    public MainController(CalculationController calculationController) {
        this.calculationController = calculationController;
    }

    public MainController() {
    }

    @FXML
    private DatePicker datePicker1;
    @FXML
    private DatePicker datePicker2;
    @FXML
    private TextField textField;
    @FXML
    private TextField amountOfPosition;
    @FXML
    private Label resultLabel;
    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CheckBox operationCheckBox;

    @FXML
    private void updateText() {
        LocalDate d1 = datePicker1.getValue();
        LocalDate d2 = datePicker2.getValue();
        String text = textField.getText();
        Integer amount = Integer.valueOf(amountOfPosition.getText());
        List<Double> result = null;
        if (operationCheckBox.isSelected()) {
            System.out.println("Reinvesting");
        } else {
            result = calculationController.calculateProfitWithDividends(text, d1, d2, amount);
            drawGrafic();
            resultLabel.setText("Прибыль в деньгах " + result.getFirst() + ", Прибыль в процентах " + result.getLast());

        }

    }


    @FXML
    public void initialize() {
        formatText();
        datePicker1.setValue(LocalDate.of(2022, 1, 1));
        datePicker2.setValue(LocalDate.of(2026, 1, 1));
        textField.setText("SBER");
        amountOfPosition.setText("5000");
        operationCheckBox.setText("Реинвестирование");
        lineChart.setTitle("Продажи по месяцам");
        drawGrafic();

//            formatDate(datePicker1);
//            formatDate(datePicker2);


    }

    public void drawGrafic() {
        lineChart.getData().clear();
        xAxis.getCategories().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("2025 год");
        List<PositionFromSource> positions = calculationController.getPositions(textField.getText(), datePicker1.getValue(), datePicker2.getValue());
        var list = series.getData();
        positions.forEach(el -> {
            xAxis.getCategories().add(el.tradeDate().toString());
            list.add(new XYChart.Data<>(el.tradeDate().toString(), el.legalClosePrice()));
        });
        lineChart.getData().add(series);
        setGraficInteraction(series);
    }

    public void setGraficInteraction(XYChart.Series<String, Number> series) {
        for (XYChart.Data<String, Number> data : series.getData()) {
            Node node = data.getNode();
            node.setOnMouseEntered(event -> {
                node.setStyle("-fx-background-color: red, white; -fx-background-radius: 5px;");
            });
            node.setOnMouseExited(event -> {
                node.setStyle("");
            });
            node.setOnMouseClicked(event ->
            {
                if (datePicker1.isFocused()) {
                    setDateByFocus(datePicker1, data.getXValue());
                } else {
                    setDateByFocus(datePicker2, data.getXValue());
                }
            });
        }
    }

    public void setDateByFocus(DatePicker datePicker,String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        datePicker.setValue(date);
    }

    public void formatText() {
        UnaryOperator<TextFormatter.Change> filter = change -> {
            if (change.getControlNewText().matches("\\d*")) {
                return change;
            }
            return null;
        };
        amountOfPosition.setTextFormatter(new TextFormatter<>(filter));

    }


}
