package com.bartoszewski.erpone.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = { "things" })
public class ThingFamily {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	String name;

	@OneToMany(mappedBy = "thingGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	List<Thing> things;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void addThing(Thing thing) {
		this.things.add(thing);
		thing.setThingFamily(this);
	}

	public void removeThing(Thing thing) {
		this.things.remove(thing);
		thing.setThingFamily(null);
	}
}
