package org.example.stockcalc.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.stockcalc.entity.Dividend;
import org.example.stockcalc.entity.PositionFromSource;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Primary
public class DividendsFromFile implements DividendsReceive {
    @Override
    public Map<String, List<Dividend>> getDividends() {
        return Map.of();
    }

    @Override
    public List<Dividend> getDividendByKeyAndDate(String key, LocalDate startDate, LocalDate endDate) {
        ObjectMapper mapper = new ObjectMapper(); // читаем JSON в Map
        Map<String, LinkedHashMap> data = null;
        try (InputStream input = DividendsFromFile.class.getResourceAsStream("/source/dividends.json")) {
            data = mapper.readValue(input, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String s = data.get("dividends").get("columns").toString();
        var bla = data.get("dividends").get("data").toString();
        return List.of();
    }


}
