package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/**
 * Clase principal para la aplicación Spring Boot que accede a datos JPA.
 * Esta clase inicia la aplicación y configura el contexto de Spring.
 */
@SpringBootApplication
public class AccessingDataJpaApplication {

    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);
    
     /**
     * Método principal que inicia la aplicación.
     * 
     * @param args Argumentos de línea de comandos pasados a la aplicación.
     */
    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class, args);
    }

}
