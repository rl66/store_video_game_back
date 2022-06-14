package com.crediservir.store.invoice.service;

import com.crediservir.store.invoice.entity.Invoice;
import com.crediservir.store.invoice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvoiceService {


    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Optional<Invoice> findById(UUID invoiceId) {
        Invoice invoice = invoiceRepository.findByInvoiceId(invoiceId);
        invoice.setInvoiceTotal(invoiceRepository.getByInvoiceId(invoiceId));
        invoiceRepository.save(invoice);
        return invoiceRepository.findById(invoiceId);
    }


    public List<Invoice> findInvoiceByPersonId(UUID personId) {
        return invoiceRepository.findInvoiceByPersonId(personId);
    }

    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(UUID invoiceId, Invoice invoice) {
        return invoiceRepository.findById(invoiceId).map(invoice1 -> {
            invoice1.setPersonId((invoice.getPersonId() != null) ? invoice.getPersonId() : invoice1.getPersonId());
            return invoiceRepository.save(invoice1);
        }).orElse(null);
    }


}
