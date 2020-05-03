package com.bartoszewski.erpone.Interfaces;

import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.PwDocument;

import org.springframework.stereotype.Repository;

@Repository
public interface PwDocumentRepository extends BaseRepository<PwDocument, Long>
{

}