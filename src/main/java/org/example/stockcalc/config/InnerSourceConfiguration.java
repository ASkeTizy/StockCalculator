package org.example.stockcalc.config;

import org.example.stockcalc.repository.DividendsReceive;
import org.example.stockcalc.repository.JSONParser;
import org.example.stockcalc.repository.PositionReceive;
import org.example.stockcalc.repository.fileSource.DividendsFromFile;
import org.example.stockcalc.repository.fileSource.PositionReceiveFromFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration(proxyBeanMethods = false)
@Profile("work")
public class InnerSourceConfiguration {
    @Bean
    public DividendsReceive dividendsReceive(){
        return new DividendsFromFile(new JSONParser("dividends", "dividends"));
    }

    @Bean
    public PositionReceive positionReceive(){
        return new PositionReceiveFromFile(new JSONParser("data", "history"));
    }
}
