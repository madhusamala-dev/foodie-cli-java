package com.trainingmug.foodiecli.ui;

import com.trainingmug.foodiecli.controller.CustomerController;
import com.trainingmug.foodiecli.controller.DishController;
import com.trainingmug.foodiecli.controller.RestaurantController;
import com.trainingmug.foodiecli.exceptions.CustomerExistsException;
import com.trainingmug.foodiecli.exceptions.DishExistsException;
import com.trainingmug.foodiecli.factory.Factory;
import com.trainingmug.foodiecli.model.Customer;
import com.trainingmug.foodiecli.model.Dish;
import com.trainingmug.foodiecli.model.Restaurant;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {

            displayMenuHeader("WELCOME TO FOODIE APP");
            System.out.println();
            System.out.println("Please select the option !");
            System.out.println("--------------------------");
            System.out.println("1. Register (New Customer)");
            System.out.println("2. Login  (Existing Customer)");
            System.out.println("3. View Restaurants");
            System.out.println("4. View Dishes ");
            System.out.println("5. Add Restaurant");
            System.out.println("6. Add Dish");
            System.out.println("5. Place Order");
            System.out.println("6. View Orders");
            System.out.println("7. Exit");

            System.out.println("Please enter your choice (1-7)");

            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    customerRegisterForm();
                    break;
                case 3:
                    displayRestaurants();
                    break;
                case 4:
                    displayDishes();
                    break;
                case 6:
                    newDishForm();
                    break;

                default:
                    System.out.println("Invalid Input. Please enter the valid input from(1-7)");

            }
        }
    }

    private void newDishForm() {
        try(Scanner scanner = Factory.getScanner()){
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

         DishController dishController = Factory.getDishController();
         dishController.save(dish);
        } catch(DishExistsException e){
            System.out.println(e.getMessage());
        } catch(Exception e){
            System.out.println("Some internal error occurred. Please try again !");
            newDishForm();
        }


    }

    private void displayRestaurants() {
        RestaurantController restaurantController = Factory.getRestaurantController();
        List<Restaurant> restaurantList = restaurantController.getRestaurantList();
        String dashesLine = new String(new char[150]).replace('\0', '-');
        displayMenuHeader("Restaurants");
        System.out.printf("%-10s %-30s %-80s %-30s\n", "Id", "Name", "Address", "Menu Items");
        System.out.println(dashesLine);
        restaurantList.forEach(restaurant -> {
            System.out.printf("%-10s %-30s %-80s %-30s\n", restaurant.getId(), restaurant.getName(), restaurant.getAddress(), String.join(":", restaurant.getMenu()));
        });
    }

    private void displayDishes() {
        DishController dishController = Factory.getDishController();
        List<Dish> dishesList = dishController.getDisesList();
        String dashesLine = new String(new char[150]).replace('\0', '-');
        displayMenuHeader("Menu Items");
        System.out.printf("%-10s %-30s %-80s %-10s\n", "Id", "Name", "Description", "Price");
        System.out.println(dashesLine);
        dishesList.forEach(dish -> {
            System.out.printf("%-10s %-30s %-80s %-10s\n", dish.getId(), dish.getName(), dish.getDescription(), String.format("$%.2f", dish.getPrice()));
        });

    }

    private void customerRegisterForm() {
        try(Scanner scanner = Factory.getScanner()){
        System.out.println("Please register entering the following details\n");
        System.out.println("Enter Id");
        String id = scanner.nextLine();
        System.out.println("Enter Name");
        String name = scanner.nextLine();
        System.out.println("Enter E-mail");
        String email = scanner.nextLine();
        /*Console console = System.console();
        System.out.println("console : " + console);
        char[] passwordArray = console.readPassword("Enter Password (invisible)");
        String password = String.valueOf(passwordArray);*/
        System.out.println("Enter Password");
        String password = scanner.nextLine();
        // System.out.println("Id : " + id + " , Name : " + name + " , E-mail :  " + email + ", Password :" + password);
        Customer customer = new Customer();
        customer.setId(id)
                .setName(name)
                .setEmail(email)
                .setPassword(password);
       /* CustomerRepository customerRepository = new CustomerRepository();
        CustomerServiceImpl customerService = new CustomerServiceImpl(customerRepository);
        CustomerController customerController = new CustomerController(customerService);*/
        CustomerController customerController = Factory.getCustomerController();


            Customer savedCustomer = customerController.save(customer);
            System.out.println("Customer Registration Successful");
            System.out.println("Details:");
            System.out.println("Id : " + customer.getId());
            System.out.println("Name : " + customer.getName());
            System.out.println("E-mail : " + customer.getEmail());
            System.out.println("Password : " + customer.getPassword());

        } catch (CustomerExistsException e) {
            System.out.println(e.getMessage());
        } catch(Exception e){
            System.out.println("Some internal error occurred. Please try again !");
            customerRegisterForm();
        }

    }

    public void displayMenuHeader(String menuHeader) {
        String dashesLine = new String(new char[150]).replace('\0', '-');
        System.out.println(dashesLine);
        String spaces = new String(new char[70]).replace('\0', ' ');
        System.out.printf("%-70s %-10s %-70s \n", spaces, menuHeader, spaces);
        System.out.println(dashesLine);
    }
}
