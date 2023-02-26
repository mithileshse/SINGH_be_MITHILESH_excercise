package com.ecore.roles.exception;

import static java.lang.String.format;

public class ResourceExistsException extends RuntimeException {


    public <T> ResourceExistsException(Class<T> resource) {
        super(format("%s already exists", resource.getSimpleName()));
    }

    public <T> ResourceExistsException(Class<T> resource, String message) {
        super(format("Invalid '%s' object. %s", resource.getSimpleName(), message));
    }
}
