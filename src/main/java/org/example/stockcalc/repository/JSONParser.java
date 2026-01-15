package org.example.stockcalc.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class JSONParser {

    private JsonNode root;

    public JSONParser(String fileName, String pathToData) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream input = JSONParser.class.getResourceAsStream("/source/" + fileName + ".json")) {
            root = mapper.readTree(input).get(pathToData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
