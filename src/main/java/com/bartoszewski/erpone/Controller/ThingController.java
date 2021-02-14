package com.bartoszewski.erpone.Controller;

import com.bartoszewski.erpone.Entity.Thing;

import java.time.LocalDate;

import com.bartoszewski.erpone.Entity.Price;
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
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/{thingId}/price")
	public ResponseEntity<Page<Price>> findAllOperationsByThingAndType(Pageable pageable,
			@PathVariable(value = "thingId") Long thing,
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		return priceService.getPriceByThingId(pageable, thing, startDate, endDate);
	}

	@PostMapping("/search")
	public ResponseEntity<Page<Thing>> searchThingByPropertiesWithLike(Pageable pageable,
			@RequestParam(value = "query", required = true) String searchQuery) {
		return thingsService.searchThingByPropertiesWithLike(pageable, searchQuery);
	}

	@PostMapping("/match")
	public ResponseEntity<Page<Thing>> searchThingByPropertiesWithMatchAgainst(Pageable pageable,
			@RequestParam(value = "query", required = true) String searchQuery) {
		return thingsService.searchThingByPropertiesWithMatchAgainst(pageable, searchQuery);
	}
}