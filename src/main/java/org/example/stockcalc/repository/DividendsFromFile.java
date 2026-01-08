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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        try (InputStream input = DividendsFromFile.class.getResourceAsStream("/source/data.json")){
           data = mapper.readValue(input, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String s  =data.get("history").get("columns").toString();
        var stroks =  s.split(", ");
        var list = Arrays.asList(stroks);
        var list2 = Arrays.stream(PositionFromSource.class.getDeclaredFields())
                .map(el -> el.getName().toUpperCase()).toList();
        List<Integer> indexes = list2.stream().map(list::indexOf).toList();
            var lol = indexes.stream().map(list::get).toList();

//        data.forEach((name,val) -> val.forEach((key2,val2)-> System.out.println(val2)));
//        System.out.println("TRADEDATE: " + data.get("TRADEDATE"));
        return List.of();
    }
}
