package com.tfg.supercomparator.application.ports.out.hipercor;


import com.tfg.supercomparator.domain.hipercor.HipercorProduct;
import org.springframework.data.repository.CrudRepository;

public interface HipercorRepository extends CrudRepository<HipercorProduct, String> {
}