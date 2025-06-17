package com.facu.aep.controller;

import com.facu.aep.dto.WeatherDTO;
import com.facu.aep.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
@CrossOrigin(origins = "*")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public WeatherDTO getWeather(@RequestParam(required = false) String city,
                                 @RequestParam(required = false) Double lat,
                                 @RequestParam(required = false) Double lon) {
        if (lat != null && lon != null) {
            return weatherService.getWeatherByCoordinates(lat, lon);
        } else if (city != null && !city.isEmpty()) {
            return weatherService.getWeatherByCity(city);
        } else {
            throw new IllegalArgumentException("Informe parâmetro 'city' ou 'lat' e 'lon'.");
        }
    }
}
