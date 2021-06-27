package com.fruitbox.controller;

import com.fruitbox.model.Box;
import com.fruitbox.model.Fruit;
import com.fruitbox.repository.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FruitBoxController {

    @Autowired
    private FruitRepository fruitRepository;

    private List<Box> boxList;

    @GetMapping("/fruit")
    public String sortFruits(Model model) {

        boxList = new ArrayList<>();
        List<Fruit> fruitList = fruitRepository.findAll();

        for (Fruit fruit : fruitList) {
            putFruitsInBox(fruit);
        }

        model.addAttribute("boxList", boxList);
        model.addAttribute("fruitList", fruitList);

        return "fruit-box";
    }

    private void putFruitsInBox(Fruit fruit) {

//      TODO: Check what happens when the fruit has more weight than the box capacity
        if (fruit.getWeight() > Box.MAX_WEIGHT) {
            fruit.setWeight(Box.MAX_WEIGHT);
        }

        Box box = selectBox(fruit.getWeight());
        boolean isNew = box.getFruitList().isEmpty();
        box.addFruit(fruit);
        if(isNew) {
            boxList.add(box);
        }
    }

    private Box selectBox(float weight) {

        if (boxList.size() > 0) {
            Box box = boxList.get(boxList.size() - 1);
            boolean isBoxFull = box.getWeight() >= 1000 || box.getRemainingCapacity() < weight;
            return isBoxFull ? new Box() : box;
        } else {
            return new Box();
        }
    }
}
