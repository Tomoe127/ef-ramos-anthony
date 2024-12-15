package com.example.examen.response;

import com.example.examen.dto.CarDto;

import java.util.List;

public record FindCarsResponse(String code, String error, List<CarDto> cars) {
}
