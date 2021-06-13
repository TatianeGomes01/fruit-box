package com.fruitbox.controller;

import com.fruitbox.model.Box;
import com.fruitbox.model.Fruit;
import com.fruitbox.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FruitBoxController {

    @Autowired
    private FruitRepository fruitRepository;


    @RequestMapping("/fruit")
    public List<Box> sortFruits() {

        List<Fruit> fruitList = fruitRepository.findAll();
        List<Box> boxList = new ArrayList();

        for (Fruit fruit : fruitList) {
            boxList.addAll(putFruitsInBox(fruit, getLastBox(boxList)));
        }

        return boxList;
    }

    private List<Box> putFruitsInBox(Fruit fruit, Box lastBox) {

        List<Box> boxList = new ArrayList();

        if (lastBox.isNewBox()) {
            if (fruit.getTotalWeight() <= Box.MAX_WEIGHT) {
                Box box = new Box(fruit);
                boxList.add(box);
            } else {
                Fruit fruitQttByWeight = fruit.getFruitQttByWeight(Box.MAX_WEIGHT);
                Box box = new Box(fruitQttByWeight);
                boxList.add(box);
                boxList.addAll(putFruitsInBox(fruit, box));
            }
        } else {
            if (fruit.getTotalWeight() <= getLastBoxMaxWeight(lastBox)) {
                lastBox.addFruit(fruit);
            }else {
                putFruitsInBox(fruit, new Box());
            }
        }

        return boxList;

    }

    private boolean isSameBox(Fruit fruit, Box lastBox) {
        return lastBox.getFruitList().size() > 0 && getLastBoxMaxWeight(lastBox) > fruit.getTotalWeight();
    }

    private Box getLastBox(List<Box> boxList) {
        if (boxList.size() > 0) {
            return boxList.get(boxList.size() - 1);
        } else {
            return new Box();
        }
    }

    private float getLastBoxMaxWeight(Box lastBox) {
        return Box.MAX_WEIGHT - lastBox.getWeight();
    }

}
