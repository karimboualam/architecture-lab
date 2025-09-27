// src/main/java/com/architecturelab/hexagonal/domain/exception/DossierNotFoundException.java
package com.architecturelab.hexagonal.domain.exception;

public class DossierNotFoundException extends RuntimeException {
    public DossierNotFoundException(Long id) {
        super("Dossier not found with id " + id);
    }
}
