package com.crediservir.store.person.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID personId;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String personDocument;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String personName;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String personLastName;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String personEmail;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String personPhone;

    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String personAddress;


    private UUID personDocumentTypeId;

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
}
