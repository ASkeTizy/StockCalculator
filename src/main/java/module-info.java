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
//    requires org.example.stockcalc;

    opens org.example.stockcalc to javafx.fxml;
    exports org.example.stockcalc;
    exports org.example.stockcalc.repository;
    exports org.example.stockcalc.service;
    exports org.example.stockcalc.controller;
    exports org.example.stockcalc.repository.fileSource;
    exports org.example.stockcalc.repository.externalSource;
    exports org.example.stockcalc.config;
    exports org.example.stockcalc.fx.controller;
}