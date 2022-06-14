package com.crediservir.store.priceperconsole.service;

import com.crediservir.store.priceperconsole.entity.PricePerConsole;
import com.crediservir.store.priceperconsole.repository.PricePerConsoleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PricePerConsoleService {

    private final PricePerConsoleRepository pricePerConsoleRepository;

    public PricePerConsoleService(PricePerConsoleRepository pricePerConsoleRepository) {
        this.pricePerConsoleRepository = pricePerConsoleRepository;
    }

    public List<PricePerConsole> getAll() {
        return pricePerConsoleRepository.findAll();
    }

    public Optional<PricePerConsole> findById(UUID pricePerConsoleId) {
        return pricePerConsoleRepository.findById(pricePerConsoleId);
    }

    public PricePerConsole savePrice(PricePerConsole pricePerConsole) {
        pricePerConsole.setPricePerConsoleDate(LocalDateTime.now());
        return pricePerConsoleRepository.save(pricePerConsole);
    }

    public Optional<PricePerConsole> findLastPriceByConsoleType(UUID consoleTypeId) {
        return pricePerConsoleRepository.findVideoGameByConsoleTypeId(consoleTypeId);
    }

    public PricePerConsole getPriceByGameReferenceId(UUID gameReferenceId) {
        return pricePerConsoleRepository.getByGameReferenceId(gameReferenceId);
    }

}
