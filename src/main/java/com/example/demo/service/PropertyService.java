package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Property;
import com.example.demo.model.User;
import com.example.demo.repository.PropertyRepository;

import java.util.Optional;

/**
 * Servicio que maneja las operaciones relacionadas con las propiedades.
 * 
 * Esta clase proporciona métodos para crear, obtener, actualizar y eliminar
 * propiedades. Utiliza un repositorio de propiedades para interactuar con la base
 * de datos. Está anotada con {@link Service} para ser detectada por el contenedor
 * de Spring y se inyecta automáticamente cuando es necesario.
 * 
 * @see Property
 * @see PropertyRepository
 */
@Service
public class PropertyService {

    /**
     * Repositorio utilizado para realizar operaciones de acceso a datos en
     * la entidad Property.
     */
    private final PropertyRepository propertyRepository;

    /**
     * Constructor para inyectar el repositorio de propiedades.
     * 
     * @param propertyRepository el repositorio de propiedades a inyectar
     */
    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    /**
     * Crea una nueva propiedad en la base de datos.
     * 
     * @param property la propiedad a crear
     * @return la propiedad creada
     */
    public Property createProperty(Property property, User user) {
        property.setUser(user); // Asocia la propiedad con el usuario
        return propertyRepository.save(property);
    }

    /**
     * Obtiene una propiedad por su ID.
     * 
     * @param id el ID de la propiedad a buscar
     * @return un {@link Optional} que contiene la propiedad si se encuentra,
     *         o vacío si no existe
     */
    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    /**
     * Obtiene todas las propiedades de la base de datos.
     * 
     * @return un iterable con todas las propiedades
     */
    public Iterable<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    /**
     * Actualiza una propiedad existente en la base de datos.
     * 
     * @param id       el ID de la propiedad a actualizar
     * @param property la propiedad con los nuevos datos
     * @return la propiedad actualizada
     */
    public Property updateProperty(Long id, Property property) {
        property.setId(id);
        return propertyRepository.save(property);
    }

    /**
     * Elimina una propiedad de la base de datos por su ID.
     * 
     * @param id el ID de la propiedad a eliminar
     */
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}

