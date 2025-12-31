package org.example.stockcalc.repository;

import org.example.stockcalc.entity.Dividend;
import org.springframework.context.annotation.Primary;
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
@Component
@Primary
public class DividendsReceiveMosStock implements DividendsReceive {
    public DividendsReceiveMosStock() {
    }

    @Override
    public Map<String, List<Dividend>> getDividends() {
        return Map.of();
    }

    @Override
    public List<Dividend> getDividendByKeyAndDate(String key, LocalDate startDate, LocalDate endDate) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String startDateParsed = startDate.format(formatter);
            String endDateParsed = endDate.format(formatter);
//            String str = "https://iss.moex.com/iss/history/engines/stock/markets/shares/securities/"+key+".json"
//                    + "?from="+startDateParsed+"&till="+endDateParsed;
            String str  = "https://iss.moex.com/iss/history/engines/stock/markets/shares/securities/SBER.json" + "?from=2024-01-01&till=2025-01-10";
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
