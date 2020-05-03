package com.bartoszewski.erpone.Interfaces;

import com.bartoszewski.erpone.Models.Documents.PurchaseDocuments.PoDocument;

import org.springframework.stereotype.Repository;

@Repository
public interface PoDocumentRepository extends BaseRepository<PoDocument, Long>
{

}