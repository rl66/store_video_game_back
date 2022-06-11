package com.crediservir.store.invoice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InvoiceDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID invoiceId;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String invoiceDate;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    private Float invoiceTotal;

    @NotNull
    private UUID personId;

    public UUID getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(UUID invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Float getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(Float invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }
}
