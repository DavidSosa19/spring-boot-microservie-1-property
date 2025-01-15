package com.RealStateManagement.spring_boot_microservie_1_property.repository;

import com.RealStateManagement.spring_boot_microservie_1_property.models.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
