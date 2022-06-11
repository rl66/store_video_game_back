package com.crediservir.store.consoletype.entity;

import com.crediservir.store.priceperconsole.entity.PricePerConsole;
import com.crediservir.store.videogame.entity.VideoGame;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "console_type")
public class ConsoleType {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "console_type_id")
    private UUID consoleTypeId;

    @Column(name = "console_type_name")
    private String consoleTypeName;

    @JsonIgnore
    @OneToMany(mappedBy = "consoleType")
    private List<VideoGame> videoGames;


    @OneToMany(mappedBy = "consoleType")
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

    public List<VideoGame> getVideoGames() {
        return videoGames;
    }

    public void setVideoGames(List<VideoGame> videoGames) {
        this.videoGames = videoGames;
    }

    public List<PricePerConsole> getPricePerConsoles() {
        return pricePerConsoles;
    }

    public void setPricePerConsoles(List<PricePerConsole> pricePerConsoles) {
        this.pricePerConsoles = pricePerConsoles;
    }

}
