package com.cardealer.services;

import com.cardealer.models.bindingModels.supplier.AddSupplierModel;
import com.cardealer.models.bindingModels.supplier.EditSupplierModel;
import com.cardealer.models.bindingModels.supplier.SupplierModel;
import com.cardealer.models.viewModels.supplier.SupplierView;

import java.util.List;

public interface SupplierService {
    List<SupplierView> getAllByImporter(Boolean importer);
    List<SupplierView> getAll();
    SupplierModel getByName(String name);
    void persist(AddSupplierModel supplierModel);
    EditSupplierModel getByIdToEdit(Long id);
    void update(EditSupplierModel editSupplierModel);
    void delete(EditSupplierModel editSupplierModel);
}
