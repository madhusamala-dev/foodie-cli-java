package com.trainingmug.foodiecli.controller;

import com.trainingmug.foodiecli.model.Restaurant;
import com.trainingmug.foodiecli.service.RestaurantServiceImpl;

import java.util.List;

public class RestaurantController {

    private final RestaurantServiceImpl restaurantService;

    public RestaurantController(RestaurantServiceImpl restaurantService) {
        this.restaurantService = restaurantService;
    }

    public List<Restaurant> getRestaurantList(){
        return this.restaurantService.getRestaurantList();
    }
}
