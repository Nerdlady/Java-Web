package com.residentEvil.models.bindlingModels;

import com.residentEvil.annotations.IsInTheFuture;
import com.residentEvil.entities.enums.Magnitude;
import com.residentEvil.entities.enums.Mutation;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

public class EditVirusModel {
    private Long id;
    @NotBlank
    @Size(min = 3,max = 10,message = "Invalid name size.")
    private String name;

    @NotBlank
    @Size(min = 5,max = 100,message = "Invalid description size.")
    private String description;

    @Pattern(regexp = "^.*[Cc]orp.*$", message = "Creator doesn't contain Corp")
    private String creator;

    @Size(max = 50,message = "Invalid side effect size.")
    private String sideEffect;
    private Boolean isDeadly;
    private Boolean isCurable;

    @NotNull(message = "Should have a mutation.")
    private Mutation mutation;

    @Range(min = 0,max = 100)
    private Double turnoverRate;

    @Range(min = 1,max = 12)
    private Integer hoursToTurn;

    private Magnitude magnitude;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @IsInTheFuture(message = "Date is in the past.")
    private Date releasedOn;

    @NotEmpty(message = "Should pick capital.")
    private String[] capitalsNames;

    private Set<CapitalModel> capitals;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<CapitalModel> getCapitals() {
        return capitals;
    }

    public void setCapitals(Set<CapitalModel> capitals) {
        this.capitals = capitals;
    }
}
