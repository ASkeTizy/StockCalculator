package org.example.stockcalc.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import org.example.stockcalc.entity.PositionFromSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
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
        XYChart.Series< String,Number> series = new XYChart.Series<>();
        series.setName("2025 год");
        List<PositionFromSource> positions = calculationController.getPositions(textField.getText(),datePicker1.getValue(),datePicker2.getValue());
        var list = series.getData();
        positions.forEach(el ->{
            xAxis.getCategories().add(el.tradeDate().toString());
            list.add(new XYChart.Data<>(el.tradeDate().toString(),el.legalClosePrice()));
        });
        lineChart.getData().add(series);
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
//    public void formatDate(DatePicker datePicker) {
//        datePicker.setConverter(new StringConverter<LocalDate>() {
//            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//
//            @Override
//            public String toString(LocalDate date) {
//                return (date != null) ? formatter.format(date) : "";
//            }
//
//            @Override
//            public LocalDate fromString(String string) {
//                return (string != null && !string.isEmpty())
//                        ? LocalDate.parse(string, formatter)
//                        : null;
//            }
//        });
//    }


}
