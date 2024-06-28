package com.trainingmug.foodiecli.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order {
    private String orderId;
    private Customer customer;
    private Restaurant restaurant;
    private List<Dish> dishes;
    private double totalPrice;
    private Date orderDate;

    // Constructor
    public Order(String orderId, Customer customer, Restaurant restaurant, List<Dish> dishes, double totalPrice, Date orderDate) {
        this.orderId = orderId;
        this.customer = customer;
        this.restaurant = restaurant;
        this.dishes = dishes;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    // Getter for orderId
    public String getOrderId() {
        return orderId;
    }

    // Setter for orderId
    public Order setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    // Getter for customer
    public Customer getCustomer() {
        return customer;
    }

    // Setter for customer
    public Order setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    // Getter for restaurant
    public Restaurant getRestaurant() {
        return restaurant;
    }

    // Setter for restaurant
    public Order setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
        return this;
    }

    // Getter for dishes
    public List<Dish> getDishes() {
        return dishes;
    }

    // Setter for dishes
    public Order setDishes(List<Dish> dishes) {
        this.dishes = dishes;
        return this;
    }

    // Getter for totalPrice
    public double getTotalPrice() {
        return totalPrice;
    }

    // Setter for totalPrice
    public Order setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    // Getter for orderDate
    public Date getOrderDate() {
        return orderDate;
    }

    // Setter for orderDate
    public Order setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    // Overriding hashCode
    @Override
    public int hashCode() {
        return Objects.hash(orderId, customer, restaurant, dishes, totalPrice, orderDate);
    }

    // Overriding equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Order order = (Order) obj;
        return Double.compare(order.totalPrice, totalPrice) == 0 &&
                Objects.equals(orderId, order.orderId) &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(restaurant, order.restaurant) &&
                Objects.equals(dishes, order.dishes) &&
                Objects.equals(orderDate, order.orderDate);
    }

    // Overriding toString
    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customer=" + customer +
                ", restaurant=" + restaurant +
                ", dishes=" + dishes +
                ", totalPrice=" + totalPrice +
                ", orderDate=" + orderDate +
                '}';
    }
}
