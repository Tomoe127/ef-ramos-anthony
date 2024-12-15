package com.example.examen.response;

import com.example.examen.dto.CarDetailDto;

public record FindCarResponse(String code, String error, CarDetailDto carDetailDto) {
}
