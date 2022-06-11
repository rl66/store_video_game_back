package com.crediservir.store.priceperconsole.service;

import com.crediservir.store.priceperconsole.entity.PricePerConsole;
import com.crediservir.store.priceperconsole.repository.PricePerConsoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PricePerConsoleService {

    private final PricePerConsoleRepository pricePerConsoleRepository;

    public PricePerConsoleService(PricePerConsoleRepository pricePerConsoleRepository) {
        this.pricePerConsoleRepository = pricePerConsoleRepository;
    }

    public List<PricePerConsole> getAll(){
        return pricePerConsoleRepository.findAll();
    }

    public Optional<PricePerConsole> findById(UUID pricePerConsoleId){
        return pricePerConsoleRepository.findById(pricePerConsoleId);
    }

    public PricePerConsole savePrice(PricePerConsole pricePerConsole){
        return pricePerConsoleRepository.save(pricePerConsole);
    }

    public PricePerConsole updatePrice(UUID pricePerConsoleId, PricePerConsole pricePerConsole) {
        return pricePerConsoleRepository.findById(pricePerConsoleId).map(pricePerConsole1 -> {
            pricePerConsole1.setConsoleType((pricePerConsole.getConsoleType() != null) ? pricePerConsole.getConsoleType() : pricePerConsole1.getConsoleType());
            return pricePerConsoleRepository.save(pricePerConsole1);
        }).orElse(null);
    }
}
