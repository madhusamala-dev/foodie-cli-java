package com.trainingmug.foodiecli.ui;

import com.trainingmug.foodiecli.controller.CustomerController;
import com.trainingmug.foodiecli.controller.DishController;
import com.trainingmug.foodiecli.controller.RestaurantController;
import com.trainingmug.foodiecli.exceptions.DishExistsException;
import com.trainingmug.foodiecli.exceptions.RestaurantExistsException;
import com.trainingmug.foodiecli.factory.Factory;
import com.trainingmug.foodiecli.model.Dish;
import com.trainingmug.foodiecli.model.Restaurant;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private DishController dishController;
    private CustomerController customerController;
    private RestaurantController restaurantController;

    public Menu() {
        this.dishController = Factory.getDishController();
        this.customerController = Factory.getCustomerController();
        this.restaurantController = Factory.getRestaurantController();
    }

    public void displayMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                displayMenuHeader("WELCOME TO FOODIE APP");
                System.out.println();
                System.out.println("Please select the option !");
                System.out.println("--------------------------");
                System.out.println("1. Customer Section");
                System.out.println("2. Restaurant Section");
                System.out.println("3. Dishes Section");
                System.out.println("4. Order Section ");
                System.out.println("5. Exit");
                System.out.println("Please enter your choice (1-5)");

                int input = scanner.nextInt();
                switch (input) {
                    case 1 -> new CustomerMenu().displayMenu();
                    case 2 -> new RestaurantsMenu().displayMenu();
                    case 3 -> new DishesMenu().displayMenu();
                    case 4 -> new OrdersMenu().displayMenu();
                    case 5 -> {
                        System.out.println("Thanks for choosing Foodie App, See you again !");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid Input. Please enter the valid input from(1-7)");

                }
            }
        } catch (Exception e) {
            System.out.println("Some internal error occurred. Please try again !");
            e.printStackTrace();
            displayMenu();
        }
    }

    private void newRestaurantForm() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the following details\n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            System.out.println("Enter Name");
            String name = scanner.nextLine();
            System.out.println("Enter Address");
            String address = scanner.nextLine();
            System.out.println("Enter Dishes for Menu separated by : (D010:D009)");
            String menu = scanner.nextLine();
            Restaurant restaurant = new Restaurant();
            restaurant.setId(id)
                    .setName(name)
                    .setAddress(address)
                    .setMenu(Arrays.asList(menu.split(":")));
            restaurantController.saveRestaurant(restaurant);

        } catch (RestaurantExistsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Some internal error occurred. Please try again !");
            newRestaurantForm();
        }
    }

    private void newDishForm() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the following details\n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            System.out.println("Enter Name");
            String name = scanner.nextLine();
            System.out.println("Enter Description");
            String description = scanner.nextLine();
            System.out.println("Enter Price");
            double price = scanner.nextDouble();
            Dish dish = new Dish();
            dish.setId(id)
                    .setName(name)
                    .setDescription(description)
                    .setPrice(price);

            this.dishController.save(dish);
            System.out.println(" : New Dish Added Successfully : " + dish.getId());
        } catch (DishExistsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Some internal error occurred. Please try again !");
            newDishForm();
        }


    }

    private void displayRestaurants() {
        List<Restaurant> restaurantList = this.restaurantController.getRestaurantList();
        String dashesLine = new String(new char[150]).replace('\0', '-');
        displayMenuHeader("Restaurants");
        System.out.printf("%-10s %-30s %-80s %-30s\n", "Id", "Name", "Address", "Menu Items");
        System.out.println(dashesLine);
        restaurantList.forEach(restaurant -> {
            System.out.printf("%-10s %-30s %-80s %-30s\n", restaurant.getId(), restaurant.getName(), restaurant.getAddress(), String.join(":", restaurant.getMenu()));
        });
    }

    private void displayDishes() {

        List<Dish> dishesList = this.dishController.getDisesList();
        String dashesLine = new String(new char[150]).replace('\0', '-');
        displayMenuHeader("Menu Items");
        System.out.printf("%-10s %-30s %-80s %-10s\n", "Id", "Name", "Description", "Price");
        System.out.println(dashesLine);
        dishesList.forEach(dish -> {
            System.out.printf("%-10s %-30s %-80s %-10s\n", dish.getId(), dish.getName(), dish.getDescription(), String.format("$%.2f", dish.getPrice()));
        });

    }


    public void displayMenuHeader(String menuHeader) {
        String dashesLine = new String(new char[150]).replace('\0', '-');
        System.out.println(dashesLine);
        String spaces = new String(new char[70]).replace('\0', ' ');
        System.out.printf("%-70s %-10s %-70s \n", spaces, menuHeader, spaces);
        System.out.println(dashesLine);
    }
}
