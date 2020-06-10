package com.bartoszewski.erpone.Entity;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.lang.NonNull;

@Entity
@JsonIgnoreProperties(value =
{
        "foreignCodes", "contactDetails", "address", "defaultCurrency"
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
    @NonNull
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nip == null) ? 0 : nip.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contractor other = (Contractor) obj;
        if (nip == null) {
            if (other.nip != null)
                return false;
        } else if (!nip.equals(other.nip))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Contractor [name=" + name + ", nip=" + nip + "]";
    }

    
}
