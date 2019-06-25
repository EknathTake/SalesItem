package com.idemia.pune.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idemia.pune.model.SaleItem;
import com.idemia.pune.service.ISalesService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/sales")
@AllArgsConstructor
public class SalesController {
	
	private ISalesService salesService;
	
	@PostMapping
	public ResponseEntity<SaleItem> saveThe(@RequestBody SaleItem item){
		return ResponseEntity.ok()
				.body(salesService.save(item)
				.onFailure(ex -> log.warn("An error occured during processing saveThe SalesItem request", ex))
                .onSuccess(voidResponse -> log.info("Successfully processed saveThe SalesItem request {}", item))
                .get());
	}
	
	@GetMapping
	public ResponseEntity<List<SaleItem>> getSalesItems(){
		return ResponseEntity.ok()
				.body(salesService.getSalesDetails()
				.collect(Collectors.toList()));
	}

	@GetMapping("/{itemId}")
	public ResponseEntity<SaleItem> getSalesItem(@PathVariable("itemId") Long itemId){
		return ResponseEntity.ok()
				.body(salesService.getSalesDetail(itemId)
				.onFailure(ex -> log.warn("An error during processing getSalesItem request: {}", ex))
                .onSuccess(response -> log.info("Successfully processed getSalesItem request: {}", response))
                .get());
		
	}
	
	@PutMapping("/{itemId}")
	public ResponseEntity<SaleItem> updateThe(@RequestBody SaleItem itemId){
		return ResponseEntity.ok()
				.body(salesService.updateThe(itemId)
						.onFailure(ex -> log.warn("An error occured during processing deleteThe SalesItem request", ex))
		                .onSuccess(voidResponse -> log.info("Successfully processed deleteThe SalesItem request {}"))
		                .get());
	}
	
	@DeleteMapping("/{itemId}")
	public ResponseEntity<?> deleteThe(@PathVariable("itemId") Long itemId) {
		if(null!= salesService.getSalesDetail(itemId).get())
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.ok()
				.body(salesService.deleteThe(itemId)
				.onFailure(ex -> log.warn("An error occured during processing deleteThe SalesItem request", ex))
                .onSuccess(voidResponse -> log.info("Successfully processed deleteThe SalesItem request {}"))
                .get());
	}

}
