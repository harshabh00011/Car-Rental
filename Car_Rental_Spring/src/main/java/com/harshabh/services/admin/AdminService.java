package com.harshabh.services.admin;

import java.io.IOException;

import com.harshabh.dto.CarDto;

public interface AdminService {

	boolean postCar(CarDto carDto) throws IOException;
	
}
