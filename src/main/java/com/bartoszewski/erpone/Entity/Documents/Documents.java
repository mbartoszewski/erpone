package com.bartoszewski.erpone.Entity.Documents;

import java.time.LocalDate;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.bartoszewski.erpone.Entity.Contractor;
import com.bartoszewski.erpone.Entity.User;
import com.bartoszewski.erpone.Enum.DocumentTypeEnum;
import com.bartoszewski.erpone.Enum.StatusTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@JsonIgnoreProperties(value = {}, allowSetters = true)
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Document_number")
    private String docNumber;

    @NotNull
    @CreationTimestamp
    @Column(name = "Created_At")
    private LocalDateTime createdAt;
    @Column(name = "Target_Date_Time")
    private LocalDateTime targetDateTime;
    @NotNull
    @UpdateTimestamp
    @Column(name = "Updated_At")
    private LocalDateTime updatedAt;

    @Column(name = "Description")
    private String description;

    @NotNull
    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    private StatusTypeEnum statusTypeEnum = StatusTypeEnum.open;

    @NotNull
    @Column(name = "Type")
    @Enumerated(EnumType.STRING)
    private DocumentTypeEnum documentTypeEnum;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @NotNull
    private List<DocumentDetails> documentDetails = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Contractor_Id")
    @NotNull
    private Contractor contractor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id")
    @NotNull
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    OrderDocumentDetails orderDocumentDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    ProductionOrderDocumentDetails productionOrderDocumentDetails;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Documents> relatedDocuments;

    public Documents() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusTypeEnum getStatusTypeEnum() {
        return statusTypeEnum;
    }

    public void setStatusTypeEnum(StatusTypeEnum statusTypeEnum) {
        this.statusTypeEnum = statusTypeEnum;
    }

    public DocumentTypeEnum getDocumentTypeEnum() {
        return documentTypeEnum;
    }

    public void setDocumentTypeEnum(DocumentTypeEnum documentTypeEnum) {
        this.documentTypeEnum = documentTypeEnum;
    }

    public List<DocumentDetails> getDocumentDetails() {
        return documentDetails;
    }

    public void setDocumentDetails(List<DocumentDetails> documentDetails) {
        this.documentDetails = documentDetails;
        documentDetails.stream().forEach(dD -> dD.setDocument(this));
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderDocumentDetails getOrderDocumentDetails() {
        return orderDocumentDetails;
    }

    public void setOrderDocumentDetails(OrderDocumentDetails orderDocumentDetails) {
        switch (documentTypeEnum) {
            case pw:
                break;
            case pz:
                break;
            case rw:
                break;
            case wz:
                break;
            case wzz:
                break;
            case zm:
                this.orderDocumentDetails = orderDocumentDetails;
                this.orderDocumentDetails.setDocument(this);
                break;
            case zp:
                break;
            default:
                break;

        }
    }

    public ProductionOrderDocumentDetails getProductionDocumentDetails() {
        return productionOrderDocumentDetails;
    }

    public void setProductionDocumentDetails(ProductionOrderDocumentDetails productionOrderDocumentDetails) {
        this.productionOrderDocumentDetails = productionOrderDocumentDetails;
    }

    public List<Documents> getRelatedDocuments() {
        return relatedDocuments;
    }

    public void setRelatedDocuments(List<Documents> relatedDocuments) {
        this.relatedDocuments = relatedDocuments;
    }

    public void addRelatedDocuments(Documents document) {
        this.relatedDocuments.add(document);
        document.setRelatedDocuments(relatedDocuments);
    }

    public void removeRelatedDocuments(Documents document) {
        this.relatedDocuments.remove(document);
        document.setRelatedDocuments(null);
    }

    public LocalDateTime getTargetDateTime() {
        return targetDateTime;
    }

    public void setTargetDateTime(LocalDateTime targetDateTime) {
        this.targetDateTime = targetDateTime;
    }

    public ProductionOrderDocumentDetails getProductionOrderDocumentDetails() {
        return productionOrderDocumentDetails;
    }

    public void setProductionOrderDocumentDetails(ProductionOrderDocumentDetails productionOrderDocumentDetails) {
        this.productionOrderDocumentDetails = productionOrderDocumentDetails;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(Long countByYear) {
        String docQuery = this.getDocumentTypeEnum().toString().toUpperCase() + "/" + countByYear.toString() + "/"
                + LocalDate.now().getYear();
        this.docNumber = docQuery;
    }
}