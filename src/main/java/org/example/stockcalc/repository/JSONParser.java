package org.example.stockcalc.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.stockcalc.entity.Dividend;
import org.example.stockcalc.entity.PositionFromSource;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class JSONParser {

    private JsonNode root;
    private String pathToData;
    public JSONParser(InputStream inputStream, String pathToData) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            root = mapper.readTree(inputStream).get(pathToData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public JSONParser(String pathToData) {
        this.pathToData = pathToData;
    }

    public  int getIterations(String responseBody) {
//        var indexes = getNeededColumnsFromFile(List.of("INDEX","TOTAL"));
        var data = getListOfDataFromFile();
//        var index = data.getFirst().get(0);
        return Integer.valueOf(data.get(0).get(1));
//        return total;
    }

    public void setSource(String content){
        ObjectMapper mapper = new ObjectMapper();
        try {
            root = mapper.readTree(content).get(pathToData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


    public List<PositionFromSource> getPositions(String type) {
        var indexes = getNeededColumnsFromFile(Arrays.asList("BOARDID", "SHORTNAME", "TRADEDATE", "LEGALCLOSEPRICE"));
        var spittedList = getListOfDataFromFile();
        List<PositionFromSource> endedList = spittedList.stream().filter(el -> el.get(indexes.get("BOARDID")).equals("TQBR"))
                .map(el -> {
                    var date = dateParser(el.get(indexes.get("TRADEDATE")));
                    var shortName = el.get(indexes.get("SHORTNAME"));
                    var price = Double.parseDouble(el.get(indexes.get("LEGALCLOSEPRICE")));
                    return new PositionFromSource(shortName, date, price);
                }).toList();
        return endedList;
    }
    public List<Dividend> getDividends(String type) {

        var indexes = getNeededColumnsFromFile(Arrays.asList("registryclosedate", "value", "currencyid"));
        var spittedList = getListOfDataFromFile();
        List<Dividend> endedList = spittedList.stream()
                .map(el -> {
                    var date = dateParser(el.get(indexes.get("registryclosedate")));
                    var currencyId = el.get(indexes.get("currencyid"));
                    var value = Double.parseDouble(el.get(indexes.get("value")));
                    return new Dividend(currencyId, value, date);
                }).toList();
        return endedList;
    }

    public List<List<String>> getListOfDataFromFile() {
        var bla = root.get("data");
        List<List<String>> list = bla.valueStream().map(el -> el.valueStream().map(JsonNode::asText).toList()).toList();
        return list;
    }

    public Map<String, Integer> getNeededColumnsFromFile(List<String> fields) {

        JsonNode columnNode = root.get("columns");

        Map<String, Integer> indexes = new HashMap<>();
        for (int i = 0; i < columnNode.size(); i++) {

            for (var field : fields) {
                String fieldName = columnNode.get(i).asText();
                if (fieldName.equals(field)) {

                    indexes.put(fieldName, i);
                }

            }
        }
        return indexes;
    }

    public LocalDate dateParser(String s) {
        String preparedString = s.replaceAll("\\s+", "");
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.from(dtf.parse(preparedString));
    }
}
