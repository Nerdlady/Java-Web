package com.cardealer.services;

import com.cardealer.models.bindingModels.customer.AddCustomerModel;
import com.cardealer.models.bindingModels.customer.EditCustomerModel;
import com.cardealer.models.bindingModels.customer.RelatedCustomerModel;
import com.cardealer.models.viewModels.customer.CustomerDetailsView;
import com.cardealer.models.viewModels.customer.CustomerDriverView;
import com.cardealer.models.viewModels.customer.CustomerNameView;
import com.cardealer.models.viewModels.customer.CustomerView;

import java.util.List;

public interface CustomerService {
    void persist(AddCustomerModel addCustomerModel);
    List<CustomerView> getAllOrderByBirthDate(String type);
    CustomerDetailsView getById(Long id);
    EditCustomerModel getByIdToEdit(Long id);
    void update(EditCustomerModel customerModel);
    List<CustomerNameView> getAll();
    RelatedCustomerModel getByName(String name);
    CustomerDriverView getDriverById(Long id);
}
