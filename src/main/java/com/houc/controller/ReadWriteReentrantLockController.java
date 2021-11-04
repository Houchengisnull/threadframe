package com.houc.controller;

import com.houc.bean.TaskProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteReentrantLockController {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

    @FXML
    private TableView execute;
    @FXML
    private TableView wait;

    @FXML
    protected void addWriteTask(){


        execute.getItems().add(new TaskProperty("Hello"));
    }

    @FXML
    protected void addReadTask() {

    }

}
