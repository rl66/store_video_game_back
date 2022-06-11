package com.crediservir.store.rental.service;

import com.crediservir.store.priceperconsole.service.PricePerConsoleService;
import com.crediservir.store.rental.entity.Rental;
import com.crediservir.store.rental.repository.RentalRepository;
import com.crediservir.store.videogame.entity.VideoGame;
import com.crediservir.store.videogame.repository.VideoGameRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    private final VideoGameRepository videoGameRepository;

    private final PricePerConsoleService pricePerConsoleService;

    public RentalService(RentalRepository rentalRepository, VideoGameRepository videoGameRepository, PricePerConsoleService pricePerConsoleService) {
        this.rentalRepository = rentalRepository;
        this.videoGameRepository = videoGameRepository;
        this.pricePerConsoleService = pricePerConsoleService;
    }


    public List<Rental> getAll(){
        return rentalRepository.findAll();
    }

    public Optional<Rental> findById(UUID rentalId){
        return  rentalRepository.findById(rentalId);
    }

    public Optional<Rental> getRentalByInvoiceId(UUID rentalId){
        return rentalRepository.getRentalByInvoiceId(rentalId);
    }

    public void saveRental (Rental rental){
        LocalDate start = rental.getRentalDateStart();
        LocalDate end = rental.getRentalDateEnd();
        var days = Duration.between(start.atStartOfDay(), end.atStartOfDay()).toDays();
        float dias = 0;
        for (int i = 0; i<= days; i++){
            LocalDate lDate = rental.getRentalDateStart().plusDays(i);
            Date newLDate = Date.from(Instant.from(lDate.atStartOfDay(ZoneId.systemDefault())));
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(newLDate);
            if (cal.get(Calendar.DAY_OF_WEEK) != 7 && cal.get(Calendar.DAY_OF_WEEK) != 1){
                dias++;
            }
        }
//        if (dias <= 5 && dias>= 3){
//            rental.setRentalDiscount(rentalRepository.);
//        }
        rental.setRentalDiscount(dias);
        rentalRepository.save(rental);
    }

    public Boolean ExistByRentalDate(LocalDate rentalDateStart, LocalDate rentalDateEnd){
        return rentalRepository.existsByRentalDateStartIsAfterAndRentalDateEndIsBefore(rentalDateStart, rentalDateEnd);
    }


}
