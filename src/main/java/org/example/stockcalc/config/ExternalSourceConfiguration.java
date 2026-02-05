package org.example.stockcalc.config;

import org.example.stockcalc.condition.OnSpecificHostCondition;
import org.example.stockcalc.repository.DividendsReceive;
import org.example.stockcalc.repository.PositionReceive;
import org.example.stockcalc.repository.externalSource.DividendsReceiveMosStock;
import org.example.stockcalc.repository.externalSource.PositionReceiveMosStock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration(proxyBeanMethods = false)
@Conditional(OnSpecificHostCondition.class)
public class ExternalSourceConfiguration {
    @Bean
    public DividendsReceive dividendsReceive(){
        return new DividendsReceiveMosStock();
    }
    @Bean
    public PositionReceive positionReceive(){
        return new PositionReceiveMosStock();
    }
}
