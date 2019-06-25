package com.idemia.pune.service;

import com.idemia.pune.model.SaleItem;

import io.vavr.control.Try;
import javassist.NotFoundException;

public interface ISalesService {

	Try<SaleItem>  save(SaleItem item);

	Try<SaleItem> getSalesDetails();

	Try<SaleItem> getSalesDetail(Long itemId);

	Try<SaleItem> updateThe(SaleItem itemId);

	Try<SaleItem> deleteThe(Long itemId);

}
