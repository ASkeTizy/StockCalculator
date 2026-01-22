package org.example.stockcalc.repository.externalSource;

import org.example.stockcalc.entity.Position;
import org.example.stockcalc.entity.PositionFromSource;
import org.example.stockcalc.repository.PositionReceive;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class PositionReceiveMosStock implements PositionReceive {
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
        try {
            HttpClient client = HttpClient.newHttpClient();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String startDateParsed = startDate.format(formatter);
            String endDateParsed = endDate.format(formatter);
            String str = "https://iss.moex.com/iss/history/engines/stock/markets/shares/securities/"+name+".json"
                    + "?from="+startDateParsed+"&till="+endDateParsed;
//            String str  = "https://iss.moex.com/iss/history/engines/stock/markets/shares/securities/SBER.json?from=2024-01-01&till=2025-01-10";
//            https://iss.moex.com/iss/securities/SBER/dividends.json
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
