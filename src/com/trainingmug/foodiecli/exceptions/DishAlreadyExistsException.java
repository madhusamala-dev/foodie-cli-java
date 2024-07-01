package com.trainingmug.foodiecli.exceptions;

public class DishAlreadyExistsException extends Exception{
    public DishAlreadyExistsException(String message) {
        super(message);
    }
}
