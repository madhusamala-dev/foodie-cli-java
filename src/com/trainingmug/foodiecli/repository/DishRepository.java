package com.trainingmug.foodiecli.repository;

import com.trainingmug.foodiecli.model.Dish;
import com.trainingmug.foodiecli.util.CsvReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DishRepository {

    List<Dish> dishList;

    public DishRepository() {
        this.dishList = new ArrayList<>();
        CsvReader csvReader = new CsvReader();
        this.dishList = csvReader.readDishesFromCsv();
    }

    public List<Dish> getDishList() {
        return this.dishList;
    }

    public Dish addDish(Dish dish) {
        this.dishList.add(dish);
        return dish;
    }

    public Optional<Dish> getDishById(String id) {
        return this.dishList.stream().filter(dish -> dish.getId().equals(id)).findFirst();
    }

    public Optional<Dish> updateDish(Dish dishToBeUpdated) {
        return this.dishList.stream().filter(dish -> dish.getId().equals(dishToBeUpdated.getId()))
                .findFirst()
                .map(dish -> {
                    dish.setName(dishToBeUpdated.getName())
                            .setPrice(dishToBeUpdated.getPrice())
                            .setDescription(dishToBeUpdated.getDescription());
                    return dish;
                });
    }

    public void deleteDish(Dish dish){
        this.dishList.remove(dish);
    }
}
