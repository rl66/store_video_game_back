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
    @Query(value = "SELECT * FROM price_per_console price\n" +
            "            INNER JOIN console_type console ON price.console_type_id = console.console_type_id\n" +
            "            INNER JOIN video_game game ON game.console_type_id = console.console_type_id\n" +
            "            INNER JOIN game_reference reference ON reference.video_game_id = game.video_game_id\n" +
            "            INNER JOIN rental rental ON rental.game_reference_id = reference.game_reference_id\n" +
            "            WHERE rental.game_reference_id =  :gameReferenceId\n" +
            "            AND price.price_per_console_date=(SELECT MAX(price_per_console_date) FROM price_per_console)", nativeQuery = true)
    PricePerConsole getByGameReferenceId(UUID gameReferenceId);



}
