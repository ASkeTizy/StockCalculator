package org.example.stockcalc;

import javafx.application.Application;
import org.example.stockcalc.controller.DividendCalculationController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
@Configuration
@ComponentScan(basePackages = "org.example.stockcalc")
public class Launcher {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Launcher.class);
//        var arr = context.getBeanDefinitionNames();
//        Arrays.stream(arr).forEach(System.out::println);
        var obj = context.getBean(DividendCalculationController.class);
        obj.calculateDividends();
//                DividendCalculationController controller = new DividendCalculationController();
//        Application.launch(HelloApplication.class, args);
    }
}
