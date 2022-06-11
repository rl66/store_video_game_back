package com.crediservir.store.rental.repository;

import com.crediservir.store.rental.entity.Rental;
import com.crediservir.store.videogame.entity.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RentalRepository  extends JpaRepository<Rental, UUID> {

    Optional<Rental> getRentalByInvoiceId(UUID invoiceId);

    Rental findByRentalId(UUID rentalId);


    Boolean existsByRentalDateStartIsAfterAndRentalDateEndIsBefore(LocalDate rentalDateStart, LocalDate rentalDateEnd);

    @Query(value = "SELECT video_game_id FROM game_reference INNER JOIN rental ON rental.game_reference_id = :gameReferenceId", nativeQuery = true)
    List<UUID> findByGameReferenceId(UUID gameReferenceId);

}
