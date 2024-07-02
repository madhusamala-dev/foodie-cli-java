package com.trainingmug.foodiecli.service;

import com.trainingmug.foodiecli.exceptions.DishExistsException;
import com.trainingmug.foodiecli.exceptions.RestaurantExistsException;
import com.trainingmug.foodiecli.model.Dish;
import com.trainingmug.foodiecli.model.Restaurant;
import com.trainingmug.foodiecli.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;

public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> getRestaurantList() {
        return this.restaurantRepository.getRestaurantList();
    }

    @Override
    public Restaurant save(Restaurant restaurant) throws RestaurantExistsException {
        Optional<Restaurant> restaurantById = this.restaurantRepository.getRestaurantById(restaurant.getId());
        if(restaurantById.isPresent())
            throw new RestaurantExistsException("Restaurant Already Exists with this Id  :" + restaurant.getId());
        return this.restaurantRepository.save(restaurant);
    }


}
