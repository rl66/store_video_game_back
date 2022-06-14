package com.crediservir.store.rental.controller;


import com.crediservir.store.rental.dto.RentalDto;
import com.crediservir.store.rental.entity.Rental;
import com.crediservir.store.rental.service.RentalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rental")
public class RentalController {

    private final RentalService rentalService;

    private final ModelMapper modelMapper;


    public RentalController(RentalService rentalService, ModelMapper modelMapper) {
        this.rentalService = rentalService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{rentalId}")
    @ApiOperation("Get rental by UUID")
    @ApiResponses({@ApiResponse(code = 200, message = "success")})
    public ResponseEntity<RentalDto> findRentalByUUID(@PathVariable UUID rentalId) {
        return rentalService.findById(rentalId).map(rental -> new ResponseEntity<>(modelMapper.map(rental, RentalDto.class), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/invoice/{invoiceId}")
    @ApiOperation("Get rental by invoiceId")
    @ApiResponses({@ApiResponse(code = 200, message = "success")})
    public ResponseEntity<RentalDto> findRentalByInvoiceId(@PathVariable UUID invoiceId) {
        return rentalService.getRentalByInvoiceId(invoiceId).map(rental -> new ResponseEntity<>(modelMapper.map(rental, RentalDto.class), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    @ApiOperation("Get all rental")
    @ApiResponses({@ApiResponse(code = 200, message = "success")})
    public ResponseEntity<List<RentalDto>> findAll() {
        List<Rental> rentals = rentalService.getAll();
        return new ResponseEntity<>(rentals.stream().map(rental -> modelMapper.map(rental, RentalDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/save/")
    @ApiOperation("Create rental game")
    @ApiResponses({@ApiResponse(code = 201, message = "rental game created"), @ApiResponse(code = 200, message = "rental game bad request")})
    public ResponseEntity<?> create(@Valid @RequestBody RentalDto rentalDto) {
        HashMap<String, String> map = new HashMap<>();
        if (rentalDto.getRentalDateStart().isBefore(LocalDate.now())) {
            map.put("message", "La fecha para alquilar un video juego debe ser de la fecha actual en adelante");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        if (rentalService.ExistByRentalDate(rentalDto.getRentalDateStart(), rentalDto.getRentalDateEnd())) {
            map.put("message", "Este video juego ya esta rentado");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        Object videoGame = rentalDto.getGameReferenceId();
        if (Objects.isNull(videoGame)) {
            map.put("message", "Debe asginar un video juego");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        HashMap<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "alquiler guardado");
        rentalService.saveRental(modelMapper.map(rentalDto, Rental.class));
        return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
    }

}
