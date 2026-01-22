package org.example.stockcalc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;


//@ComponentScan
public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        var context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("work");
        context.register(Launcher.class);
        context.refresh();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        stage.setTitle("Hello!");

        fxmlLoader.setControllerFactory(context::getBean);
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
//        fxmlLoader.load();
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) { launch(); }
}
