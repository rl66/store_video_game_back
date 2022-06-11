package com.crediservir.store.priceperconsole.repository;

import com.crediservir.store.priceperconsole.entity.PricePerConsole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PricePerConsoleRepository extends JpaRepository<PricePerConsole, UUID> {

    PricePerConsole findByPricePerConsoleId(UUID pricePerConsoleId);

}
