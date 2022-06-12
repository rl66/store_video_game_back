package com.crediservir.store.invoice.controller;

import com.crediservir.store.invoice.dto.InvoiceDto;
import com.crediservir.store.invoice.entity.Invoice;
import com.crediservir.store.invoice.service.InvoiceService;
import com.crediservir.store.person.entity.Person;
import com.crediservir.store.rental.dto.RentalDto;
import com.crediservir.store.rental.entity.Rental;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    private final ModelMapper modelMapper;


    public InvoiceController(InvoiceService invoiceService, ModelMapper modelMapper) {
        this.invoiceService = invoiceService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{invoiceId}")
    @ApiOperation("Get invoice by UUID")
    @ApiResponses({@ApiResponse(code = 200, message = "success")})
    public ResponseEntity<InvoiceDto> findInvoiceByUUID(@PathVariable UUID invoiceId){
        return invoiceService.findById(invoiceId).map(invoice -> new ResponseEntity<>(modelMapper.map(invoice, InvoiceDto.class), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/person/{personId}")
    @ApiOperation("Get invoice by person id")
    @ApiResponses({@ApiResponse(code = 200, message = "success")})
    public ResponseEntity<InvoiceDto> findInvoiceByPersonId(@PathVariable UUID personId){
        return invoiceService.findInvoiceByPersonId(personId).map(invoice -> new ResponseEntity<>(modelMapper.map(invoice, InvoiceDto.class), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{invoiceId}")
    @ApiOperation("Update person")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return the updated person"),
            @ApiResponse(code = 404, message = "Returns the data sent is invalid")
    })
    public ResponseEntity<Map<String, Object>> updateInvoice(@RequestBody InvoiceDto invoiceDto, @PathVariable("invoiceId") UUID invoiceId){
        Map<String, Object> map = new HashMap<>();
        map.put("message","Datos invalidos");
        if(invoiceService.findById(invoiceId).isPresent()){
            modelMapper.map(invoiceService.updateInvoice(invoiceId, modelMapper.map(invoiceDto, Invoice.class)), Person.class);
            map.put("message","factura asginada");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/saverental/{invoiceId}")
    @ApiOperation("Update person")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return the updated person"),
            @ApiResponse(code = 404, message = "Returns the data sent is invalid")
    })
    public ResponseEntity<Map<String, Object>> updateInvoiceRental(@RequestBody List<RentalDto> rentals, @PathVariable("invoiceId") UUID invoiceId){
        Map<String, Object> map = new HashMap<>();
        map.put("message","Datos invalidos");
        Optional<Invoice> invoice = invoiceService.findById(invoiceId);
            if(invoice.isPresent()){
                invoice.get().setRentals(rentals.stream().map(rentalDto -> modelMapper.map(rentalDto, Rental.class)).collect(Collectors.toList()));
                invoiceService.saveInvoice(invoice.get());
            }
            return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
