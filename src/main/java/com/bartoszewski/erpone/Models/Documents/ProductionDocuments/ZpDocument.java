package com.bartoszewski.erpone.Models.Documents.ProductionDocuments;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.bartoszewski.erpone.Enums.StatusTypeEnum;
import com.bartoszewski.erpone.Models.Documents.DocumentDetails;
import com.bartoszewski.erpone.Models.Documents.Documents;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ZpDocument extends Documents
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Zp_Id")
	private Long zpId;
	@Column(name = "Target_Date_Time", nullable = false, updatable = true)
	private LocalDateTime targetDateTime;
	@Enumerated(EnumType.STRING)
	private StatusTypeEnum statusTypeEnum = StatusTypeEnum.Open;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "zpDocument", fetch = FetchType.LAZY)
	@JsonProperty("documentsDetails")
	@NotNull
	private List<DocumentDetails> documentsDetails = new ArrayList<>(0);

	public LocalDateTime getTargetDateTime()
	{
		return targetDateTime;
	}

	public void setTargetDateTime(LocalDateTime targetDateTime)
	{
		this.targetDateTime = targetDateTime;
	}

	public StatusTypeEnum getStatusTypeEnum()
	{
		return statusTypeEnum;
	}

	public void setStatusTypeEnum(StatusTypeEnum statusTypeEnum)
	{
		this.statusTypeEnum = statusTypeEnum;
	}

	public List<DocumentDetails> getDocumentsDetails()
	{
		return documentsDetails;
	}

	public void setDocumentsDetails(List<DocumentDetails> documentsDetails)
	{
		documentsDetails.stream().forEach(dD -> dD.setZpDocument(this));
		this.documentsDetails = documentsDetails;
	}

	public Long getZpId()
	{
		return zpId;
	}

	public void setZpId(Long poId)
	{
		this.zpId = poId;
	}
}