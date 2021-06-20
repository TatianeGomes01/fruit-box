package com.fruitbox.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Cacheable(false)
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private float price;
    private float weight;
    private int quantity;

    @Transient
    private int splitQtt = quantity;

    public Fruit() {
    }

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

        if (maxFruitQtt > splitQtt) {
            maxFruitQtt = splitQtt;
        }

        return splitFruit(maxFruitQtt);
    }

    private Fruit splitFruit(int splitQtt) {
        Fruit splitFruit = new Fruit();

        splitFruit.id = this.id;
        splitFruit.type = this.type;
        splitFruit.price = this.price;
        splitFruit.weight = this.weight;
        splitFruit.quantity = splitQtt;

        this.splitQtt = quantity - splitQtt;

        return splitFruit;
    }

    public List<Fruit> splitFruitByWeight(float weight) {

        int numberOfBoxes = (int) Math.ceil(getTotalWeight() / weight);
        List<Fruit> fruitSplitByWeight = new ArrayList<>();

        splitQtt = quantity;

        for (int i = 0; i < numberOfBoxes; i++) {
            fruitSplitByWeight.add(getFruitQttByWeight(weight));
        }

        return fruitSplitByWeight;
    }
}


