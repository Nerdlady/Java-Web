package com.cardealer.services;

import com.cardealer.entities.Supplier;
import com.cardealer.models.bindingModels.supplier.AddSupplierModel;
import com.cardealer.models.bindingModels.supplier.EditSupplierModel;
import com.cardealer.models.bindingModels.supplier.SupplierModel;
import com.cardealer.models.viewModels.supplier.SupplierView;
import com.cardealer.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;


    @Override
    public List<SupplierView> getAllByImporter(Boolean importer) {
        List<Supplier> suppliers = this.supplierRepository.getAllByImporter(importer);
        List<SupplierView> supplierViews = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        for (Supplier supplier : suppliers) {
            SupplierView supplierView = modelMapper.map(supplier,SupplierView.class);
            supplierViews.add(supplierView);
        }
        return supplierViews;
    }

    @Override
    public List<SupplierView> getAll() {
        List<Supplier> suppliers = this.supplierRepository.findAll();
        List<SupplierView> supplierViews = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();

        for (Supplier supplier : suppliers) {
            SupplierView supplierView = modelMapper.map(supplier,SupplierView.class);
            supplierViews.add(supplierView);
        }
        return supplierViews;
    }

    @Override
    public SupplierModel getByName(String name) {
        Supplier supplier = this.supplierRepository.findFirstByName(name);
        ModelMapper modelMapper = new ModelMapper();
        SupplierModel supplierModel = null;
        if (supplier != null){
            supplierModel = modelMapper.map(supplier,SupplierModel.class);
        }

        return supplierModel;
    }

    @Override
    public void persist(AddSupplierModel supplierModel) {
        ModelMapper modelMapper = new ModelMapper();
        Supplier supplier = modelMapper.map(supplierModel,Supplier.class);
        this.supplierRepository.saveAndFlush(supplier);
    }

    @Override
    public EditSupplierModel getByIdToEdit(Long id) {
        Supplier supplier = this.supplierRepository.findOne(id);
        ModelMapper modelMapper = new ModelMapper();
        EditSupplierModel editSupplierModel = null;
        if (supplier != null){
            editSupplierModel = modelMapper.map(supplier,EditSupplierModel.class);
        }
        return editSupplierModel;
    }

    @Override
    public void update(EditSupplierModel editSupplierModel) {
        Supplier supplier = this.supplierRepository.findOne(editSupplierModel.getId());
        supplier.setImporter(editSupplierModel.getImporter());
        supplier.setName(editSupplierModel.getName());
        this.supplierRepository.saveAndFlush(supplier);
    }

    @Override
    public void delete(EditSupplierModel editSupplierModel) {
        Supplier supplier = this.supplierRepository.findOne(editSupplierModel.getId());
        this.supplierRepository.delete(supplier);
    }


}
