package com.cardealer.services;

import com.cardealer.models.bindingModels.sale.SaleModel;
import com.cardealer.models.viewModels.sale.SaleView;

import java.util.List;

public interface SaleService {
    List<SaleView> getAll();
    SaleView getById(Long id);
    List<SaleView> getAllByDiscount(Float discount);
    List<SaleView> getAllByDiscountGreaterThen(Float discount);
    void persist(SaleModel saleModel);
}
