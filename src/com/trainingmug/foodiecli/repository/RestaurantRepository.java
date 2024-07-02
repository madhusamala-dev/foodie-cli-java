package com.trainingmug.foodiecli.repository;

import com.trainingmug.foodiecli.factory.Factory;
import com.trainingmug.foodiecli.model.Restaurant;
import com.trainingmug.foodiecli.util.CsvReader;

import java.util.List;

public class RestaurantRepository {

    private List<Restaurant> restaurantList;

    public RestaurantRepository() {
        this.restaurantList = Factory.getCsvReader().readRestaurantsFromCsv();
    }

    public List<Restaurant> getRestaurantList(){
        return this.restaurantList;
    }
}
