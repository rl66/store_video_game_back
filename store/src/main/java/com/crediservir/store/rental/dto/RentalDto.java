package com.crediservir.store.rental.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentalDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UUID rentalId;

    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String rentalDateStart;

    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String rentalDateEnd;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    private Float rentalDiscount;

    private UUID invoiceId;

    public UUID getRentalId() {
        return rentalId;
    }

    public void setRentalId(UUID rentalId) {
        this.rentalId = rentalId;
    }

    public String getRentalDateStart() {
        return rentalDateStart;
    }

    public void setRentalDateStart(String rentalDateStart) {
        this.rentalDateStart = rentalDateStart;
    }

    public String getRentalDateEnd() {
        return rentalDateEnd;
    }

    public void setRentalDateEnd(String rentalDateEnd) {
        this.rentalDateEnd = rentalDateEnd;
    }

    public Float getRentalDiscount() {
        return rentalDiscount;
    }

    public void setRentalDiscount(Float rentalDiscount) {
        this.rentalDiscount = rentalDiscount;
    }

    public UUID getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(UUID invoiceId) {
        this.invoiceId = invoiceId;
    }
}
