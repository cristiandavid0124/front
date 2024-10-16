package com.example.demo.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.Property;
import com.example.demo.model.User;

public interface PropertyRepository extends CrudRepository<Property, Long> {

    List<Property> findByUser(User user);

}
