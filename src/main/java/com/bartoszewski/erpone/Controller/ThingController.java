package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.Thing;
import com.bartoszewski.erpone.Entity.Documents.PriceWithDocumentTypeProjection;
import com.bartoszewski.erpone.Entity.Projections.AllThingsWarehouse;
import com.bartoszewski.erpone.Entity.Projections.SearchThingsByProperties;
import com.bartoszewski.erpone.Entity.Projections.ThingsListToLoad;
import com.bartoszewski.erpone.Entity.Projections.ThingsValueByProperties;

import java.time.LocalDate;
import java.util.List;

import com.bartoszewski.erpone.Service.BaseService;
import com.bartoszewski.erpone.Service.PriceService;
import com.bartoszewski.erpone.Service.ThingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "/things" })
public class ThingController extends BaseController<Thing, Long> {
	ThingService thingsService;
	PriceService priceService;

	@Autowired
	public ThingController(BaseService<Thing, Long> service, ThingService thingsService, PriceService priceService) {
		super(service);
		this.thingsService = thingsService;
		this.priceService = priceService;
	}

	@GetMapping("/all")
	public ResponseEntity<Page<AllThingsWarehouse>> getAllThingsWarehouse(Pageable pageable) {
		return thingsService.getAllThingsWarehouse(pageable);
	}

	@GetMapping("/list")
	public ResponseEntity<Page<ThingsListToLoad>> getThingsListToLoad(Pageable pageable) {
		return thingsService.getThingsListToLoad(pageable);
	}

	@GetMapping("/{thingId}/price")
	public ResponseEntity<Page<PriceWithDocumentTypeProjection>> findAllOperationsByThingAndType(Pageable pageable,
			@PathVariable(value = "thingId") Long thing,
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		return priceService.getPriceWithDocumentType(pageable, thing, startDate, endDate);
	}

	@GetMapping("/search")
	public ResponseEntity<Page<SearchThingsByProperties>> searchThingByPropertiesWithLike(Pageable pageable,
			@RequestParam(value = "query", required = true) String searchQuery) {
		return thingsService.searchThingByPropertiesWithLike(pageable, searchQuery);
	}

	@GetMapping("/match")
	public ResponseEntity<Page<SearchThingsByProperties>> searchThingByPropertiesWithMatchAgainst(Pageable pageable,
			@RequestParam(value = "query", required = true) String searchQuery) {
		return thingsService.searchThingByPropertiesWithMatchAgainst(pageable, searchQuery);
	}

	@GetMapping("/value")
	public ResponseEntity<Page<ThingsValueByProperties>> getThingsValueByProperties(Pageable pageable,
			@RequestParam(value = "groupId", required = false) List<Long> groupId,
			@RequestParam(value = "familyId", required = false) List<Long> familyId,
			@RequestParam(value = "thingsId", required = false) List<Long> thingsId,
			@RequestParam(value = "contractorsId", required = false) List<Long> contractorsId,
			@RequestParam(value = "dateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
			@RequestParam(value = "dateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {
		return thingsService.getThingsValueByProperties(pageable, groupId, familyId, thingsId, contractorsId, dateFrom,
				dateTo);
	}
}