package com.crediservir.store.consoletype.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsoleTypeDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID consoleTypeId;

    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String consoleTypeName;

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
}
