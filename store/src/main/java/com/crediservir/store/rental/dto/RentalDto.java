package com.crediservir.store.rental.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentalDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID rentalId;

    private LocalDate rentalDateStart;

    private LocalDate rentalDateEnd;

    private Float rentalDiscount;

    private Float rentalPrice;

    private UUID gameReferenceId;

    private UUID invoiceId;

    public UUID getRentalId() {
        return rentalId;
    }

    public void setRentalId(UUID rentalId) {
        this.rentalId = rentalId;
    }

    public LocalDate getRentalDateStart() {
        return rentalDateStart;
    }

    public void setRentalDateStart(LocalDate rentalDateStart) {
        this.rentalDateStart = rentalDateStart;
    }

    public LocalDate getRentalDateEnd() {
        return rentalDateEnd;
    }

    public void setRentalDateEnd(LocalDate rentalDateEnd) {
        this.rentalDateEnd = rentalDateEnd;
    }

    public Float getRentalDiscount() {
        return rentalDiscount;
    }

    public void setRentalDiscount(Float rentalDiscount) {
        this.rentalDiscount = rentalDiscount;
    }

    public UUID getGameReferenceId() {
        return gameReferenceId;
    }

    public void setGameReferenceId(UUID gameReferenceId) {
        this.gameReferenceId = gameReferenceId;
    }

    public UUID getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(UUID invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Float getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(Float rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
}
