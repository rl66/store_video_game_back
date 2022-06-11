package com.crediservir.store.priceperconsole.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PricePerConsoleDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID pricePerConsoleId;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String pricePerConsoleDate;

    @NotNull
    private Float pricePerConsoleCash;

    private UUID consoleTypeId;

    public UUID getPricePerConsoleId() {
        return pricePerConsoleId;
    }

    public void setPricePerConsoleId(UUID pricePerConsoleId) {
        this.pricePerConsoleId = pricePerConsoleId;
    }

    public String getPricePerConsoleDate() {
        return pricePerConsoleDate;
    }

    public void setPricePerConsoleDate(String pricePerConsoleDate) {
        this.pricePerConsoleDate = pricePerConsoleDate;
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
