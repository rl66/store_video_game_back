package com.crediservir.store.rental.entity;

import com.crediservir.store.gamereference.entity.GameReference;
import com.crediservir.store.invoice.entity.Invoice;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "rental_id")
    private UUID rentalId;

    @Column(name = "rental_date_start")
    private LocalDate rentalDateStart;

    @Column(name = "rental_date_end")
    private LocalDate rentalDateEnd;

    @Column(name = "rental_discount")
    private Float rentalDiscount;

    @Column(name = "invoice_id")
    private UUID invoiceId;

    @Column(name = "game_reference_id")
    private UUID gameReferenceId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoice_id", insertable = false, updatable = false)
    private Invoice invoice;


    @ManyToOne
    @JoinColumn(name = "game_reference_id", insertable = false, updatable = false)
    private GameReference gameReference;

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

    public UUID getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(UUID invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public UUID getGameReferenceId() {
        return gameReferenceId;
    }

    public void setGameReferenceId(UUID gameReferenceId) {
        this.gameReferenceId = gameReferenceId;
    }

    public GameReference getGameReference() {
        return gameReference;
    }

    public void setGameReference(GameReference gameReference) {
        this.gameReference = gameReference;
    }
}
