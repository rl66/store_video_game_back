package com.crediservir.store.rental.repository;


import com.crediservir.store.rental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RentalRepository  extends JpaRepository<Rental, UUID> {

    Optional<Rental> getRentalByInvoiceId(UUID invoiceId);

    Rental findByRentalId(UUID rentalId);


    Boolean existsByRentalDateStartIsAfterAndRentalDateEndIsBefore(LocalDate rentalDateStart, LocalDate rentalDateEnd);



}
