package com.houc.bean;

import javafx.beans.property.SimpleStringProperty;

public class TaskProperty extends Task{

    private SimpleStringProperty taskNameProperty;

    public TaskProperty(){
        this.taskName = "";
        this.taskNameProperty = new SimpleStringProperty(taskName);
    }

    public TaskProperty(String taskName) {
        this.taskName = taskName;
        this.taskNameProperty = new SimpleStringProperty(taskName);
    }

    @Override
    public void setTaskName(String taskName) {
        super.setTaskName(taskName);
        this.taskNameProperty.set(taskName);
    }

    @Override
    public String getTaskName() {
        return super.getTaskName();
    }
}
