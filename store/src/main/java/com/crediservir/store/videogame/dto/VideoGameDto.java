package com.crediservir.store.videogame.dto;

import com.crediservir.store.consoletype.entity.ConsoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoGameDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID videoGameId;


    private String videoGameName;


    private String videoGameYear;

    private String videoGameGender;

    private Long videoGameStock;

    private UUID consoleTypeId;

    @JsonIgnore
    private ConsoleType consoleType;

    public UUID getVideoGameId() {
        return videoGameId;
    }

    public void setVideoGameId(UUID videoGameId) {
        this.videoGameId = videoGameId;
    }

    public String getVideoGameName() {
        return videoGameName;
    }

    public void setVideoGameName(String videoGameName) {
        this.videoGameName = videoGameName;
    }

    public String getVideoGameYear() {
        return videoGameYear;
    }

    public void setVideoGameYear(String videoGameYear) {
        this.videoGameYear = videoGameYear;
    }

    public String getVideoGameGender() {
        return videoGameGender;
    }

    public void setVideoGameGender(String videoGameGender) {
        this.videoGameGender = videoGameGender;
    }

    public UUID getConsoleTypeId() {
        return consoleTypeId;
    }

    public void setConsoleTypeId(UUID consoleTypeId) {
        this.consoleTypeId = consoleTypeId;
    }

    public Long getVideoGameStock() {
        return videoGameStock;
    }

    public void setVideoGameStock(Long videoGameStock) {
        this.videoGameStock = videoGameStock;
    }

    public ConsoleType getConsoleType() {
        return consoleType;
    }

    public void setConsoleType(ConsoleType consoleType) {
        this.consoleType = consoleType;
    }
}
