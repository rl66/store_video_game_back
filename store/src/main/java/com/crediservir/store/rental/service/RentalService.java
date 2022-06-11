package com.crediservir.store.rental.service;

import com.crediservir.store.rental.entity.Rental;
import com.crediservir.store.rental.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
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

    public Rental saveRental (Rental rental){
        return  rentalRepository.save(rental);
    }

}
