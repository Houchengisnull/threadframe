module threadframe {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires slf4j.api;
    requires transitive org.mapstruct.processor;

    exports com.houc;

    opens com.houc.controller to javafx.fxml;
    opens com.houc.bean to javafx.base;
    /*opens javafx.event to java.base;*/
}