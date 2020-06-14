package com.cars.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cars.models.Car;
import com.cars.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarRestController {

	@Autowired
	CarService carService;
	
	@GetMapping
	public List<Car> getCars(@RequestParam(value="brand", required = false) String brand, 
			@RequestParam(value="model", required = false) String model,
			@RequestParam(value="type", required = false) String type,
			@RequestParam(value="key", required = false) String inputKey) {
		
		if(inputKey != null && (brand != null ||  model != null || type != null)) {
			return null;
		} else if(inputKey != null) {
			char key = inputKey.charAt(0);
			return carService.searchKey(key);
		}
		else if(brand == null && model == null && type == null) {
			return carService.getAllCars();
		} else {
			return carService.finalSearchCriteria(brand, model, type);
		}
		
	}
}
