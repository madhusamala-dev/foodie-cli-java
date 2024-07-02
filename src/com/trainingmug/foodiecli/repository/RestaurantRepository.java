package com.trainingmug.foodiecli.repository;

import com.trainingmug.foodiecli.factory.Factory;
import com.trainingmug.foodiecli.model.Dish;
import com.trainingmug.foodiecli.model.Restaurant;
import com.trainingmug.foodiecli.util.CsvReader;

import java.util.List;
import java.util.Optional;

public class RestaurantRepository {

    private List<Restaurant> restaurantList;

    public RestaurantRepository() {
        this.restaurantList = Factory.getCsvReader().readRestaurantsFromCsv();
    }

    public List<Restaurant> getRestaurantList(){
        return this.restaurantList;
    }

    public Restaurant save(Restaurant restaurant){ this.restaurantList.add(restaurant); return restaurant; }

    public Optional<Restaurant> getRestaurantById(String id) {
        return this.restaurantList.stream().filter(restaurant -> restaurant.getId().equals(id)).findFirst();
    }
}
