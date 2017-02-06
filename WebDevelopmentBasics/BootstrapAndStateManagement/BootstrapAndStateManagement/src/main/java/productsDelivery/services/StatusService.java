package productsDelivery.services;


import productsDelivery.models.Status;
import productsDelivery.repository.StatusRepository;

public class StatusService {
    private StatusRepository statusRepository;

    public StatusService() {
        this.statusRepository = new StatusRepository();
    }

    public Status getStatusByName(String name){
        return  this.statusRepository.getStatusByName(name);
    }
}
