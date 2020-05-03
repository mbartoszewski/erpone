package com.bartoszewski.erpone.Models.Documents;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.bartoszewski.erpone.Models.Price;
import com.bartoszewski.erpone.Models.Thing;
import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.PwDocument;
import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.PwzDocument;
import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.RwDocument;
import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.WzDocument;
import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.WzzDocument;
import com.bartoszewski.erpone.Models.Documents.ProductionDocuments.ZpDocument;
import com.bartoszewski.erpone.Models.Documents.PurchaseDocuments.PoDocument;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties(value =
{
        "wzDocument", "pwzDocument", "pwDocument", "rwDocument", "wzzDocument", "poDocument"
}, allowSetters = true)
public class DocumentDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	@Column(name = "Quantity", nullable = false)
	private Double quantity;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "Thing_Id")
	private Thing thing;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Price_Id")
	private Price price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty("wzDocument")
	@JoinColumn(name = "Wz_Document_Id")
	private WzDocument wzDocument;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty("wzzDocument")
	@JoinColumn(name = "Wzz_Document_Id")
	private WzzDocument wzzDocument;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty("pwzDocument")
	@JoinColumn(name = "Pwz_Document_Id")
	private PwzDocument pwzDocument;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty("rwDocument")
	@JoinColumn(name = "Rw_Document_Id")
	private RwDocument rwDocument;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty("pwDocument")
	@JoinColumn(name = "Pw_Document_Id")
	private PwDocument pwDocument;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty("poDocument")
	@JoinColumn(name = "Po_Document_Id")
	private PoDocument poDocument;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonProperty("zpDocument")
	@JoinColumn(name = "Zp_Document_Id")
	private ZpDocument zpDocument;

	public DocumentDetails()
	{
	}

	public Double getQuantity()
	{
		return quantity;
	}

	public void setQuantity(Double quantity)
	{
		this.quantity = quantity;
	}

	public Thing getThing()
	{
		return thing;
	}

	public void setThing(Thing thing)
	{
		this.thing = thing;
	}

	public WzDocument getWzDocument()
	{
		return wzDocument;
	}

	public void setWzDocument(WzDocument wzDocument)
	{
		this.wzDocument = wzDocument;
	}

	public Price getPrice()
	{
		return price;
	}

	public void setPrice(Price price)
	{
		this.price = price;
	}

	public WzzDocument getWzzDocument()
	{
		return wzzDocument;
	}

	public void setWzzDocument(WzzDocument wzzDocument)
	{
		this.wzzDocument = wzzDocument;
	}

	public PwzDocument getPwzDocument()
	{
		return pwzDocument;
	}

	public void setPwzDocument(PwzDocument pwzDocument)
	{
		this.pwzDocument = pwzDocument;
	}

	public RwDocument getRwDocument()
	{
		return rwDocument;
	}

	public void setRwDocument(RwDocument rwDocument)
	{
		this.rwDocument = rwDocument;
	}

	public PwDocument getPwDocument()
	{
		return pwDocument;
	}

	public void setPwDocument(PwDocument pwDocument)
	{
		this.pwDocument = pwDocument;
	}

	public PoDocument getPoDocument()
	{
		return poDocument;
	}

	public void setPoDocument(PoDocument poDocument)
	{
		this.poDocument = poDocument;
	}

	public ZpDocument getZpDocument()
	{
		return zpDocument;
	}

	public void setZpDocument(ZpDocument zpDocument)
	{
		this.zpDocument = zpDocument;
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