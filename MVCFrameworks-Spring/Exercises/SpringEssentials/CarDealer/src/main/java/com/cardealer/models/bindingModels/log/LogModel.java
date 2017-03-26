package com.cardealer.models.bindingModels.log;

import com.cardealer.entities.enums.Operation;
import com.cardealer.models.bindingModels.user.LoggedUser;

import java.util.Date;

public class LogModel {
    private LoggedUser user;
    private String tableName;
    private Operation operation;
    private Date date;

    public LogModel() {
    }


    public LogModel(LoggedUser user, String tableName, Operation operation, Date date) {
        this.user = user;
        this.tableName = tableName;
        this.operation = operation;
        this.date = date;
    }

    public LoggedUser getUser() {
        return user;
    }

    public void setUser(LoggedUser user) {
        this.user = user;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
