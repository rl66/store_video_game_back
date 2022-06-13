package com.crediservir.store.gamereference.service;

import com.crediservir.store.gamereference.entity.GameReference;
import com.crediservir.store.gamereference.repository.GameReferenceRepository;


import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Service
public class GameReferenceService {

    private final GameReferenceRepository gameReferenceRepository;


    public GameReferenceService(GameReferenceRepository gameReferenceRepository) {
        this.gameReferenceRepository = gameReferenceRepository;
    }

    public List<GameReference> getAll(){
        return gameReferenceRepository.findAll();
    }

    public List<GameReference> getAllReferenceByVideoGameId(UUID videoGameId){
        return gameReferenceRepository.getByVideoGameId(videoGameId);
    }

    public GameReference saveReference(GameReference gameReference){
        return gameReferenceRepository.save(gameReference);
    }

    public void deleteByVideoGameId(UUID videoGameId){
        gameReferenceRepository.deleteByVideoGameId(videoGameId);
    }

    public List<GameReference> getReferencesNoRent(LocalDate rentalDateStart) {
        return gameReferenceRepository.getGameReferenceByRentalDateEnd(rentalDateStart);
    }

}
