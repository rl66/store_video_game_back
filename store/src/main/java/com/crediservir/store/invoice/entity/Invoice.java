package com.crediservir.store.invoice.entity;

import com.crediservir.store.person.entity.Person;
import com.crediservir.store.rental.entity.Rental;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @Column(name = "invoice_id")
    private UUID invoiceId;

    @Column(name = "invoice_date")
    private LocalDate invoiceDate;

    @Column(name = "invoice_total")
    private Float invoiceTotal;

    @Column(name = "person_id")
    private UUID personId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;

    @JsonIgnore
    @OneToMany(mappedBy = "invoice")
    private List<Rental> rentals;

    public UUID getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(UUID invoiceId) {
        this.invoiceId = invoiceId;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
