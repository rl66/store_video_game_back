package com.crediservir.store.gamereference.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameReferenceDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID gameReferenceId;

    private UUID videoGameId;

    public UUID getGameReferenceId() {
        return gameReferenceId;
    }

    public void setGameReferenceId(UUID gameReferenceId) {
        this.gameReferenceId = gameReferenceId;
    }

    public UUID getVideoGameId() {
        return videoGameId;
    }

    public void setVideoGameId(UUID videoGameId) {
        this.videoGameId = videoGameId;
    }
}
