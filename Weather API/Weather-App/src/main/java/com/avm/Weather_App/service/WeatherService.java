package com.avm.Weather_App.service;

import com.avm.Weather_App.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    //so when ever we want to Some values form Application.property that time we use this
    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.forcast.url}")
    private String forecastUrl;

    //so Basically this class will help to Consume API from Another class
    private RestTemplate template=new RestTemplate();



    public WeatherResponse getData(String city){
        String url= apiUrl+"?key="+apiKey+"&q="+city;
        Root response=template.getForObject(url, Root.class);
        WeatherResponse weatherResponse= new WeatherResponse();


        weatherResponse.setCity(response.getLocation().name);
        weatherResponse.setRegion(response.getLocation().region);
        weatherResponse.setCountry(response.getLocation().country);

        String condition= response.getCurrent().getCondition().getText();

        weatherResponse.setCondition(condition);
        weatherResponse.setTemperature(response.getCurrent().getTemp_c());


        return weatherResponse;
    }

    public WeatherForeCast getForeCast(String city, int days){

        WeatherForeCast weatherForeCast=new WeatherForeCast();
        WeatherResponse weatherResponse= getData(city);


        WeatherForeCast response=new WeatherForeCast();

        response.setWeatherResponse(weatherResponse);

        List<DayTemp> dayList=new ArrayList<>();

        String url= forecastUrl+"?key="+apiKey+"&q="+city+"&days="+days;
        Root apiResponse=template.getForObject(url, Root.class);
        Forecast forecast = apiResponse.getForecast();

        ArrayList<Forecastday> forecastday=forecast.getForecastday();

        for(Forecastday rs: forecastday){
            DayTemp d=new DayTemp();
            d.setDate(rs.getDate());
            d.setMinTemp(rs.getDay().mintemp_c);
            d.setAvgTemp(rs.getDay().avgtemp_c);
            d.setMaxTemp(rs.getDay().maxtemp_c);

            dayList.add(d);
        }

        response.setDayTemp(dayList);
        return response;
    }
}
