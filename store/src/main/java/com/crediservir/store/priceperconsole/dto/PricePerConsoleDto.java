package com.crediservir.store.priceperconsole.dto;

import com.crediservir.store.consoletype.entity.ConsoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PricePerConsoleDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID pricePerConsoleId;

    private LocalDateTime pricePerConsoleDate;
    
    private Float pricePerConsoleCash;

    private UUID consoleTypeId;

    @JsonIgnore
    private ConsoleType consoleType;

    public UUID getPricePerConsoleId() {
        return pricePerConsoleId;
    }

    public void setPricePerConsoleId(UUID pricePerConsoleId) {
        this.pricePerConsoleId = pricePerConsoleId;
    }

    public LocalDateTime getPricePerConsoleDate() {
        return pricePerConsoleDate;
    }

    public void setPricePerConsoleDate(LocalDateTime pricePerConsoleDate) {
        this.pricePerConsoleDate = pricePerConsoleDate;
    }

    public ConsoleType getConsoleType() {
        return consoleType;
    }

    public void setConsoleType(ConsoleType consoleType) {
        this.consoleType = consoleType;
    }

    public Float getPricePerConsoleCash() {
        return pricePerConsoleCash;
    }

    public void setPricePerConsoleCash(Float pricePerConsoleCash) {
        this.pricePerConsoleCash = pricePerConsoleCash;
    }

    public UUID getConsoleTypeId() {
        return consoleTypeId;
    }

    public void setConsoleTypeId(UUID consoleTypeId) {
        this.consoleTypeId = consoleTypeId;
    }
}
