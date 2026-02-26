package org.example.stockcalc.repository.externalSource;

import org.example.stockcalc.entity.Dividend;
import org.example.stockcalc.repository.DividendsReceive;
import org.example.stockcalc.repository.JSONParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class DividendsReceiveMosStock implements DividendsReceive {

    JSONParser parser;

    public DividendsReceiveMosStock(JSONParser parser) {
        this.parser = parser;
    }

    @Override
    public List<Dividend> getDividends(String type) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String str = "https://iss.moex.com/iss/securities/" + type + "/dividends.json?";
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(str)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var responseBody = response.body();
            parser.setSource(responseBody);
            return parser.getDividends(type);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Dividend> getDividendByKeyAndDate(String key, LocalDate startDate, LocalDate endDate) {
        return getDividends(key).stream().filter(el -> el.date().isAfter(startDate) && el.date().isBefore(endDate)).toList();
    }
}
