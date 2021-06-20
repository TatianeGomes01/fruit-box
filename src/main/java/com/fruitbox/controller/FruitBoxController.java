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

        List<Fruit> unmodifiedFruitList = fruitRepository.findAll();
        model.addAttribute("boxList", boxList);
        model.addAttribute("fruitList", unmodifiedFruitList);

        return "fruit-box";
    }

    private void putFruitsInBox(Fruit fruit) {

        List<Fruit> splitFruitList = fruit.splitFruitByWeight(Box.MAX_WEIGHT);
        for (Fruit splitFruit : splitFruitList) {
            Box box = selectBox(splitFruit.getTotalWeight());
            boolean isNewBox = box.getFruitList().size() == 0;
            box.addFruit(splitFruit);
            if (isNewBox) {
                boxList.add(box);
            }
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
