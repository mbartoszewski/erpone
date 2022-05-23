package com.bartoszewski.erpone.Entity;

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

import com.bartoszewski.erpone.Entity.Documents.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = { "foreignCodes", "documentsDetails", "prices", "recipe",
        "warehouse" }, allowSetters = true)
public class Thing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Code", unique = true, updatable = false, nullable = false)
    private String code;

    @Column(name = "Name", updatable = true, nullable = false)
    private String name;
    @Column(name = "Quantity", updatable = true)
    private Double quantity = 0.0;
    @Column(name = "Active", updatable = true)
    private int active = 1;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "thing", fetch = FetchType.LAZY)
    private List<Price> prices;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "thing", fetch = FetchType.LAZY)
    private List<ForeignCode> foreignCodes = new ArrayList<>(0);

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "thing", fetch = FetchType.LAZY)
    private List<DocumentDetails> documentsDetails = new ArrayList<>(0);

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "thing", fetch = FetchType.LAZY)
    private List<Recipe> recipe = new ArrayList<>(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Warehouse_Id")

    private Warehouse warehouse;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Group_Id")

    private ThingGroup thingGroup;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Family_Id")

    private ThingFamily thingFamily;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Unit_Id")

    private Unit unit;

    public Thing() {
    }

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

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public List<ForeignCode> getForeignCodes() {
        return foreignCodes;
    }

    public void setForeignCodes(List<ForeignCode> foreignCodes) {
        this.foreignCodes = foreignCodes;
    }

    public void addForeignCodes(ForeignCode foreignCode) {
        foreignCodes.add(foreignCode);
        foreignCode.setThing(this);
    }

    public void removeForeignCodes(ForeignCode foreignCode) {
        foreignCodes.remove(foreignCode);
        foreignCode.setThing(null);
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<DocumentDetails> getDocumentsDetails() {
        return documentsDetails;
    }

    public void setDocumentsDetails(List<DocumentDetails> documentsDetails) {
        this.documentsDetails = documentsDetails;
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
        Thing other = (Thing) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Thing [code=" + code + ", name=" + name + ", quantity=" + quantity + ", unit=" + unit + "]";
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public List<Recipe> getRecipe() {
        return recipe;
    }

    public void setRecipe(List<Recipe> recipe) {
        this.recipe = recipe;
    }

    public void addPrice(Price price) {
        prices.add(price);
        price.setThing(this);
    }

    public void removePrice(Price price) {
        prices.remove(price);
        price.setThing(null);
    }

    public ThingGroup getThingGroup() {
        return thingGroup;
    }

    public void setThingGroup(ThingGroup thingGroup) {
        this.thingGroup = thingGroup;
    }

    public ThingFamily getThingFamily() {
        return thingFamily;
    }

    public void setThingFamily(ThingFamily thingFamily) {
        this.thingFamily = thingFamily;
    }

}