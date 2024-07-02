package com.trainingmug.foodiecli.controller;

import com.trainingmug.foodiecli.model.Dish;
import com.trainingmug.foodiecli.service.DishServiceImpl;

import java.util.List;

public class DishController {

    private DishServiceImpl dishService;

    public DishController(DishServiceImpl dishService) {
        this.dishService = dishService;
    }

    public List<Dish> getDisesList(){
        return this.dishService.getDishesList();
    }
}
