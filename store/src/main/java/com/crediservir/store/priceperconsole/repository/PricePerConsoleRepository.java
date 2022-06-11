package com.crediservir.store.priceperconsole.repository;

import com.crediservir.store.priceperconsole.entity.PricePerConsole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PricePerConsoleRepository extends JpaRepository<PricePerConsole, UUID> {

    PricePerConsole findByPricePerConsoleId(UUID pricePerConsoleId);

    @Query(value = "SELECT * FROM price_per_console WHERE price_per_console_date=(SELECT MAX(price_per_console_date) FROM price_per_console WHERE console_type_id = :consoleTypeId)", nativeQuery = true)
    Optional<PricePerConsole> findVideoGameByConsoleTypeId(UUID consoleTypeId);


}
