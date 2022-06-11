package com.crediservir.store.priceperconsole.entity;

import com.crediservir.store.consoletype.entity.ConsoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "price_per_console")
public class PricePerConsole {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "price_per_console_id")
    private UUID pricePerConsoleId;

    @Column(name = "price_per_console_date")
    private String pricePerConsoleDate;

    @Column(name = "price_per_console_cash")
    private Float pricePerConsoleCash;

    @Column(name = "console_type_id")
    private UUID consoleTypeId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "console_type_id", insertable = false, updatable = false)
    private ConsoleType consoleType;

    public UUID getPricePerConsoleId() {
        return pricePerConsoleId;
    }

    public void setPricePerConsoleId(UUID pricePerConsoleId) {
        this.pricePerConsoleId = pricePerConsoleId;
    }

    public String getPricePerConsoleDate() {
        return pricePerConsoleDate;
    }

    public void setPricePerConsoleDate(String pricePerConsoleDate) {
        this.pricePerConsoleDate = pricePerConsoleDate;
    }

    public Float getPricePerConsoleCash() {
        return pricePerConsoleCash;
    }

    public void setPricePerConsoleCash(Float pricePerConsoleCash) {
        this.pricePerConsoleCash = pricePerConsoleCash;
    }

    public UUID getConsoleTypeId() {
        return consoleTypeId;
    }

    public void setConsoleTypeId(UUID consoleTypeId) {
        this.consoleTypeId = consoleTypeId;
    }

    public ConsoleType getConsoleType() {
        return consoleType;
    }

    public void setConsoleType(ConsoleType consoleType) {
        this.consoleType = consoleType;
    }
}
