package com.example.examen.service.impl;

import com.example.examen.dto.CarAddDto;
import com.example.examen.dto.CarDetailDto;
import com.example.examen.dto.CarDto;
import com.example.examen.entity.Car;
import com.example.examen.repository.CarRepository;
import com.example.examen.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    CarRepository carRepository;


    @Override
    public List<CarDto> getAll() throws Exception {
        List<CarDto> cars = new ArrayList<>();
        Iterable<Car> iterable = carRepository.findAll();
        iterable.forEach(car -> {
            cars.add(new CarDto(car.getCarId(),
                    car.getMake(),
                    car.getModel(),
                    car.getYear(),
                    car.getEngineType(),
                    car.getColor()));
        });
           return cars;
    }

    @Override
    public Optional<CarDetailDto> getCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> new CarDetailDto(car.getCarId(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getLicensePlate(),
                car.getOwnerName(),
                car.getOwnerContact(),
                car.getPurchaseDate(),
                car.getMileage(),
                car.getEngineType(),
                car.getColor(),
                car.getInsuranceCompany(),
                car.getInsurancePolicyNumber(),
                car.getRegistrationExpirationDate(),
                car.getServiceDueDate()));
    }

    @Override
    public boolean updateCar(CarDto carDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carDto.CarId());
        return optional.map(car -> {
            car.setMake(carDto.make());
            car.setModel(carDto.model());
            car.setYear(carDto.year());
            car.setEngineType(carDto.engineType());
            car.setColor(carDto.color());
            carRepository.save(car);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean deleteCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);
        return optional.map( car -> {
            carRepository.delete(car);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean addCar(CarAddDto carAddDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carAddDto.carId());
        if (optional.isPresent())
            return false;
        else {
            Car car = new Car();
            car.setMake(carAddDto.make());
            car.setModel(carAddDto.model());
            car.setYear(carAddDto.year());
            car.setVin(carAddDto.vin());
            car.setLicensePlate(carAddDto.licensePlate());
            car.setOwnerName(carAddDto.ownerName());
            car.setOwnerContact(carAddDto.ownerContact());
            car.setPurchaseDate(carAddDto.purchaseDate());
            car.setMileage(carAddDto.mileage());
            car.setEngineType(carAddDto.engineType());
            car.setColor(carAddDto.color());
            car.setInsuranceCompany(carAddDto.insuranceCompany());
            car.setInsurancePolicyNumber(carAddDto.insurancePolicyNumber());
            car.setRegistrationExpirationDate(carAddDto.registrationExpirationDate());
            car.setServiceDueDate(carAddDto.serviceDueDate());
            carRepository.save(car);
            return true;
        }
    }
}
