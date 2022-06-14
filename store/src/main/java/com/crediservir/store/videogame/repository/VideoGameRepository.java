package com.crediservir.store.videogame.repository;

import com.crediservir.store.videogame.entity.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface VideoGameRepository extends JpaRepository<VideoGame, UUID> {


    Boolean existsByVideoGameName(String videoGameName);



}
