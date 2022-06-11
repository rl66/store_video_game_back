package com.crediservir.store.priceperconsole.controller;


import com.crediservir.store.priceperconsole.dto.PricePerConsoleDto;
import com.crediservir.store.priceperconsole.entity.PricePerConsole;
import com.crediservir.store.priceperconsole.service.PricePerConsoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/priceperconsole")
public class PricePerConsoleController {

    private final PricePerConsoleService pricePerConsoleService;

    private final ModelMapper modelMapper;


    public PricePerConsoleController(PricePerConsoleService pricePerConsoleService, ModelMapper modelMapper) {
        this.pricePerConsoleService = pricePerConsoleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{pricePerConsoleId}")
    @ApiOperation("Get pricePerConseleId by UUID")
    @ApiResponses({@ApiResponse(code = 200, message = "success")})
    public ResponseEntity<PricePerConsoleDto> findPriceByUUID(@PathVariable UUID pricePerConsoleId){
        return pricePerConsoleService.findById(pricePerConsoleId).map(pricePerConsole-> new ResponseEntity<>(modelMapper.map(pricePerConsole, PricePerConsoleDto.class), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    @ApiOperation("Get all price per console")
    @ApiResponses({@ApiResponse(code = 200, message = "success")})
    public ResponseEntity<List<PricePerConsoleDto>> findAll(){
        List<PricePerConsole> pricePerConsoles = pricePerConsoleService.getAll();
        return new ResponseEntity<>(pricePerConsoles.stream().map(pricePerConsole -> modelMapper.map(pricePerConsole,PricePerConsoleDto.class))
                .collect(Collectors.toList()),HttpStatus.OK);
    }

    @PostMapping("/save/")
    @ApiOperation("Create price per console")
    @ApiResponses({@ApiResponse(code = 201, message = "price created"), @ApiResponse(code = 200, message = "price bad request")})
    public ResponseEntity<?> create(@Valid @RequestBody PricePerConsoleDto pricePerConsoleDto){
        HashMap<String, String> map = new HashMap<>();
        Object console = pricePerConsoleDto.getConsoleTypeId();
        if (Objects.isNull(console)){
            map.put("message", "Debe asginar un tipo de consola");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        PricePerConsole pricePerConsole = pricePerConsoleService.savePrice(modelMapper.map(pricePerConsoleDto, PricePerConsole.class));
        return new ResponseEntity<>(modelMapper.map(pricePerConsole, PricePerConsoleDto.class), HttpStatus.CREATED);
    }

//    @PutMapping("/update/{pricePerConsoleId}")
//    @ApiOperation("Update price")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Return the updated price"),
//            @ApiResponse(code = 404, message = "Returns the data sent is invalid")
//    })
//    public ResponseEntity<Map<String, Object>> updatePrice(@RequestBody PricePerConsoleDto pricePerConsoleDto, @PathVariable("pricePerConsoleId") UUID pricePerConsoleId){
//        Map<String, Object> map = new HashMap<>();
//        map.put("message","Datos invalidos");
//        if(pricePerConsoleService.findById(pricePerConsoleId).isPresent()){
//            map.put("message", modelMapper.map(pricePerConsoleService.updatePrice(pricePerConsoleId, modelMapper.map(pricePerConsoleDto, PricePerConsole.class)), PricePerConsole.class));
//            return new ResponseEntity<>(map, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
//    }
}
