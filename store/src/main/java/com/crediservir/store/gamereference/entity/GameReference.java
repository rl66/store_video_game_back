package com.crediservir.store.gamereference.entity;

import com.crediservir.store.rental.entity.Rental;
import com.crediservir.store.videogame.entity.VideoGame;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "game_reference")
public class GameReference {

    @Id
    @Column(name = "game_reference_id")
    private UUID gameReferenceId;

    @Column(name = "video_game_id")
    private UUID videoGameId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "video_game_id", insertable = false, updatable = false)
    private VideoGame videoGame;

    @JsonIgnore
    @OneToMany(mappedBy = "gameReference")
    private List<Rental> rentals;

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

    public VideoGame getVideoGame() {
        return videoGame;
    }

    public void setVideoGame(VideoGame videoGame) {
        this.videoGame = videoGame;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
