package com.bartoszewski.erpone.Entity;

import java.util.ArrayList;
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

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@JsonIgnoreProperties(value = { "things" }, allowSetters = true)
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Code", unique = true, nullable = false)
    private String code;

    @Column(name = "Name", unique = true, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unit")
    private List<Thing> things = new ArrayList<>(0);

    public Unit() {
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

    public List<Thing> getThings() {
        return things;
    }

    public void setThings(List<Thing> things) {
        this.things = things;
    }

    public void addThings(Thing thing) {
        things.add(thing);
        thing.setUnit(this);
    }

    public void removeThings(Thing thing) {
        things.remove(thing);
        thing.setUnit(null);
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
        Unit other = (Unit) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Unit [code=" + code + ", name=" + name + "]";
    }

    public Unit(Long id, @NotNull String code, @NotNull String name, List<Thing> things) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.things = things;
    }

}
