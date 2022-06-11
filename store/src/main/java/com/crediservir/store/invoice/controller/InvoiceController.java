package com.crediservir.store.invoice.controller;

import com.crediservir.store.invoice.dto.InvoiceDto;
import com.crediservir.store.invoice.service.InvoiceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

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

    @GetMapping("/{personId}")
    @ApiOperation("Get invoice by person id")
    @ApiResponses({@ApiResponse(code = 200, message = "success")})
    public ResponseEntity<InvoiceDto> findInvoiceByPersonId(@PathVariable UUID personId){
        return invoiceService.findInvoiceByPersonId(personId).map(invoice -> new ResponseEntity<>(modelMapper.map(invoice, InvoiceDto.class), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
