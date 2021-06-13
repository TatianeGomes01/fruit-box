package com.fruitbox.model;

import java.util.ArrayList;
import java.util.List;

public class Box {

    public static final float MAX_WEIGHT = 1000;
    public static final float PACKAGING_FEE = 2;

    private List<Fruit> fruitList = new ArrayList<>();
    private float weight = 0;
    private float price = 0;

    public Box(Fruit fruit) {
        this.fruitList.add(fruit);
    }

    public Box() {}

    public List<Fruit> getFruitList() {
        return fruitList;
    }

    public float getWeight() {
        for (Fruit fruit : fruitList) {
            weight += fruit.getTotalWeight();
        }
        return weight;
    }

    public float getPrice() {
        for (Fruit fruit : fruitList) {
            price += fruit.getTotalPrice();
        }
        return price + PACKAGING_FEE;
    }

    public void addFruit(Fruit fruit){
     this.fruitList.add(fruit);
    }

    public boolean isNewBox(){
        return this.fruitList.size() == 0;
    }
}
