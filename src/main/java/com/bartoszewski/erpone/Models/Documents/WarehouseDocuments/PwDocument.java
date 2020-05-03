package com.bartoszewski.erpone.Models.Documents.WarehouseDocuments;

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
import com.bartoszewski.erpone.Models.Documents.Documents;
import com.bartoszewski.erpone.Models.Documents.DocumentDetails;

@Entity
public class PwDocument extends Documents
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusTypeEnum statusTypeEnum = StatusTypeEnum.Open;

    @OneToMany(mappedBy = "pwDocument", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @NotNull
    private List<DocumentDetails> documentsDetails = new ArrayList<>(0);

    public PwDocument()
    {
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
        this.documentsDetails = documentsDetails;
        documentsDetails.stream().forEach(dD -> dD.setPwDocument(this));
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