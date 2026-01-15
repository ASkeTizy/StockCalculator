package org.example.stockcalc;

import org.example.stockcalc.config.ExternalSourceConfiguration;
import org.example.stockcalc.controller.DividendCalculationController;
import org.example.stockcalc.config.InnerSourceConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
@ComponentScan
public class Launcher {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("work");
//      context.scan("org.example.stockcalc");
        context.register(Launcher.class);
//        context.register(ExternalSourceConfiguration.class);
        context.refresh();


        var obj = context.getBean(DividendCalculationController.class);
        obj.calculateDividends();
//                DividendCalculationController controller = new DividendCalculationController();
//        Application.launch(HelloApplication.class, args);
    }
}
