package com.example.assignment.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.assignment.Entity.Load;
import com.example.assignment.Repository.LoadRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class LoadService {

    private final LoadRepository loadRepository;

    @Autowired
    public LoadService(LoadRepository loadRepository) {
        this.loadRepository = loadRepository;
    }

    public Load createLoad(Load load) {
      
        return loadRepository.save(load);
    }

    public List<Load> getFilteredLoads(String shipperId, String truckType, String productType,
                                   Load.Status status, String loadingPoint, String unloadingPoint) {
    Specification<Load> spec = Specification
            .where(LoadSpecification.hasShipperId(shipperId))
            .and(LoadSpecification.hasTruckType(truckType))
            .and(LoadSpecification.hasProductType(productType))
            .and(LoadSpecification.hasStatus(status))
            .and(LoadSpecification.hasLoadingPoint(loadingPoint))
            .and(LoadSpecification.hasUnloadingPoint(unloadingPoint));

    return loadRepository.findAll(spec);
}

    public Load getLoadById(UUID loadId) {
    return loadRepository.findById(loadId)
            .orElseThrow(() -> new NoSuchElementException("Load not found with id: " + loadId));
}

    public Load updateLoad(UUID loadId, Load updatedLoad) {
       loadRepository.findById(loadId)
            .orElseThrow(() -> new NoSuchElementException("Load not found with id: " + loadId));

        updatedLoad.setId(loadId);
  

    return loadRepository.save(updatedLoad);

    }

    public void deleteLoad(UUID loadId) {
        loadRepository.findById(loadId)
            .orElseThrow(() -> new NoSuchElementException("Load not found with id: " + loadId));

        loadRepository.deleteById(loadId);
    }
}
