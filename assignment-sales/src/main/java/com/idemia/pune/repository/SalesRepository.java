package com.idemia.pune.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.idemia.pune.model.SaleItem;

@Repository
public interface SalesRepository extends CrudRepository<SaleItem, Long>{

}
