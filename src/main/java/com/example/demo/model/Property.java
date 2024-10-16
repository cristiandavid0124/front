package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Address is required")
    private String address;

    @Min(value = 1, message = "Price must be greater than 0")
    private int price;

    @Min(value = 1, message = "Size must be greater than 0")
    private int size;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description can't be longer than 500 characters")
    private String description;

    @ManyToOne // Define la relación con el modelo User
    @JoinColumn(name = "user_id") // Especifica la columna en la tabla de propiedades
    private User user;

    protected Property() {}

    public Property(String address, int price, int size, String description) {
        this.address = address;
        this.price = price;
        this.size = size;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public int getPrice() {
        return price;
    }

    public int getSize() {
        return size;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user; // Método getter para el usuario
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) { // Método setter para el usuario
        this.user = user;
    }
    
    @Override
    public String toString() {
        return String.format("Property[id=%d, address='%s', price=%d, size=%d, description='%s', user='%s']",
                id, address, price, size, description, user != null ? user.getUsername() : "null"); // Asegúrate de que el método getUsername() esté definido en User
    }
}
