package com.bartoszewski.erpone.Models;

import java.util.ArrayList;
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

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@JsonIgnoreProperties(value =
{
        "things"
}, allowSetters = true)
public class Unit
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Column(name = "Code", nullable = false, unique = true)
    private String code;
    @Column(name = "Name", nullable = false, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unit")
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
        thing.setUnit(this);
    }

    public void removeThings(Thing thing)
    {
        things.remove(thing);
        thing.setUnit(null);
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
