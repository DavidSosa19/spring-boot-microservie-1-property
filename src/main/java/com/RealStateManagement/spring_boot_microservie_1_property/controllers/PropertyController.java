package com.RealStateManagement.spring_boot_microservie_1_property.controllers;

import com.RealStateManagement.spring_boot_microservie_1_property.models.Property;
import com.RealStateManagement.spring_boot_microservie_1_property.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    @Qualifier("usingRepository")
    private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<?> addProperty(@RequestBody Property newProperty){
        return new ResponseEntity<>(propertyService.addProperty(newProperty), HttpStatus.CREATED);
    }

    @DeleteMapping("/{idProperty}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long idProperty){
        propertyService.deleteProperty(idProperty);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllProperty(){
        return new ResponseEntity<>(propertyService.getAllProperty(),HttpStatus.OK);
    }
}
