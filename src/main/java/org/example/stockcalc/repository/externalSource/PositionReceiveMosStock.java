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
import java.util.ArrayList;
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
    private String getResponse(HttpClient client,String name,String startDateParsed,String endDateParsed,Integer start){
        String str = "https://iss.moex.com/iss/history/engines/stock/markets/shares/securities/" + name + ".json" + "?from=" + startDateParsed + "&till=" + endDateParsed + "&start=" + start;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(str))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();

    }
    @Override
    public List<PositionFromSource> getPositionsByKeyAndDate(String name, LocalDate startDate, LocalDate endDate) {

        HttpClient client = HttpClient.newHttpClient();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDateParsed = startDate.format(formatter);
        String endDateParsed = endDate.format(formatter);
        String responseBody = getResponse(client,name,startDateParsed,endDateParsed,0);

            int start = 0;

            List<PositionFromSource> list = new ArrayList<>();
            JSONParser cursorParser = new JSONParser("history.cursor");
            cursorParser.setSource(responseBody);
            int iter = cursorParser.getIterations(responseBody);
//            JSONParser dataParser = new JSONParser("data");
            if(iter < 100) {
                parser.setSource(responseBody);
                return parser.getPositions(name);
            } else {

                for(int i =0; i< iter / 100; i++) {
                    responseBody = getResponse(client,name,startDateParsed,endDateParsed,start);
                    parser.setSource(responseBody);
                    list.addAll(parser.getPositions(name));
                    start += 100;
                }
                return list;
            }




    }
}
