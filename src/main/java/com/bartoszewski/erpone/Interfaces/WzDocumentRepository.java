package com.bartoszewski.erpone.Interfaces;

import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.WzDocument;

import org.springframework.stereotype.Repository;

@Repository
public interface WzDocumentRepository extends BaseRepository<WzDocument, Long>
{

}