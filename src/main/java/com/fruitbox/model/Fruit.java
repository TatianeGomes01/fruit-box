package com.fruitbox.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Fruit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private float price;
    private float weight;
    private int quantity;

    public Fruit(){}



    public Fruit(String type, float price, float weight, int quantity) {
        this.type = type;
        this.price = price;
        this.weight = weight;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {

        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getTotalWeight() {
        return this.quantity * this.weight;
    }

    public float getTotalPrice() {
        return this.price * this.quantity;
    }

    public Fruit getFruitQttByWeight(float maxWeight) {

        int maxFruitQtt = (int) (maxWeight / weight);

        return splitFruit(maxFruitQtt);
    }

    private Fruit splitFruit(int quantity){
        Fruit splitFruit = new Fruit();

        splitFruit.id = this.id;
        splitFruit.type = this.type;
        splitFruit.price = this.price;
        splitFruit.weight = this.weight;
        splitFruit.quantity = quantity;

        this.quantity -= quantity;

        return splitFruit;
    }
}


