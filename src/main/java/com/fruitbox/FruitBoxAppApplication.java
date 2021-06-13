package com.fruitbox;

import com.fruitbox.controller.FruitBoxController;
import com.fruitbox.model.Box;
import com.fruitbox.model.Fruit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
//@RestController
public class FruitBoxAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FruitBoxAppApplication.class, args);
	}

//	@GetMapping("/fruit")
//	public String fruitType(@RequestParam(value = "type", defaultValue = "Fruits") String type) {
//
//		Fruit fruit = new Fruit(type, 2, 1000, 10);
//
//		ArrayList<Fruit> fruits = new ArrayList<>();
//
//		List<Box> boxList = FruitBoxController.sortFruits(fruits);
//
//		return String.format("Your fruit type is: %s!", type);
//	}

}
