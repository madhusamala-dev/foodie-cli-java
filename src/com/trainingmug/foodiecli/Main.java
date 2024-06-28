package com.trainingmug.foodiecli;

import com.trainingmug.foodiecli.ui.Menu;
import com.trainingmug.foodiecli.util.CsvReader;

public class Main {
    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader();
        System.out.println("-----------Dishes------------------------");
        System.out.println( csvReader.readDishesFromCsv() );
        System.out.println("-----------Customers------------------------");
        System.out.println( csvReader.readCustomersFromCsv());
        System.out.println("---------- Restaurants -----------------");
        System.out.println( csvReader.readRestaurantsFromCsv());

        Menu menu = new Menu();
        menu.displayMainMenu();
    }
}
