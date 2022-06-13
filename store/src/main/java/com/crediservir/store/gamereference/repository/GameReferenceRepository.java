package com.crediservir.store.gamereference.repository;

import com.crediservir.store.gamereference.entity.GameReference;
import com.crediservir.store.videogame.entity.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface GameReferenceRepository extends JpaRepository<GameReference, UUID> {

    List<GameReference> getByVideoGameId(UUID videoGameId);
     void deleteByVideoGameId(UUID videoGameId);

     @Query(value = "SELECT * FROM game_reference reference \n" +
             "JOIN video_game game ON game.video_game_id = reference.video_game_id\n" +
             "WHERE reference.game_reference_id NOT IN (SELECT game_reference_id FROM rental WHERE rental_date_end >= :rentalDateStart)\n",nativeQuery = true)
    List<GameReference> getGameReferenceByRentalDateEnd(LocalDate rentalDateStart);
}
