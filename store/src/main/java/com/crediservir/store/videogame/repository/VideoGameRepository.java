package com.crediservir.store.videogame.repository;

import com.crediservir.store.videogame.entity.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VideoGameRepository extends JpaRepository<VideoGame, UUID> {

    VideoGame findByVideoGameId(UUID videoGameId);

    Boolean existsByVideoGameName(String videoGameName);

    VideoGame findByConsoleTypeId(UUID videoGameId);

    @Query(value = "SELECT * FROM game_reference reference\n" +
            "INNER JOIN video_game game ON reference.video_game_id = game.video_game_id\n" +
            "AND EXISTS (SELECT rental_date_end FROM rental r WHERE r.rentaL_date_end < '2022-06-16')", nativeQuery = true)
    List<VideoGame> existsVideoGame(LocalDate rentalDateStart);

}
