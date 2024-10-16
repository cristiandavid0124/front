package com.example.demo;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.demo.model.*;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para validar la entidad {@link Property}.
 * 
 * Esta clase contiene pruebas unitarias que verifican las restricciones de validación
 * aplicadas a la clase {@link Property}. Utiliza el marco de pruebas JUnit y Jakarta Bean Validation
 * para asegurar que las propiedades cumplen con las reglas de negocio definidas.
 * 
 * @see Property
 */
public class PropertyTest {

    /**
     * Validador utilizado para validar las instancias de Property.
     */
    private Validator validator;

    /**
     * Método de configuración que se ejecuta antes de cada prueba.
     * Inicializa el validador de Bean Validation.
     */
    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Prueba que verifica que una propiedad válida no genere violaciones de restricción.
     * 
     * Se crea una instancia válida de {@link Property} y se valida que no existan
     * violaciones en las restricciones definidas.
     */
    @Test
    public void testValidProperty() {
        Property property = new Property("123 Main St", 250000, 1200, "A beautiful property");

        Set<ConstraintViolation<Property>> violations = validator.validate(property);

        assertTrue(violations.isEmpty(), "Property should be valid");
    }

    /**
     * Prueba que verifica que la dirección de la propiedad no esté en blanco.
     * 
     * Se crea una propiedad con una dirección vacía y se valida que genere una
     * violación de restricción correspondiente.
     */
    @Test
    public void testAddressNotBlank() {
        Property property = new Property("", 250000, 1200, "A beautiful property");

        Set<ConstraintViolation<Property>> violations = validator.validate(property);

        assertFalse(violations.isEmpty(), "Property address should not be blank");
        assertEquals("Address is required", violations.iterator().next().getMessage());
    }

    /**
     * Prueba que verifica que el precio de la propiedad sea mayor que cero.
     * 
     * Se crea una propiedad con un precio de cero y se valida que genere una
     * violación de restricción correspondiente.
     */
    @Test
    public void testPriceMustBeGreaterThanZero() {
        Property property = new Property("123 Main St", 0, 1200, "A beautiful property");

        Set<ConstraintViolation<Property>> violations = validator.validate(property);

        assertFalse(violations.isEmpty(), "Property price must be greater than 0");
        assertEquals("Price must be greater than 0", violations.iterator().next().getMessage());
    }

    /**
     * Prueba que verifica que el tamaño de la propiedad sea mayor que cero.
     * 
     * Se crea una propiedad con un tamaño de cero y se valida que genere una
     * violación de restricción correspondiente.
     */
    @Test
    public void testSizeMustBeGreaterThanZero() {
        Property property = new Property("123 Main St", 250000, 0, "A beautiful property");

        Set<ConstraintViolation<Property>> violations = validator.validate(property);

        assertFalse(violations.isEmpty(), "Property size must be greater than 0");
        assertEquals("Size must be greater than 0", violations.iterator().next().getMessage());
    }

    /**
     * Prueba que verifica que la descripción de la propiedad no esté en blanco.
     * 
     * Se crea una propiedad con una descripción vacía y se valida que genere una
     * violación de restricción correspondiente.
     */
    @Test
    public void testDescriptionNotBlank() {
        Property property = new Property("123 Main St", 250000, 1200, "");

        Set<ConstraintViolation<Property>> violations = validator.validate(property);

        assertFalse(violations.isEmpty(), "Property description should not be blank");
        assertEquals("Description is required", violations.iterator().next().getMessage());
    }

    /**
     * Prueba que verifica que la descripción de la propiedad no exceda los 500 caracteres.
     * 
     * Se crea una propiedad con una descripción que excede el límite permitido y se valida
     * que genere una violación de restricción correspondiente.
     */
    @Test
    public void testDescriptionMaxLength() {
        String longDescription = "A".repeat(501); // Generar una descripción de 501 caracteres
        Property property = new Property("123 Main St", 250000, 1200, longDescription);

        Set<ConstraintViolation<Property>> violations = validator.validate(property);

        assertFalse(violations.isEmpty(), "Property description should not exceed 500 characters");
        assertEquals("Description can't be longer than 500 characters", violations.iterator().next().getMessage());
    }
}
