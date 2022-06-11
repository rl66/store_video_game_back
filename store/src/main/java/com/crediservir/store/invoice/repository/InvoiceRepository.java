package com.crediservir.store.invoice.repository;

import com.crediservir.store.invoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

    Optional<Invoice> findInvoiceByPersonId(UUID personId);

    Invoice findByInvoiceId(UUID invoiceId);

}
