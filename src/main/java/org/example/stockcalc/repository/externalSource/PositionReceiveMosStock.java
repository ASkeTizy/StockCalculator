package org.example.stockcalc.repository.externalSource;

import org.example.stockcalc.entity.PositionFromSource;
import org.example.stockcalc.repository.JSONParser;
import org.example.stockcalc.repository.PositionReceive;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class PositionReceiveMosStock implements PositionReceive {
    JSONParser parser;

    public PositionReceiveMosStock(JSONParser parser) {
        this.parser = parser;
    }

    @Override
    public List<String> getPositionCodes() {
        return List.of();
    }

    @Override
    public List<PositionFromSource> getPositions(String type) {
        return List.of();
    }

    @Override
    public List<PositionFromSource> getPositionsByKeyAndDate(String name, LocalDate startDate, LocalDate endDate) {

        HttpClient client = HttpClient.newHttpClient();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDateParsed = startDate.format(formatter);
        String endDateParsed = endDate.format(formatter);
        try {
            int start = 0;
            boolean hasMoreData = true;
            StringBuilder buider = new StringBuilder();
            while (hasMoreData) {
                String str = "https://iss.moex.com/iss/history/engines/stock/markets/shares/securities/" + name + ".json" + "?from=" + startDateParsed + "&till=" + endDateParsed + "&start=" + start;
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(str))
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                var responseBody = response.body();
                if (responseBody.contains("\"data\": [")) {
                    hasMoreData = false;
                } else {
                    buider.append(responseBody);
                    start += 100;
                }
                buider.append(response.body());
            }
            parser.setSource(buider.toString());
            return parser.getPositions(name);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
