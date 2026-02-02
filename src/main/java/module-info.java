module org.example.stockcalc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires eu.hansolo.tilesfx;
    requires java.net.http;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires com.fasterxml.jackson.databind;

    exports org.example.stockcalc;
    exports org.example.stockcalc.repository;
    exports org.example.stockcalc.service;
    exports org.example.stockcalc.controller;

    exports org.example.stockcalc.config;
    opens org.example.stockcalc to javafx.graphics, javafx.fxml, spring.core;
    opens org.example.stockcalc.controller to javafx.fxml, spring.beans, spring.context, spring.core;
    opens org.example.stockcalc.config to spring.core, spring.beans, spring.context;
}