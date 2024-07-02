package com.trainingmug.foodiecli.service;

import com.trainingmug.foodiecli.exceptions.RestaurantExistsException;
import com.trainingmug.foodiecli.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    public List<Restaurant> getRestaurantList();
    public Restaurant save(Restaurant restaurant) throws RestaurantExistsException;
}
