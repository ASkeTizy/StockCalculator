package org.example.stockcalc.config;

import org.example.stockcalc.condition.OnSpecificHostCondition;
import org.example.stockcalc.repository.DividendsReceive;
import org.example.stockcalc.repository.JSONParser;
import org.example.stockcalc.repository.PositionReceive;
import org.example.stockcalc.repository.externalSource.DividendsReceiveMosStock;
import org.example.stockcalc.repository.externalSource.PositionReceiveMosStock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration(proxyBeanMethods = false)

public class ExternalSourceConfiguration {
    @Bean
    public DividendsReceive dividendsReceive(){
        try {

            return new DividendsReceiveMosStock(new JSONParser("dividends"));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @Bean
    public PositionReceive positionReceive(){
        try {
            return new PositionReceiveMosStock(new JSONParser("history"));

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
