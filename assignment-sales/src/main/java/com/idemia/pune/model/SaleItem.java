package com.idemia.pune.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data 
@Builder 
@NoArgsConstructor
@AllArgsConstructor
public class SaleItem {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String itemName;
    
	private BigInteger price;
    
    private double percentDiscount;
}
