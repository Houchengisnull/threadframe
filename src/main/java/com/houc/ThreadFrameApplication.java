package com.houc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ThreadFrameApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        log.debug("启动程序");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/root.fxml"));
        Pane load = (Pane) fxmlLoader.load();

        primaryStage.setTitle("线程演示Demo");
        primaryStage.setScene(new Scene(load));
        primaryStage.show();
    }
}
