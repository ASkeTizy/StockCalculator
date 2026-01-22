package org.example.stockcalc;

import org.example.stockcalc.controller.CalculationController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
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


//        var obj = context.getBean(CalculationController.class);
//      var result =  obj.profitWithDividends();
//        var result1 =  obj.profitDividends();
//        var result2 =  obj.profitWithoutDividends();
//        var result3 =  obj.profitWithDividendsPercent();
//        var result4 =  obj.profitWithoutDividendsPercent();
//        System.out.println(result);
//        System.out.println(result1);
//        System.out.println(result2);
//        System.out.println(result3);
//        System.out.println(result4);
      //                DividendCalculationController controller = new DividendCalculationController();
//        Application.launch(HelloApplication.class, args);
    }
}
