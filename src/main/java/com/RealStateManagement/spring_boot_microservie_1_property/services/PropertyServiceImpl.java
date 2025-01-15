package com.RealStateManagement.spring_boot_microservie_1_property.services;

import com.RealStateManagement.spring_boot_microservie_1_property.models.Property;
import com.RealStateManagement.spring_boot_microservie_1_property.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service("usingRepository")
public class PropertyServiceImpl implements PropertyService{

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Property addProperty(Property newProperty){
        newProperty.setCreatedAt(LocalDateTime.now());
        newProperty.setModifiedAt(newProperty.getCreatedAt());
        return propertyRepository.save(newProperty);
    }

    @Override
    public void deleteProperty(Long idProperty){
        if(propertyRepository.existsById(idProperty)){
            propertyRepository.deleteById(idProperty);
        }else{
            throw new IllegalArgumentException("Property with id "+idProperty+" does not exits.");
        }
    }

    @Override
    public List<Property> getAllProperty(){
        return propertyRepository.findAll();
    }

    @Override
    public Property getPropertyById(Long idProperty){
        return propertyRepository.findById(idProperty).orElseThrow(()-> new IllegalArgumentException("Property with id "+idProperty+" does not exits."));
    }

    @Override
    public Optional<Property> updateProperty(Long idProperty, Property updatedProperty){
        return propertyRepository.findById(idProperty).map(currentProperty ->{
            currentProperty.setProperty_photo(updatedProperty.getProperty_photo());
            currentProperty.setName(updatedProperty.getName());
            currentProperty.setAddress(updatedProperty.getAddress());
            currentProperty.setModifiedAt(LocalDateTime.now());
            return propertyRepository.save(currentProperty);
        });
    }
}
