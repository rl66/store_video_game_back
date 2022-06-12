package com.crediservir.store.invoice.service;

import com.crediservir.store.invoice.entity.Invoice;
import com.crediservir.store.invoice.repository.InvoiceRepository;
import com.crediservir.store.person.entity.Person;
import com.crediservir.store.person.repository.PersonRepository;
import com.crediservir.store.rental.entity.Rental;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvoiceService {



    private final InvoiceRepository invoiceRepository;

    private final PersonRepository personRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, PersonRepository personRepository) {
        this.invoiceRepository = invoiceRepository;
        this.personRepository = personRepository;
    }

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

    public Invoice updateInvoice(UUID invoiceId, Invoice invoice){
        return invoiceRepository.findById(invoiceId).map(invoice1 -> {
            invoice1.setPersonId((invoice.getPersonId()!=null)?invoice.getPersonId():invoice1.getPersonId());
            return invoiceRepository.save(invoice1);
        }).orElse(null);
    }


}
