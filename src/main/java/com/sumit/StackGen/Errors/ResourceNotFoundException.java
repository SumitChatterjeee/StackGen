package com.sumit.StackGen.Errors;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private final String resourceName;
    private final String resourceId;

    public ResourceNotFoundException(String resourceName, String resourceId) {
        super(resourceName + " not found with id: " + resourceId);
        this.resourceName = resourceName;
        this.resourceId = resourceId;
    }
}