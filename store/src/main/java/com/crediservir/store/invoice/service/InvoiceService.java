package com.crediservir.store.invoice.service;

import com.crediservir.store.invoice.entity.Invoice;
import com.crediservir.store.invoice.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvoiceService {

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    private final InvoiceRepository invoiceRepository;

    private List<Invoice> getAll(){
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> findById(UUID invoiceId){
        return invoiceRepository.findById(invoiceId);
    }

    public Optional<Invoice> findInvoiceByPersonId(UUID personId){
        return invoiceRepository.findInvoiceByPersonId(personId);
    }

    public Invoice saveInvoice(Invoice invoice){
       return invoiceRepository.save(invoice);
    }

}
