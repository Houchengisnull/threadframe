package com.houc.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

@Slf4j
public class RootController implements Initializable {

    @FXML private ListView<Label> sidebar;
    @FXML private Pane center;
    @FXML private BorderPane root;

    public RootController(){
        log.debug("初始化 RootController");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Properties properties = new Properties();
        FileReader in = null;
        try {
            in = new FileReader(getClass().getResource("/sidebar.properties").getFile());
            properties.load(in);
            Set<Object> keys = properties.keySet();
            for (Object key : keys) {
                String keyValue = (String) key;
                String substring = keyValue.substring(keyValue.lastIndexOf(".") + 1);
                sidebar.getItems().add(new Label(substring));
            }
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    static boolean a = true;

    @FXML
    public void click(Event event) throws IOException {
        Pane load = null;
        if (a) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ReadWriteReentrantLock.fxml"));
            load = (Pane) fxmlLoader.load();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/helloworld.fxml"));
            load= (Pane) fxmlLoader.load();
        }

        a = !a;
        root.setCenter(load);
    }

}
