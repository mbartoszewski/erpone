package com.bartoszewski.erpone.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = { "prices", "contractors" }, allowSetters = true)
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @NotNull
    @Column(name = "Code")
    private String code;
    @NotNull
    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy = "currency", fetch = FetchType.LAZY)
    private List<Price> prices;
    @OneToMany(mappedBy = "defaultCurrency", fetch = FetchType.LAZY)
    private List<Contractor> contractors;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public void addPrices(Price price) {
        prices.add(price);
        price.setCurrency(this);
    }

    public void removePrices(Price price) {
        prices.remove(price);
        price.setCurrency(null);
    }

    public void addContractor(Contractor contractor) {
        this.contractors.add(contractor);
        contractor.setDefaultCurrency(this);
    }

    public void removeContractor(Contractor contractor) {
        this.contractors.remove(contractor);
        contractor.setDefaultCurrency(null);
    }

    public List<Contractor> getContractors() {
        return contractors;
    }

    public void setContractors(List<Contractor> contractors) {
        this.contractors = contractors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        Currency other = (Currency) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Currency [code=" + code + ", name=" + name + "]";
    }

}