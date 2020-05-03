package com.bartoszewski.erpone.Models;

import java.util.ArrayList;
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

import com.bartoszewski.erpone.Models.Documents.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties(value =
{
        "foreignCodes", "documentsDetails"
}, allowSetters = true)
public class Thing
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Code", nullable = false)
    private String code;
    @Column(name = "Name", updatable = true, nullable = false)
    private String name;
    @Column(name = "Quantity", updatable = true)
    private Double quantity = 0.0;
    @Column(name = "Active", updatable = true)
    private int active = 1;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "thing", fetch = FetchType.LAZY)
    @JoinColumn(name = "Price_Id")
    @JsonProperty("price")
    private Price price;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "thing", fetch = FetchType.LAZY)
    @JsonProperty("foreignCodes")
    private List<ForeignCode> foreignCodes = new ArrayList<>(0);

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "thing", fetch = FetchType.LAZY)
    @JsonProperty("documentsDetails")
    private List<DocumentDetails> documentsDetails = new ArrayList<>(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Warehouse_Id")
    @JsonProperty("warehouse")
    @NotNull
    private Warehouse warehouse;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Unit_Id")
    @JsonProperty("unit")
    @NotNull
    private Unit unit;

    public Thing()
    {
    }

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

    public Double getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Double quantity)
    {
        this.quantity = quantity;
    }

    public int getActive()
    {
        return active;
    }

    public void setActive(int active)
    {
        this.active = active;
    }

    public List<ForeignCode> getForeignCodes()
    {
        return foreignCodes;
    }

    public void setForeignCodes(List<ForeignCode> foreignCodes)
    {
        this.foreignCodes = foreignCodes;
    }

    public void addForeignCodes(ForeignCode foreignCode)
    {
        foreignCodes.add(foreignCode);
        foreignCode.setThing(this);
    }

    public void removeForeignCodes(ForeignCode foreignCode)
    {
        foreignCodes.remove(foreignCode);
        foreignCode.setThing(null);
    }

    public Warehouse getWarehouse()
    {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse)
    {
        this.warehouse = warehouse;
    }

    public Unit getUnit()
    {
        return unit;
    }

    public void setUnit(Unit unit)
    {
        this.unit = unit;
    }

    public void addDocumentsDetails(DocumentDetails documentsDetail)
    {
        documentsDetails.add(documentsDetail);
        documentsDetail.setThing(this);
    }

    public void removeDocumentsDetails(DocumentDetails documentsDetail)
    {
        documentsDetails.remove(documentsDetail);
        documentsDetail.setThing(null);
    }

    public List<DocumentDetails> getDocumentsDetails()
    {
        return documentsDetails;
    }

    public void setDocumentsDetails(List<DocumentDetails> documentsDetails)
    {
        this.documentsDetails = documentsDetails;
    }

    public Price getPrice()
    {
        return price;
    }

    public void setPrice(Price price)
    {
        this.price = price;
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
