package org.example.stockcalc.repository;

import org.example.stockcalc.entity.PositionFromSource;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@Primary
public class PositionReceiveImpl implements PositionReceive{
    public PositionReceiveImpl() {

    }

    @Override
    public List<String> getPositionCodes() {
        return List.of();
    }

    @Override
    public List<PositionFromSource> getPositionsByKeyAndDate(String name, LocalDate startDate, LocalDate endDate) {
//        ObjectMapper mapper = new ObjectMapper(); // читаем JSON в Map
//        Map<String, LinkedHashMap> data = null;
//        try (InputStream input = PositionReceiveImpl.class.getResourceAsStream("/source/data.json")) {
//            data = mapper.readValue(input, Map.class);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        String s = data.get("history").get("columns").toString();
//        var bla = data.get("history").get("data").toString();
//        String clean = cleanString(bla);
//        String columns = cleanString(s);
//        var prices = getDataFromJSON(bla);
//        var twoDemensialArray = clean.split("\\],\\s*\\[");
//        var spittedList = Arrays.asList(twoDemensialArray).stream().map(el -> el.split(",")).toList();
//        var list = getDataFromJSON(columns);
//        var list2 = Arrays.asList("BOARDID","SHORTNAME", "TRADEDATE", "LEGALCLOSEPRICE");
//        Map<String,Integer> indexes = list2.stream()
//                .collect(Collectors.toMap(el->el,list::indexOf));
        var parser = new JSONParser("data","history");
        var indexes = parser.getNeededColumnsFromFile(Arrays.asList("BOARDID","SHORTNAME", "TRADEDATE", "LEGALCLOSEPRICE"));
        var spittedList = parser.getListOfDataFromFile();
        List<PositionFromSource> endedList = spittedList.stream().filter(el -> el[indexes.get("BOARDID")].equals("TQBR"))
                .map(el -> {
                    var date = dateParser(el[indexes.get("TRADEDATE")]);
                    var shortName = el[indexes.get("SHORTNAME")];
                    var price = Double.parseDouble(el[indexes.get("LEGALCLOSEPRICE")]);
                    return new PositionFromSource(shortName, date, price);
                }).toList();
       List<PositionFromSource> finalArr =  endedList.stream().filter(el -> el.tradeDate().isAfter(startDate) && el.tradeDate().isBefore(endDate)).toList();
        endedList.forEach(System.out::println);
        return finalArr;
    }
    private List<String> getDataFromJSON(String s) {
        var stroks = s.split(", ");
        return Arrays.asList(stroks);
    }
    private String cleanString(String s) {
        return s.substring(1, s.length() - 1);
    }
    private LocalDate dateParser(String s) {
        String preparedString = s.replaceAll("\\s+", "");
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.from(dtf.parse(preparedString));
    }
}
