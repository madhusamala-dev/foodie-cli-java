package com.trainingmug.foodiecli.ui;

import com.trainingmug.foodiecli.controller.OrderController;
import com.trainingmug.foodiecli.exceptions.*;
import com.trainingmug.foodiecli.factory.Factory;
import com.trainingmug.foodiecli.model.Customer;
import com.trainingmug.foodiecli.model.Dish;
import com.trainingmug.foodiecli.model.Order;
import com.trainingmug.foodiecli.model.Restaurant;
import com.trainingmug.foodiecli.service.CustomerService;
import com.trainingmug.foodiecli.service.DishService;
import com.trainingmug.foodiecli.service.RestaurantService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrdersMenu extends Menu {

    private final OrderController orderController;

    public OrdersMenu() {
        this.orderController = Factory.getOrderController();
    }

    @Override
    public void displayMenu() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                displayMenuHeader("WELCOME TO ORDERS SECTION");
                System.out.println();
                System.out.println("Please select the option !");
                System.out.println("--------------------------");
                System.out.println("1. Place New Order");
                System.out.println("2. Search Order");
                System.out.println("3. View All Orders");
                System.out.println("4. Exit");
                System.out.println("Please enter your choice (1-4)");
                int input = scanner.nextInt();
                switch (input) {
                    case 1 -> newOrderForm();
                    case 2 -> searchOrderForm();
                    case 3 -> ordersList();
                    case 4 -> {
                        System.out.println("Thank you , See you again !");
                        super.displayMenu();
                    }
                    default -> System.out.println("Invalid Input. Please enter the valid input from(1-4)");
                }
            }
        } catch (Exception e) {
            System.out.println("Some internal error occurred. Please try again !");
            displayMenu();
        }

    }

    private void ordersList() {
        List<Order> ordersList = this.orderController.getOrdersList();
        displayMenuHeader("All Order Details");
        ordersList.forEach(order -> {
            String dishNames = order.getDishList().stream().map(Dish::getName).collect(Collectors.joining(","));
            System.out.printf("%-10s %-30s %-30s %-80s %-10s %-10s\n", "Id", "Customer Name", "Restaurant Name", "Items", "Order Date", "Price");
            printDashLine();
            System.out.printf("%-10s %-30s %-30s %-80s %-10s %-10s\n", order.getId(), order.getCustomer().getName(), order.getRestaurant().getName(), dishNames, order.getOrderDate(), order.getTotalPrice());
        });
    }

    private void searchOrderForm() {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the following details to search for Order\n");
            System.out.println("Enter Id");
            String id = scanner.nextLine();
            Order order = orderController.getOrderById(id);
            displayOrderDetails(order);
        } catch (OrderNotFoundException e) {
            System.out.println(e.getMessage());
            displayMenu();
        }
    }

    private void displayOrderDetails(Order order) {
            String dishNames = order.getDishList().stream().map(Dish::getName).collect(Collectors.joining(","));
            displayMenuHeader("Order Details");
            System.out.printf("%-10s %-30s %-30s %-80s %-10s %-10s\n", "Id", "Customer Name", "Restaurant Name", "Items","Order Date","Price");
            printDashLine();
            System.out.printf("%-10s %-30s %-30s %-80s %-10s %-10s\n", order.getId(), order.getCustomer().getName(), order.getRestaurant().getName(), dishNames,order.getOrderDate(),order.getTotalPrice());


    }

    private void newOrderForm() throws DishNotFoundException {

        Customer loggedInCustomer = null;
        Restaurant restaurant = null;
        List<Dish> dishList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(System.in);
            CustomerService customerService = Factory.getCustomerService();
            RestaurantService restaurantService = Factory.getRestaurantService();
            DishService dishService = Factory.getDishService();
            loggedInCustomer = customerService.getCurrentLoggedInCustomer();
            System.out.println("Logged In Customer : " + loggedInCustomer);
            while (loggedInCustomer == null) {
                System.out.println("Please login to place an order");
                new CustomerMenu().customerLoginForm();
                loggedInCustomer = customerService.getCurrentLoggedInCustomer();
            }

            System.out.println("Enter Order Id :");
            String id = scanner.nextLine();

            while (restaurant == null) {
                new RestaurantsMenu().displayRestaurants();
                printDashLine();
                System.out.println("Choose the Restaurant Id (Ex: R08 )");
                String restaurantId = scanner.nextLine();
                restaurant = restaurantService.getRestaurantById(restaurantId);
            }
            char addMoreItems = 'Y';
            while (addMoreItems == 'Y') {
                new RestaurantsMenu().displayMenuItems(restaurant.getId());
                printDashLine();
                System.out.println("Enter the Dish Id (Ex : D001 )");
                String dishId = scanner.nextLine();
                Dish selectedDish = dishService.getDishById(dishId);
                dishList.add(selectedDish);
                System.out.println("One Dish is added successfully : " + selectedDish.getName());
                System.out.println("Do you want to add more dishes (Y/N)");
                addMoreItems = scanner.nextLine().charAt(0);
            }


            double orderPrice = calculateOrderTotalPrice(dishList);
            LocalDate orderDate = LocalDate.now();

            System.out.println("Order Details");
            System.out.println("Order Id : " + id);
            System.out.println("Customer : " + loggedInCustomer);
            System.out.println("Restaurant : " + restaurant);
            System.out.println("Dishes : " + dishList);
            System.out.println("Order Value : " + orderPrice);
            System.out.println("Order Date : " + orderDate);

            Order order = new Order();
            order.setId(id)
                    .setCustomer(loggedInCustomer)
                    .setRestaurant(restaurant)
                    .setDishList(dishList)
                    .setTotalPrice(orderPrice)
                    .setOrderDate(orderDate);

            Order placedOrder = orderController.saveOrder(order);
            displayOrderDetails(placedOrder);

        } catch (RestaurantNotFoundException | OrderExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    private double calculateOrderTotalPrice(List<Dish> dishList) {
        return dishList.stream().mapToDouble(Dish::getPrice).sum();
    }
    }
