package com.crediservir.store.consoletype.Repository;

import com.crediservir.store.consoletype.entity.ConsoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.UUID;

public interface ConsoleTypeRepository extends JpaRepository<ConsoleType, UUID> {


}
