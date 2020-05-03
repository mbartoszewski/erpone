package com.bartoszewski.erpone.Models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.bartoszewski.erpone.Models.Documents.PurchaseDocuments.PoDocument;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties(value =
{
        "foreignCodes", "poDocuments"
}, allowSetters = true)
public class Contractor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Name", unique = false, nullable = false)
    private String name;
    @Column(name = "NIP", unique = true, nullable = false)
    private String nip;
    @Column(name = "Regon", unique = true)
    private String regon;

    @ManyToOne
    @JoinColumn(name = "Currency_Id")
    @JsonProperty("defaultCurrency")
    @NotNull
    private Currency defaultCurrency;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Contact_Details_Id")
    @JsonProperty("contactDetails")
    private ContactDetail contactDetails;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "Address_Id")
    @JsonProperty("address")
    private Address address;

    @OneToMany(mappedBy = "contractor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty("foreignCodes")
    private List<ForeignCode> foreignCodes;
    @OneToMany(mappedBy = "contractor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty("poDocuments")
    private List<PoDocument> poDocuments;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNip()
    {
        return nip;
    }

    public void setNip(String nip)
    {
        this.nip = nip;
    }

    public String getRegon()
    {
        return regon;
    }

    public void setRegon(String regon)
    {
        this.regon = regon;
    }

    public Currency getDefaultCurrency()
    {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency)
    {
        this.defaultCurrency = defaultCurrency;
    }

    public ContactDetail getContactDetails()
    {
        return contactDetails;
    }

    public void setContactDetails(ContactDetail contactDetails)
    {
        this.contactDetails = contactDetails;
        // contactDetails.setContractor(this);
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
        // address.setContractor(this);
    }

    public List<ForeignCode> getForeignCodes()
    {
        return foreignCodes;
    }

    public void setForeignCodes(List<ForeignCode> foreignCodes)
    {
        this.foreignCodes = foreignCodes;
    }

    public List<PoDocument> getPoDocuments()
    {
        return poDocuments;
    }

    public void setPoDocuments(List<PoDocument> poDocuments)
    {
        this.poDocuments = poDocuments;
    }

    public void addPoDocument(PoDocument poDocument)
    {
        poDocuments.add(poDocument);
        poDocument.setContractor(this);
    }

    public void removePoDocument(PoDocument poDocument)
    {
        poDocuments.remove(poDocument);
        poDocument.setContractor(null);
    }

    public void addForeignCode(ForeignCode foreignCode)
    {
        foreignCodes.add(foreignCode);
        foreignCode.setContractor(this);
    }

    public void removeForeignCode(ForeignCode foreignCode)
    {
        foreignCodes.remove(foreignCode);
        foreignCode.setContractor(null);
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }
}
