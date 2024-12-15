package com.example.examen.service;

import com.example.examen.dto.CarAddDto;
import com.example.examen.dto.CarDto;
import com.example.examen.dto.CarDetailDto;

import java.util.List;
import java.util.Optional;

public interface ManageService {

    List<CarDto> getAll() throws Exception;

    Optional<CarDetailDto> getCarById(int id) throws Exception;

    boolean updateCar(CarDto carDto) throws Exception;

    boolean deleteCarById(int id) throws Exception;

    boolean addCar(CarAddDto carAddDto) throws Exception;
}
