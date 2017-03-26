package com.residentEvil.models.viewModels;

import com.residentEvil.entities.enums.Magnitude;
import com.residentEvil.entities.enums.Mutation;

import java.util.Date;

public class ModifiableVirusView {

    private String name;
    private String description;
    private String creator;
    private String sideEffect;
    private Boolean isDeadly;
    private Boolean isCurable;
    private Mutation mutation;
    private Double turnoverRate;
    private Integer hoursToTurn;
    private Magnitude magnitude;
    private Date releasedOn;
    private String[] capitalsNames;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getSideEffect() {
        return sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    public Boolean getDeadly() {
        return isDeadly;
    }

    public void setDeadly(Boolean deadly) {
        isDeadly = deadly;
    }

    public Boolean getCurable() {
        return isCurable;
    }

    public void setCurable(Boolean curable) {
        isCurable = curable;
    }

    public Mutation getMutation() {
        return mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    public Double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Integer getHoursToTurn() {
        return hoursToTurn;
    }

    public void setHoursToTurn(Integer hoursToTurn) {
        this.hoursToTurn = hoursToTurn;
    }

    public Magnitude getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    public Date getReleasedOn() {
        return releasedOn;
    }

    public void setReleasedOn(Date releasedOn) {
        this.releasedOn = releasedOn;
    }

    public String[] getCapitalsNames() {
        return capitalsNames;
    }

    public void setCapitalsNames(String[] capitalsNames) {
        this.capitalsNames = capitalsNames;
    }
}
