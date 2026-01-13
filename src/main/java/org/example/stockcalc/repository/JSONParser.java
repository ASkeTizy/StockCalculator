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

    JsonNode root;

    public JSONParser(String fileName, String pathToData) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream input = JSONParser.class.getResourceAsStream("/source/" + fileName + ".json")) {
            root = mapper.readTree(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<String[]> getListOfDataFromFile() {
        var bla = root.get("data");
        List<String[]> list =new ArrayList<>();
        bla.forEach(el ->{
            el.
        });

        return List.of();
    }

    public List<Integer> getNeededColumnsFromFile(List<String> fields) {

        JsonNode columnNode = root.get("history").get("columns");

        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < columnNode.size(); i++) {

            for (var field : fields) {
                if (columnNode.get(i).asText().equals(field)) {

                    indexes.add(i);
                }

            }
        }
        return indexes;
    }

    private List<String> getDataFromJSON(String s) {
        var stroks = s.split(", ");
        return Arrays.asList(stroks);
    }

    private String cleanString(String s) {
        return s.substring(1, s.length() - 1);
    }

    public LocalDate dateParser(String s) {
        String preparedString = s.replaceAll("\\s+", "");
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.from(dtf.parse(preparedString));
    }
}
