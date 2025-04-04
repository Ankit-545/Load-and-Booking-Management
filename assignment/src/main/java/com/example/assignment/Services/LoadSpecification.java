package com.example.assignment.Services;



import org.springframework.data.jpa.domain.Specification;

import com.example.assignment.Entity.Load;

public class LoadSpecification {

    public static Specification<Load> hasShipperId(String shipperId) {
        return (root, query, cb) -> shipperId == null ? null : cb.equal(root.get("shipperId"), shipperId);
    }

    public static Specification<Load> hasTruckType(String truckType) {
        return (root, query, cb) -> truckType == null ? null : cb.equal(root.get("truckType"), truckType);
    }

    public static Specification<Load> hasStatus(Load.Status status) {
        return (root, query, cb) -> status == null ? null : cb.equal(root.get("status"), status);
    }

    public static Specification<Load> hasProductType(String productType) {
        return (root, query, cb) -> productType == null ? null : cb.equal(root.get("productType"), productType);
    }

    public static Specification<Load> hasLoadingPoint(String loadingPoint) {
        return (root, query, cb) -> loadingPoint == null ? null :
            cb.equal(root.get("facility").get("loadingPoint"), loadingPoint);
    }

    public static Specification<Load> hasUnloadingPoint(String unloadingPoint) {
        return (root, query, cb) -> unloadingPoint == null ? null :
            cb.equal(root.get("facility").get("unloadingPoint"), unloadingPoint);
    }
}
