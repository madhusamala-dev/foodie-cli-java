package com.trainingmug.foodiecli.service;

import com.trainingmug.foodiecli.model.Restaurant;
import com.trainingmug.foodiecli.repository.RestaurantRepository;

import java.util.List;

public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> getRestaurantList() {
        return this.restaurantRepository.getRestaurantList();
    }
}
