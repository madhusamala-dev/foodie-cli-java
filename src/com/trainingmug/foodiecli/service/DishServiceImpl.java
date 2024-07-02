package com.trainingmug.foodiecli.service;

import com.trainingmug.foodiecli.model.Dish;
import com.trainingmug.foodiecli.repository.DishRepository;

import java.util.List;

public class DishServiceImpl implements DishService{

    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public List<Dish> getDishesList() {
        return this.dishRepository.getDishList();
    }
}
