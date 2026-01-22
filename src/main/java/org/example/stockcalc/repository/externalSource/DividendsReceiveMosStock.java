package org.example.stockcalc.repository.externalSource;

import org.example.stockcalc.entity.Dividend;
import org.example.stockcalc.repository.DividendsReceive;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


public class DividendsReceiveMosStock implements DividendsReceive {
    public DividendsReceiveMosStock() {
    }

    @Override
    public List<Dividend> getDividends(String type) {
        return List.of();
    }

    @Override
    public List<Dividend> getDividendByKeyAndDate(String key, LocalDate startDate, LocalDate endDate) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String startDateParsed = startDate.format(formatter);
            String endDateParsed = endDate.format(formatter);
            String str =  "https://iss.moex.com/iss/securities/SBER/dividends.json";
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(str))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

            return List.of();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
