package com.houc.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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

    private HashMap<String, String> sidebarItems = new HashMap<>();
    private String sidebarPath = "/sidebar.properties";
    private EventHandler toggleSidebarHandler = new ToggleHandle();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Properties properties = new Properties();
        FileReader in = null;
        try {
            in = new FileReader(getClass().getResource(sidebarPath).getFile());
            properties.load(in);
            Set<Object> keys = properties.keySet();
            for (Object key : keys) {
                String keyValue = (String) key;
                String substring = keyValue.substring(keyValue.lastIndexOf(".") + 1);
                // 存放侧边栏及其对应fxml
                String fxmlName = properties.getProperty((String) key);
                sidebarItems.put(substring, fxmlName);
                // 加载侧边栏
                Label label = new Label(substring);
                label.setOnMouseClicked(toggleSidebarHandler);
                sidebar.getItems().add(label);
            }
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @FXML
    public void click(Event event) throws IOException {
        Label source = (Label) event.getSource();
        String sidebarItem = source.getText();
        String fxmlName = sidebarItems.get(sidebarItem);
        String fxmlPath = "/fxml/" + fxmlName + ".fxml";
        log.debug("click sidebar [{}], then change to [{}] pane", sidebarItem, fxmlName);
        log.debug("fxml path : {}", fxmlPath);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Pane load = (Pane) loader.load();

        root.setCenter(load);
    }

    class ToggleHandle implements EventHandler {

        @SneakyThrows
        @Override
        public void handle(Event event) {
            click(event);
        }
    }

}
