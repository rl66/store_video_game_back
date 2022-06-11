package com.crediservir.store.videogame.service;

import com.crediservir.store.gamereference.entity.GameReference;
import com.crediservir.store.gamereference.service.GameReferenceService;
import com.crediservir.store.videogame.entity.VideoGame;
import com.crediservir.store.videogame.repository.VideoGameRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VideoGameService {


    private final VideoGameRepository videoGameRepository;

    private final GameReferenceService gameReferenceService;

    public VideoGameService(VideoGameRepository videoGameRepository, GameReferenceService gameReferenceService) {
        this.videoGameRepository = videoGameRepository;
        this.gameReferenceService = gameReferenceService;
    }

    public List<VideoGame> getAll() {
        return videoGameRepository.findAll();
    }

    public Optional<VideoGame> findById(UUID videoGameId) {
        return videoGameRepository.findById(videoGameId);
    }

    public Boolean existVideoGameByName(String videoGameName) {
        return videoGameRepository.existsByVideoGameName(videoGameName);
    }

    public void saveVideoGame(VideoGame videoGame) {
        videoGameRepository.save(videoGame);
        GameReference gameReference = new GameReference();
        for (int i = 0; i <= videoGame.getVideoGameStock(); i++) {
            gameReference.setVideoGameId(videoGame.getVideoGameId());
            gameReference.setGameReferenceId(UUID.randomUUID());
            gameReferenceService.saveReference(gameReference);
        }
    }


    public VideoGame updateVideoGameByUuid(UUID videoGameId, VideoGame videoGame) {
        return videoGameRepository.findById(videoGameId).map(videoGame1 -> {
            videoGame1.setVideoGameName((videoGame.getVideoGameName() != null) ? videoGame.getVideoGameName() : videoGame1.getVideoGameName());
            videoGame1.setVideoGameGender((videoGame.getVideoGameGender() != null) ? videoGame.getVideoGameGender() : videoGame1.getVideoGameGender());
            videoGame1.setVideoGameYear((videoGame.getVideoGameYear() != null) ? videoGame.getVideoGameYear() : videoGame1.getVideoGameYear());
            return videoGameRepository.save(videoGame1);
        }).orElse(null);
    }

    public void deleteVideoGameById(UUID videoGameId) {
        videoGameRepository.deleteById(videoGameId);
    }


}
