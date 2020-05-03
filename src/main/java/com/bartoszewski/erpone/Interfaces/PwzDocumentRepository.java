package com.bartoszewski.erpone.Interfaces;

import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.PwzDocument;

import org.springframework.stereotype.Repository;

@Repository
public interface PwzDocumentRepository extends BaseRepository<PwzDocument, Long>
{

}