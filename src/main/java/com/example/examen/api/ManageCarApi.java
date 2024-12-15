package com.example.examen.api;

import com.example.examen.dto.CarAddDto;
import com.example.examen.dto.CarDetailDto;
import com.example.examen.dto.CarDto;
import com.example.examen.response.*;
import com.example.examen.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class ManageCarApi {

    @Autowired
    ManageService manageService;

    @GetMapping("/all")
    public FindCarsResponse findCars() {
        try {
            List<CarDto> cars = manageService.getAll();
            if (!cars.isEmpty())
                return new FindCarsResponse("01", null, cars);
            else
                return new FindCarsResponse("02", "Cars Not Found", null);
        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarsResponse("99", "Cars Not Found", null);
        }
    }


    @GetMapping("/detail")
    public FindCarResponse findCar(@RequestParam(value = "id", defaultValue = "0") String id) {
        try {
            Optional<CarDetailDto> optional = manageService.getCarById(Integer.parseInt(id));
            return optional.map(car ->
                    new FindCarResponse("01", null, car)
            ).orElse(
                    new FindCarResponse("02", null, null)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarResponse("99", "Car Not Found", null);
        }
    }


    @PutMapping("/update")
    public UpdateCarResponse updateCar(@RequestBody CarDto carDto) {
        try {
            if (manageService.updateCar(carDto))
                return new UpdateCarResponse("01", null);
            else
                return new UpdateCarResponse("02", "An error occurred, please try again");
        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateCarResponse("99", "An error occurred, please try again");
        }
    }


    @DeleteMapping("/delete/{id}")
    public DeleteCarResponse updateCar(@PathVariable String id) {
        try {
            if (manageService.deleteCarById(Integer.parseInt(id)))
                return new DeleteCarResponse("01", null);
            else
                return new DeleteCarResponse("02", "An error occurred, please try again");
        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteCarResponse("99", "An error occurred, please try again");
        }
    }


    @PostMapping("/create")
    public CreateCarResponse createCar(@RequestBody CarAddDto carAddDto) {
        try {
            if (manageService.addCar(carAddDto))
                return new CreateCarResponse("01", null);
            else
                return new CreateCarResponse("02", "An error occurred, please try again");
        } catch (Exception e) {
            e.printStackTrace();
            return new CreateCarResponse("99", "An error occurred, please try again");
        }

    }

}
