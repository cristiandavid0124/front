package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import com.example.demo.model.Property;
import com.example.demo.model.User;
import com.example.demo.service.PropertyService;
import com.example.demo.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/properties")
@Validated
@CrossOrigin(origins = "*")
public class PropertyController {

    private final PropertyService propertyService;
    private final UserService userService;

    @Autowired
    public PropertyController(PropertyService propertyService, UserService userService) {
        this.propertyService = propertyService;
        this.userService = userService;
    }

    // Create a new property
    @PostMapping
public ResponseEntity<Property> createProperty(@RequestParam String username, @Valid @RequestBody Property property) {
    User user = userService.findByUsername(username);
    if (user == null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    property.setUser(user); // Asocia la propiedad al usuario
    
    try {
        Property createdProperty = propertyService.createProperty(property, user);
        return new ResponseEntity<>(createdProperty, HttpStatus.CREATED);
    } catch (Exception e) {
        // Log del error para fines de depuración
        e.printStackTrace(); 
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    // Get a property by ID
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Optional<Property> property = propertyService.getPropertyById(id);
        return property.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all properties
    @GetMapping
    public Iterable<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    // Update an existing property by ID
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@RequestParam String username, @PathVariable Long id, @Valid @RequestBody Property property) {
        Optional<Property> existingProperty = propertyService.getPropertyById(id);
        if (existingProperty.isPresent()) {
            if (!existingProperty.get().getUser().getUsername().equals(username)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            Property updatedProperty = propertyService.updateProperty(id, property);
            return new ResponseEntity<>(updatedProperty, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@RequestParam String username, @PathVariable Long id) {
        Optional<Property> property = propertyService.getPropertyById(id);
        if (property.isPresent()) {
            if (!property.get().getUser().getUsername().equals(username)) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            propertyService.deleteProperty(id);
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
