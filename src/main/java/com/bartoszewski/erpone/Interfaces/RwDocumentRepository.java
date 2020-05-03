package com.bartoszewski.erpone.Interfaces;

import com.bartoszewski.erpone.Models.Documents.WarehouseDocuments.RwDocument;

import org.springframework.stereotype.Repository;

@Repository
public interface RwDocumentRepository extends BaseRepository<RwDocument, Long>
{

}