package com.cars.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import com.cars.models.Car;

@Service
public class CarService {
	

	public List<Car> getAllCars() {
		List<Car> cars = new ArrayList<>();
		
		BufferedReader fileReader;
		try {
			fileReader = new BufferedReader(new FileReader("src/main/resources/arabalar.txt"));
			String line = fileReader.readLine();
			while (line != null) {
				List<String> carDetails = Arrays.asList(line.split(";"));
				Car car = new Car(carDetails.get(0), carDetails.get(1), carDetails.get(2));
				cars.add(car);
				line = fileReader.readLine();
			}
			fileReader.close();
		} catch(IOException exception) {
			exception.printStackTrace();
		}
		
		return cars;
	}
	
	
	public List<Car> finalSearchCriteria(String brand, String model, String type) {
		List<Car> cars = getAllCars();
		
		if(type != null) {
			for(int i = 0; i < cars.size(); i++) {
				if(!cars.get(i).getType().contains(type)) {
					cars.remove(i);
					i = i-1;
				}
			}
		}
		
		if(brand != null) {
			for(int i = 0; i < cars.size(); i++) {
				if(!cars.get(i).getBrand().equals(brand)) {
					cars.remove(i);
					i = i-1;
				}
			}
		}
		
		if(model != null) {
			for(int i = 0; i < cars.size(); i++) {
				if(!cars.get(i).getModel().equals(model)) {
					cars.remove(i);
					i = i-1;
				}
			}
		}
		
		return cars;
	}
	
	public List<Car> searchKey(char key) {
		List<Car> cars = getAllCars();
		List<Car> filtered = new ArrayList<>();
		
		for(int i = 0; i < cars.size(); i++) {
			Car current = cars.get(i);
			String brand = current.getBrand();
			String model = current.getModel();
			String type = current.getType();
			
			if(brand.contains(Character.toString(key)) || model.contains(Character.toString(key)) || type.contains(Character.toString(key))) {
				filtered.add(current);
			}
		}
		return filtered;
	}
}
