package com.avm.Weather_App.controller;


import com.avm.Weather_App.dto.WeatherForeCast;
import com.avm.Weather_App.dto.WeatherResponse;
import com.avm.Weather_App.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
@CrossOrigin
public class Controller {

    @Autowired
    private WeatherService service;


    @GetMapping("/my/{city}")
    //here {city} and PathVariable will be match
    public WeatherResponse getWeather(@PathVariable String city){

        return service.getData(city);
    }

    @GetMapping("/forecast")
    //here {city} and PathVariable will be match
    public WeatherForeCast getForecast(@RequestParam String city, @RequestParam int days){

        return service.getForeCast(city, days);
    }


}
