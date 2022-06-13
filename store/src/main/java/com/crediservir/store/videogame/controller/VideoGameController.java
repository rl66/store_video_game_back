package com.crediservir.store.videogame.controller;

import com.crediservir.store.gamereference.dto.GameReferenceDto;
import com.crediservir.store.gamereference.entity.GameReference;
import com.crediservir.store.gamereference.service.GameReferenceService;
import com.crediservir.store.videogame.dto.VideoGameDto;
import com.crediservir.store.videogame.entity.VideoGame;
import com.crediservir.store.videogame.service.VideoGameService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/videogame")
public class VideoGameController {

    private final VideoGameService videoGameService;

    private final GameReferenceService gameReferenceService;
    private final ModelMapper modelMapper;

    public VideoGameController(VideoGameService videoGameService, GameReferenceService gameReferenceService, ModelMapper modelMapper) {
        this.videoGameService = videoGameService;
        this.gameReferenceService = gameReferenceService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{videoGameId}")
    @ApiOperation("Get video game by UUID")
    @ApiResponses({@ApiResponse(code = 200, message = "success")})
    public ResponseEntity<VideoGameDto> findVideoGameByUUID(@PathVariable UUID videoGameId){
        return videoGameService.findById(videoGameId).map(videoGame -> new ResponseEntity<>(modelMapper.map(videoGame, VideoGameDto.class), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    @ApiOperation("Get all video games")
    @ApiResponses({@ApiResponse(code = 200, message = "success")})
    public ResponseEntity<List<VideoGameDto>> findAll(){
        List<VideoGame> videoGames = videoGameService.getAll();
        return new ResponseEntity<>(videoGames.stream().map(videoGame -> modelMapper.map(videoGame,VideoGameDto.class))
                .collect(Collectors.toList()),HttpStatus.OK);
    }
    @GetMapping("/videogamewithoutrent/{rentalDateStart}")
    @ApiParam()
    @ApiOperation("Get all video games")
    @ApiResponses({@ApiResponse(code = 200, message = "success")})
    public ResponseEntity<List<GameReferenceDto>> findVideoGameWithoutRent(@PathVariable String rentalDateStart){
        List<GameReference> gameReferences = gameReferenceService.getReferencesNoRent(LocalDate.parse(rentalDateStart));
        return new ResponseEntity<>(gameReferences.stream().map(gameReference -> modelMapper.map(gameReference,GameReferenceDto.class))
                .collect(Collectors.toList()),HttpStatus.OK);
    }

    @PostMapping("/save/")
    @ApiOperation("Create video game")
    @ApiResponses({@ApiResponse(code = 201, message = "video game created"), @ApiResponse(code = 200, message = "video game bad request")})
    public ResponseEntity<?> create(@Valid @RequestBody VideoGameDto videoGameDto){
        HashMap<String, String> map = new HashMap<>();
        if (videoGameService.existVideoGameByName(videoGameDto.getVideoGameName())){
            map.put("message", "Este video juego con este nombre ya existe");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        Object console = videoGameDto.getConsoleTypeId();
        if (Objects.isNull(console)){
            map.put("message", "Debe asginar un tipo de consola");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        HashMap<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "juego guardado");
//        VideoGame videoGame = videoGameService.sa(modelMapper.map(videoGameDto, VideoGame.class));
        videoGameService.saveVideoGame(modelMapper.map(videoGameDto, VideoGame.class));
        return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
    }

    @PutMapping("/update/{videoGameId}")
    @ApiOperation("Update person")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return the updated video game"),
            @ApiResponse(code = 404, message = "Returns the data sent is invalid")
    })
    public ResponseEntity<Map<String, Object>> updatePerson(@RequestBody VideoGameDto videoGameDto, @PathVariable("videoGameId") UUID videoGameId){
        Map<String, Object> map = new HashMap<>();
        map.put("message","Datos invalidos");
        if(videoGameService.findById(videoGameId).isPresent()){
            map.put("message", modelMapper.map(videoGameService.updateVideoGameByUuid(videoGameId, modelMapper.map(videoGameDto, VideoGame.class)), VideoGame.class));
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

//    @GetMapping("/price/{consoleTypeId}")
//    @ApiOperation("Get last price by console Type Id")
//    @ApiResponses({@ApiResponse(code = 200, message = "success")})
//    public ResponseEntity<PricePerConsoleDto> findLastPriceByConsoleTypeId(@PathVariable UUID consoleTypeId){
//        return pricePerConsoleService.findLastPriceByConsoleType(consoleTypeId).map(console -> new ResponseEntity<>(modelMapper.map(console, PricePerConsoleDto.class), HttpStatus.OK))
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
}
