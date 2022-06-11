package com.crediservir.store.person.entity;

import com.crediservir.store.documenttype.entity.DocumentType;
import com.crediservir.store.invoice.entity.Invoice;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "person_id")
    private UUID personId;

    @Column(name = "person_document", nullable = false, length = 100)
    private String personDocument;

    @Column(name = "person_name", nullable = false, length = 100)
    private String personName;

    @Column(name = "person_last_name", nullable = false, length = 100)
    private String personLastName;

    @Column(name = "person_email", nullable = false, length = 100)
    private String personEmail;

    @Column(name = "person_phone", nullable = false, length = 100)
    private String personPhone;

    @Column(name = "person_address", nullable = false, length = 100)
    private String personAddress;

    @Column(name = "document_type_id", nullable = false, length = 100)
    private UUID personDocumentTypeId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "document_type_id", insertable = false, updatable = false)
    private DocumentType documentType;

    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private List<Invoice> invoices;

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public String getPersonDocument() {
        return personDocument;
    }

    public void setPersonDocument(String personDocument) {
        this.personDocument = personDocument;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }

    public UUID getPersonDocumentTypeId() {
        return personDocumentTypeId;
    }

    public void setPersonDocumentTypeId(UUID personDocumentTypeId) {
        this.personDocumentTypeId = personDocumentTypeId;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }


}
