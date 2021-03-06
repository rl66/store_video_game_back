package com.crediservir.store.consoletype.dto;

import com.crediservir.store.priceperconsole.entity.PricePerConsole;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsoleTypeDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID consoleTypeId;

    private String consoleTypeName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<PricePerConsole> pricePerConsoles;

    public UUID getConsoleTypeId() {
        return consoleTypeId;
    }

    public void setConsoleTypeId(UUID consoleTypeId) {
        this.consoleTypeId = consoleTypeId;
    }

    public String getConsoleTypeName() {
        return consoleTypeName;
    }

    public void setConsoleTypeName(String consoleTypeName) {
        this.consoleTypeName = consoleTypeName;
    }

    public List<PricePerConsole> getPricePerConsoles() {
        return pricePerConsoles;
    }

    public void setPricePerConsoles(List<PricePerConsole> pricePerConsoles) {
        this.pricePerConsoles = pricePerConsoles;
    }
}
