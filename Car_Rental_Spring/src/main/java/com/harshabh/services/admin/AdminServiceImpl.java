package com.harshabh.services.admin;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harshabh.dto.CarDto;
import com.harshabh.entity.Car;
import com.harshabh.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

	@Autowired
	private CarRepository carRepository;

	@Override
	public boolean postCar(CarDto carDto) {
		try {
			Car car=new Car();
			
			car.setName(carDto.getName());
			car.setBrand(carDto.getBrand());
			car.setColor(carDto.getColor());
			car.setDescription(carDto.getDescription());
			car.setPrice(carDto.getPrice());
			car.setTransmission(carDto.getTransmission());
			car.setType(carDto.getType());
			car.setYear(carDto.getYear());
			car.setImage(carDto.getImage().getBytes());
			
			carRepository.save(car);
			return true;
		}
		catch(Exception e) {
			return false;
		}
		
		
	}
	
}
