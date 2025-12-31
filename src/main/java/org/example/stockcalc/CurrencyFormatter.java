package org.example.stockcalc;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class CurrencyFormatter extends TextFormatter<Double> {
    private static final double DEFAULT_VALUE = 5.00d;
    private static final String CURRENCY_SYMBOL = "$"; // british pound

    private static final DecimalFormat strictZeroDecimalFormat
            = new DecimalFormat("###,##0.00");

    CurrencyFormatter() {
        super(
                // string converter converts between a string and a value property.
                new StringConverter<Double>() {
                    @Override
                    public String toString(Double value) {
                        return strictZeroDecimalFormat.format(value);
                    }

                    @Override
                    public Double fromString(String string) {
                        try {
                            return strictZeroDecimalFormat.parse(string).doubleValue();
                        } catch (ParseException e) {
                            return Double.NaN;
                        }
                    }
                },
                DEFAULT_VALUE,
                change -> {

                    try {
                        strictZeroDecimalFormat.parse(change.getControlNewText());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    return change;

                }
        );
    }
}
