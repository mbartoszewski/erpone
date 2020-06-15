package com.bartoszewski.erpone.Entity.Documents;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.bartoszewski.erpone.Entity.User;
import com.bartoszewski.erpone.Enum.DocumentTypeEnum;
import com.bartoszewski.erpone.Enum.StatusTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

@Entity
@JsonIgnoreProperties(value = { "documentDetails" }, allowSetters = true)
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @CreationTimestamp
    @Column(name = "CreatedAt", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "UpdatedAt", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "Description")
    private String description;

    @Column(name = "Status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusTypeEnum statusTypeEnum = StatusTypeEnum.open;

    @Column(name = "Type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentTypeEnum documentTypeEnum;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonProperty("documentDetails")
    private List<DocumentDetails> documentDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "User_Id")
    @NonNull
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    PurchaseOrderDetails purchaseOrderDetails;

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

    public PurchaseOrderDetails getPurchaseOrderDetails() {
        return purchaseOrderDetails;
    }

    public void setPurchaseOrderDetails(PurchaseOrderDetails purchaseOrderDetails) {
        if (documentTypeEnum == DocumentTypeEnum.po) {
            this.purchaseOrderDetails = purchaseOrderDetails;
            this.purchaseOrderDetails.setDocument(this);
        }

    }
}