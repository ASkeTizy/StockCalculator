package org.example.stockcalc.repository.externalSource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.stockcalc.entity.Dividend;
import org.example.stockcalc.repository.DividendsReceive;
import org.example.stockcalc.repository.JSONParser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DividendsReceiveMosStock implements DividendsReceive {
    public DividendsReceiveMosStock() {
    }

    @Override
    public List<Dividend> getDividends(String type) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String startDateParsed = startDate.format(formatter);
//            String endDateParsed = endDate.format(formatter);
            int start =0;
            boolean hasMoreData = true;
            StringBuilder buider = new StringBuilder();
            while(hasMoreData) {

                String str =  "https://iss.moex.com/iss/securities/"+ type + "/dividends.json?";
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(str))
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                var responseBody = response.body();
                if (responseBody.contains("\"data\": []")) {
                    hasMoreData = false;
                } else {
                    buider.append(responseBody);
                    start += 100;
                }
                buider.append(response.body());
            }
            var jsonParser = new JSONParser(buider.toString(),"dividends");
            return jsonParser.getDividends(type);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Dividend> getDividendByKeyAndDate(String key, LocalDate startDate, LocalDate endDate) {
       return List.of();
    }
}
