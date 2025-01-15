package com.RealStateManagement.spring_boot_microservie_1_property.services;

import com.RealStateManagement.spring_boot_microservie_1_property.models.Property;

import java.util.List;
import java.util.Optional;

public interface PropertyService {
    Property addProperty(Property newProperty);

    void deleteProperty(Long idProperty);

    List<Property> getAllProperty();

    Property getPropertyById(Long idProperty);

    Optional<?> updateProperty(Long idProperty, Property updatedProperty);
}
