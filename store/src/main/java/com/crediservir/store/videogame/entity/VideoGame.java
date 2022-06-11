package com.crediservir.store.videogame.entity;

import com.crediservir.store.consoletype.entity.ConsoleType;
import com.crediservir.store.gamereference.entity.GameReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "video_game")
public class VideoGame {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "video_game_id")
    private UUID videoGameId;

    @Column(name = "video_game_name")
    private String videoGameName;

    @Column(name = "video_game_year")
    private String videoGameYear;

    @Column(name = "video_game_gender")
    private String videoGameGender;

    @Column(name = "video_game_stock")
    private Long videoGameStock;

    @Column(name = "console_type_id")
    private UUID consoleTypeId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "console_type_id", insertable = false, updatable = false)
    private ConsoleType consoleType;

    @JsonIgnore
    @OneToMany(mappedBy = "videoGame", orphanRemoval=true)
    private List<GameReference> gameReferences;

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

    public ConsoleType getConsoleType() {
        return consoleType;
    }

    public void setConsoleType(ConsoleType consoleType) {
        this.consoleType = consoleType;
    }

    public Long getVideoGameStock() {
        return videoGameStock;
    }

    public void setVideoGameStock(Long videoGameStock) {
        this.videoGameStock = videoGameStock;
    }

    public List<GameReference> getGameReferences() {
        return gameReferences;
    }

    public void setGameReferences(List<GameReference> gameReferences) {
        this.gameReferences = gameReferences;
    }
}
