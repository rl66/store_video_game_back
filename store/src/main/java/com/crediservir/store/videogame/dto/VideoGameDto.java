package com.crediservir.store.videogame.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoGameDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID videoGameId;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String videoGameName;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String videoGameYear;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String videoGameGender;

    private Long videoGameStock;

    private UUID consoleTypeId;

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
}
