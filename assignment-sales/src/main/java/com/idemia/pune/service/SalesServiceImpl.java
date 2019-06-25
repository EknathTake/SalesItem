package com.idemia.pune.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.idemia.pune.exception.SalesItemNotFoundException;
import com.idemia.pune.model.SaleItem;
import com.idemia.pune.repository.SalesRepository;

import io.vavr.Function1;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("salesService1")
public class SalesServiceImpl implements ISalesService {
	
	@Autowired
	private SalesRepository salesRepository;

	@Override
	public Try<SaleItem> save(SaleItem item) {
		return Try.of(() -> salesRepository.save(item))
				.onFailure(ex -> log.warn("An error occured during processing saveThe SalesItem request", ex))
                .onSuccess(voidResponse -> log.info("Successfully processed saveThe SalesItem request {}"));
	}

	@Override
	public Try getSalesDetails() {
		return Try.of(() -> salesRepository.findAll())
				.onFailure(ex -> log.warn("An error occured during processing getSalesDetails request", ex))
                .onSuccess(voidResponse -> log.info("Successfully processed getSalesDetails request {}"));
	}

	@Override
	public Try getSalesDetail(Long id) {
		return Try.of(() -> getSalesItemById.apply(id)
				.map(mapper -> mapper)
				.get())
				.onFailure(ex -> log.warn("An error occured during processing getSalesDetails request", ex))
                .onSuccess(voidResponse -> log.info("Successfully processed getSalesDetails request {}"));
	}

	@Override
	public Try updateThe(SaleItem newSaleItem) {
		return Try.of(() -> {
			return mapSalesItem(newSaleItem)
					.filter(item -> null != item)
					.map(saleItem -> salesRepository.save(saleItem))
					.get();
			})
				.recoverWith(SalesItemNotFoundException.class, Try.success(null))
				.recoverWith(NoSuchElementException.class, Try.success(null))
				.onFailure(ex -> log.warn("An error occured during processing updateThe SalesItem request", ex))
				.onSuccess(response -> log.info("Successfully processed updateThe SalesItem request {}"));
	}

	private Optional<SaleItem> mapSalesItem(SaleItem newSaleItem) {
		return Optional.ofNullable(getSalesItemById.apply(newSaleItem.getId()).map(saleItem -> SaleItem.builder()
				.id((newSaleItem.getId() == 0 ) ? saleItem.getId() : newSaleItem.getId())
				.itemName(StringUtils.isEmpty(newSaleItem.getItemName()) ? newSaleItem.getItemName() : saleItem.getItemName())
				.percentDiscount((newSaleItem.getPercentDiscount() == 0.0)? newSaleItem.getPercentDiscount() : saleItem.getPercentDiscount())
				.price((newSaleItem.getPrice() == null) ? newSaleItem.getPrice() : saleItem.getPrice())
				.build())
				.orElse(null));
	}

	@Override
	public Try deleteThe(Long itemId)  {
		return Try.of(() -> deleteById(itemId))
				.onFailure(ex -> log.warn("An error occured during processing deleteThe SalesItem request", ex))
				.onSuccess(response -> log.info("Successfully processed deleteThe SalesItem request {}"));
	}

	private SaleItem deleteById(Long itemId) {
		salesRepository.deleteById(itemId);
		//this is stupid code but, for now that's okey,
		return null;
	}
	
	Function1<Long, Optional<SaleItem>> getSalesItemById = (id) -> Try.of(()-> salesRepository
														.findById(id))
														.map(item -> item)
														.get();

}
