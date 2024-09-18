package com.harshabh.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harshabh.dto.CarDto;
import com.harshabh.services.admin.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/car")
	public ResponseEntity<?> postCar(@ModelAttribute CarDto carDto) throws IOException{
		boolean success = adminService.postCar(carDto);
		if(success) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
}
