package com.crediservir.store.invoice.repository;

import com.crediservir.store.invoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

    List<Invoice> findInvoiceByPersonId(UUID personId);

    Invoice findByInvoiceId(UUID invoiceId);

    @Query(value = "SELECT SUM(rental_price) FROM rental WHERE rental.invoice_id = :invoiceId", nativeQuery = true)
    Float getByInvoiceId(UUID invoiceId);
}
