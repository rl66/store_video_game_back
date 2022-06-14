package com.crediservir.store.rental.service;

import com.crediservir.store.invoice.service.InvoiceService;
import com.crediservir.store.priceperconsole.entity.PricePerConsole;
import com.crediservir.store.priceperconsole.service.PricePerConsoleService;
import com.crediservir.store.rental.entity.Rental;
import com.crediservir.store.rental.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    private final InvoiceService invoiceService;

    private final PricePerConsoleService pricePerConsoleService;

    public RentalService(RentalRepository rentalRepository, InvoiceService invoiceService, PricePerConsoleService pricePerConsoleService) {
        this.rentalRepository = rentalRepository;
        this.invoiceService = invoiceService;
        this.pricePerConsoleService = pricePerConsoleService;
    }

    public List<Rental> getAll() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> findById(UUID rentalId) {
        return rentalRepository.findById(rentalId);
    }

    public Optional<Rental> getRentalByInvoiceId(UUID rentalId) {
        return rentalRepository.getRentalByInvoiceId(rentalId);
    }

    public void saveRental(Rental rental) {
        LocalDate start = rental.getRentalDateStart();
        LocalDate end = rental.getRentalDateEnd();
        var days = Duration.between(start.atStartOfDay(), end.atStartOfDay()).toDays();
        float dias = 0;
        float fines = 0;
        float invoiceTotal = 0;
        for (int i = 0; i <= days; i++) {
            LocalDate lDate = rental.getRentalDateStart().plusDays(i);
            Date newLDate = Date.from(Instant.from(lDate.atStartOfDay(ZoneId.systemDefault())));
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(newLDate);
            if (cal.get(Calendar.DAY_OF_WEEK) != 7 && cal.get(Calendar.DAY_OF_WEEK) != 1) {
                dias++;
            } else if (cal.get(Calendar.DAY_OF_WEEK) == 7 || cal.get(Calendar.DAY_OF_WEEK) == 1) {
                fines++;
            }
        }
        rentalRepository.save(rental);
        PricePerConsole pricePerConsoles = pricePerConsoleService.getPriceByGameReferenceId(rental.getGameReferenceId());
        float totalfines = fines * pricePerConsoles.getPricePerConsoleCash();
        if (dias < 3) {
            float discountDays = 0;
            float price = dias * pricePerConsoles.getPricePerConsoleCash() - discountDays;
            invoiceTotal = totalfines + price;
            rental.setRentalPrice(invoiceTotal);
            rental.setRentalDiscount(discountDays);
        }
        if (dias <= 5 && dias >= 3) {
            float discountDays = ((dias * pricePerConsoles.getPricePerConsoleCash() * 10) / 100);
            float price = dias * pricePerConsoles.getPricePerConsoleCash() - discountDays;
            invoiceTotal = totalfines + price;
            rental.setRentalPrice(invoiceTotal);
            rental.setRentalDiscount(discountDays);
        }
        if (dias <= 10 && dias >= 6) {
            float discountDays = ((dias * pricePerConsoles.getPricePerConsoleCash() * 15) / 100);
            float price = dias * pricePerConsoles.getPricePerConsoleCash() - discountDays;
            invoiceTotal = totalfines + price;
            rental.setRentalPrice(invoiceTotal);
            rental.setRentalDiscount(discountDays);
        }
        if (dias >= 10) {
            float discountDays = ((dias * pricePerConsoles.getPricePerConsoleCash() * 20) / 100);
            float price = dias * pricePerConsoles.getPricePerConsoleCash() - discountDays;
            invoiceTotal = totalfines + price;
            rental.setRentalPrice(invoiceTotal);
            rental.setRentalDiscount(discountDays);
        }
        rentalRepository.save(rental);
    }

    public Boolean ExistByRentalDate(LocalDate rentalDateStart, LocalDate rentalDateEnd) {
        return rentalRepository.existsByRentalDateStartIsAfterAndRentalDateEndIsBefore(rentalDateStart, rentalDateEnd);
    }


}
