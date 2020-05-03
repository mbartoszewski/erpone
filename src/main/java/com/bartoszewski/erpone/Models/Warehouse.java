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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Warehouses")
@JsonIgnoreProperties(value =
{
        "things", "wzDocument"
}, allowSetters = true)
public class Warehouse
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Code", nullable = false, updatable = true, unique = true)
    private String code;
    @Column(name = "Name", nullable = false, updatable = true, unique = true)
    private String name;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonProperty("things")
    private List<Thing> things = new ArrayList<>(0);

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

    public List<Thing> getThings()
    {
        return things;
    }

    public void setThings(List<Thing> things)
    {
        this.things = things;
    }

    public void addThings(Thing thing)
    {
        things.add(thing);
        thing.setWarehouse(this);
    }

    public void removeThings(Thing thing)
    {
        things.remove(thing);
        thing.setWarehouse(null);
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