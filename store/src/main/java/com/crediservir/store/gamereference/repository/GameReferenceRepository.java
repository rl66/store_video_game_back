package com.crediservir.store.gamereference.repository;

import com.crediservir.store.gamereference.entity.GameReference;
import com.crediservir.store.videogame.entity.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GameReferenceRepository extends JpaRepository<GameReference, UUID> {

    List<GameReference> getByVideoGameId(UUID videoGameId);
     void deleteByVideoGameId(UUID videoGameId);


}
