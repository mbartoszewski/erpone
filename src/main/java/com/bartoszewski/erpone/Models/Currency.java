package com.bartoszewski.erpone.Models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties(value =
{
        "prices", "contractors"
}, allowSetters = true)
public class Currency
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Code", nullable = false)
    private String code;
    @Column(name = "Name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "currency", fetch = FetchType.LAZY)
    @JsonProperty("prices")
    private List<Price> prices;
    @OneToMany(mappedBy = "defaultCurrency", fetch = FetchType.LAZY)
    @JsonProperty("contractors")
    private List<Contractor> contractors;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<Price> getPrices()
    {
        return prices;
    }

    public void setPrices(List<Price> prices)
    {
        this.prices = prices;
    }

    public void addPrices(Price price)
    {
        prices.add(price);
        price.setCurrency(this);
    }

    public void removePrices(Price price)
    {
        prices.remove(price);
        price.setCurrency(null);
    }

    public void addContractor(Contractor contractor)
    {
        this.contractors.add(contractor);
        contractor.setDefaultCurrency(this);
    }

    public void removeContractor(Contractor contractor)
    {
        this.contractors.remove(contractor);
        contractor.setDefaultCurrency(null);
    }

    public List<Contractor> getContractors()
    {
        return contractors;
    }

    public void setContractors(List<Contractor> contractors)
    {
        this.contractors = contractors;
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