# Weather API

- For Creating Weather API
    1. Complete Project- 
        - Our Db, Our Data
        - Mock Everything
    2. Consume Already Existing API

## 1. DTO

- First thing is we make a dto (Data Transfer Object)
- So for now i need 3 things city, Condition (which is a String ) and a Temperature (Which is an Double )
    - We make Constructor and Getter Setter for all the Fields we define

## #. So now we make a basic Weather API with the help of public API key using WeatherAPIkey.com

- So in this we take a API key form [WeatherAPI.com](http://WeatherAPI.com) - api explore and scroll down you will find the Request URL in this website
- we Get Request URL
    - Copy that URL Paste in Postman and get the request
    - Copy the response From the postman and convert that json in pojo and according to it create classes in DTO and make Constructor and Getter Setter in that class
- we need to add that Request URL and API in Appliction.properties

### Application.properties

```java
spring.application.name=Weather-App

weather.api.key=ec0fdff73c63461fbc6100915262302
weather.api.url=http://api.weatherapi.com/v1/current.json
```

### Controller

```java
package com.avm.Weather_App.controller;

import com.avm.Weather_App.dto.Root;
import com.avm.Weather_App.dto.WeatherResponse;
import com.avm.Weather_App.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class Controller {

    @Autowired
    private WeatherService service;

    @GetMapping("/{city}")
    //here {city} and PathVariable will be match
    public String getWeatherData(@PathVariable String city){
        return service.test();
    }
    @GetMapping("/my/{city}")
    //here {city} and PathVariable will be match
    public WeatherResponse getWeather(@PathVariable String city){

        return service.getData(city);
    }

}
```

### WeatherService

```java
package com.avm.Weather_App.service;

import com.avm.Weather_App.dto.Root;
import com.avm.Weather_App.dto.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    //so when ever we want to Some values form Application.property that time we use this
    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    //so Basically this class will help to Consume API from Another class
    private RestTemplate template=new RestTemplate();

    public String test(){
        return "good";
    }

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
}
```

### DTO- WeatherResponse, Condition, Root

```java
//WeatherResponse
package com.avm.Weather_App.dto;

public class WeatherResponse {
    private String city;

    private String region;
    private String country;
    private String condition;

    private Double temperature;

    public WeatherResponse() {
    }

    public WeatherResponse(String city, String region, String country, String condition, Double temperature) {
        this.city = city;
        this.region = region;
        this.country = country;
        this.condition = condition;
        this.temperature = temperature;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}

//Condition
package com.avm.Weather_App.dto;

public class Condition {
    public String text;
    public String icon;
    public int code;

    public Condition() {
    }

    public Condition(String text, String icon, int code) {
        this.text = text;
        this.icon = icon;
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
//root
package com.avm.Weather_App.dto;

public class Root {
    public Location location;
    public Current current;

    public Root() {
    }

    public Root(Location location, Current current) {
        this.location = location;
        this.current = current;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }
}
```

### DTO - Current, location

```java
//Current

package com.avm.Weather_App.dto;

public class Current {
    public int last_updated_epoch;
    public String last_updated;
    public double temp_c;
    public double temp_f;
    public int is_day;
    public Condition condition;
    public double wind_mph;
    public double wind_kph;
    public int wind_degree;
    public String wind_dir;
    public double pressure_mb;
    public double pressure_in;
    public double precip_mm;
    public double precip_in;
    public int humidity;
    public int cloud;
    public double feelslike_c;
    public double feelslike_f;
    public double windchill_c;
    public double windchill_f;
    public double heatindex_c;
    public double heatindex_f;
    public double dewpoint_c;
    public double dewpoint_f;
    public double vis_km;
    public double vis_miles;
    public double uv;
    public double gust_mph;
    public double gust_kph;
    public double short_rad;
    public double diff_rad;
    public double dni;
    public double gti;

    public Current() {
    }

    public Current(int last_updated_epoch, String last_updated, double temp_c, double temp_f, int is_day, Condition condition, double wind_mph, double wind_kph, int wind_degree, String wind_dir, double pressure_mb, double pressure_in, double precip_mm, double precip_in, int humidity, int cloud, double feelslike_c, double feelslike_f, double windchill_c, double windchill_f, double heatindex_c, double heatindex_f, double dewpoint_c, double dewpoint_f, double vis_km, double vis_miles, double uv, double gust_mph, double gust_kph, double short_rad, double diff_rad, double dni, double gti) {
        this.last_updated_epoch = last_updated_epoch;
        this.last_updated = last_updated;
        this.temp_c = temp_c;
        this.temp_f = temp_f;
        this.is_day = is_day;
        this.condition = condition;
        this.wind_mph = wind_mph;
        this.wind_kph = wind_kph;
        this.wind_degree = wind_degree;
        this.wind_dir = wind_dir;
        this.pressure_mb = pressure_mb;
        this.pressure_in = pressure_in;
        this.precip_mm = precip_mm;
        this.precip_in = precip_in;
        this.humidity = humidity;
        this.cloud = cloud;
        this.feelslike_c = feelslike_c;
        this.feelslike_f = feelslike_f;
        this.windchill_c = windchill_c;
        this.windchill_f = windchill_f;
        this.heatindex_c = heatindex_c;
        this.heatindex_f = heatindex_f;
        this.dewpoint_c = dewpoint_c;
        this.dewpoint_f = dewpoint_f;
        this.vis_km = vis_km;
        this.vis_miles = vis_miles;
        this.uv = uv;
        this.gust_mph = gust_mph;
        this.gust_kph = gust_kph;
        this.short_rad = short_rad;
        this.diff_rad = diff_rad;
        this.dni = dni;
        this.gti = gti;
    }

    public int getLast_updated_epoch() {
        return last_updated_epoch;
    }

    public void setLast_updated_epoch(int last_updated_epoch) {
        this.last_updated_epoch = last_updated_epoch;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public double getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(double temp_c) {
        this.temp_c = temp_c;
    }

    public double getTemp_f() {
        return temp_f;
    }

    public void setTemp_f(double temp_f) {
        this.temp_f = temp_f;
    }

    public int getIs_day() {
        return is_day;
    }

    public void setIs_day(int is_day) {
        this.is_day = is_day;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public double getWind_mph() {
        return wind_mph;
    }

    public void setWind_mph(double wind_mph) {
        this.wind_mph = wind_mph;
    }

    public double getWind_kph() {
        return wind_kph;
    }

    public void setWind_kph(double wind_kph) {
        this.wind_kph = wind_kph;
    }

    public int getWind_degree() {
        return wind_degree;
    }

    public void setWind_degree(int wind_degree) {
        this.wind_degree = wind_degree;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public double getPressure_mb() {
        return pressure_mb;
    }

    public void setPressure_mb(double pressure_mb) {
        this.pressure_mb = pressure_mb;
    }

    public double getPressure_in() {
        return pressure_in;
    }

    public void setPressure_in(double pressure_in) {
        this.pressure_in = pressure_in;
    }

    public double getPrecip_mm() {
        return precip_mm;
    }

    public void setPrecip_mm(double precip_mm) {
        this.precip_mm = precip_mm;
    }

    public double getPrecip_in() {
        return precip_in;
    }

    public void setPrecip_in(double precip_in) {
        this.precip_in = precip_in;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getCloud() {
        return cloud;
    }

    public void setCloud(int cloud) {
        this.cloud = cloud;
    }

    public double getFeelslike_c() {
        return feelslike_c;
    }

    public void setFeelslike_c(double feelslike_c) {
        this.feelslike_c = feelslike_c;
    }

    public double getFeelslike_f() {
        return feelslike_f;
    }

    public void setFeelslike_f(double feelslike_f) {
        this.feelslike_f = feelslike_f;
    }

    public double getWindchill_c() {
        return windchill_c;
    }

    public void setWindchill_c(double windchill_c) {
        this.windchill_c = windchill_c;
    }

    public double getWindchill_f() {
        return windchill_f;
    }

    public void setWindchill_f(double windchill_f) {
        this.windchill_f = windchill_f;
    }

    public double getHeatindex_c() {
        return heatindex_c;
    }

    public void setHeatindex_c(double heatindex_c) {
        this.heatindex_c = heatindex_c;
    }

    public double getHeatindex_f() {
        return heatindex_f;
    }

    public void setHeatindex_f(double heatindex_f) {
        this.heatindex_f = heatindex_f;
    }

    public double getDewpoint_c() {
        return dewpoint_c;
    }

    public void setDewpoint_c(double dewpoint_c) {
        this.dewpoint_c = dewpoint_c;
    }

    public double getDewpoint_f() {
        return dewpoint_f;
    }

    public void setDewpoint_f(double dewpoint_f) {
        this.dewpoint_f = dewpoint_f;
    }

    public double getVis_km() {
        return vis_km;
    }

    public void setVis_km(double vis_km) {
        this.vis_km = vis_km;
    }

    public double getVis_miles() {
        return vis_miles;
    }

    public void setVis_miles(double vis_miles) {
        this.vis_miles = vis_miles;
    }

    public double getUv() {
        return uv;
    }

    public void setUv(double uv) {
        this.uv = uv;
    }

    public double getGust_mph() {
        return gust_mph;
    }

    public void setGust_mph(double gust_mph) {
        this.gust_mph = gust_mph;
    }

    public double getGust_kph() {
        return gust_kph;
    }

    public void setGust_kph(double gust_kph) {
        this.gust_kph = gust_kph;
    }

    public double getShort_rad() {
        return short_rad;
    }

    public void setShort_rad(double short_rad) {
        this.short_rad = short_rad;
    }

    public double getDiff_rad() {
        return diff_rad;
    }

    public void setDiff_rad(double diff_rad) {
        this.diff_rad = diff_rad;
    }

    public double getDni() {
        return dni;
    }

    public void setDni(double dni) {
        this.dni = dni;
    }

    public double getGti() {
        return gti;
    }

    public void setGti(double gti) {
        this.gti = gti;
    }
}

//Location
package com.avm.Weather_App.dto;

public class Location {
    public String name;
    public String region;
    public String country;
    public double lat;
    public double lon;
    public String tz_id;
    public int localtime_epoch;
    public String localtime;

    public Location() {
    }

    public Location(String name, String region, String country, double lat, double lon, String tz_id, int localtime_epoch, String localtime) {

        this.name = name;
        this.region = region;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
        this.tz_id = tz_id;
        this.localtime_epoch = localtime_epoch;
        this.localtime = localtime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getTz_id() {
        return tz_id;
    }

    public void setTz_id(String tz_id) {
        this.tz_id = tz_id;
    }

    public int getLocaltime_epoch() {
        return localtime_epoch;
    }

    public void setLocaltime_epoch(int localtime_epoch) {
        this.localtime_epoch = localtime_epoch;
    }

    public String getLocaltime() {
        return localtime;
    }

    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }
}
```

# # So in this we also integrate Forecast System and also integrate UI

## Controller

```java
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
```

## For DTO

```java
// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
public class Astro{
    public String sunrise;
    public String sunset;
    public String moonrise;
    public String moonset;
    public String moon_phase;
    public int moon_illumination;
    public int is_moon_up;
    public int is_sun_up;
}

public class Condition{
    public String text;
    public String icon;
    public int code;
}

public class Current{
    public int last_updated_epoch;
    public String last_updated;
    public double temp_c;
    public double temp_f;
    public int is_day;
    public Condition condition;
    public double wind_mph;
    public double wind_kph;
    public int wind_degree;
    public String wind_dir;
    public double pressure_mb;
    public double pressure_in;
    public double precip_mm;
    public double precip_in;
    public int humidity;
    public int cloud;
    public double feelslike_c;
    public double feelslike_f;
    public double windchill_c;
    public double windchill_f;
    public double heatindex_c;
    public double heatindex_f;
    public double dewpoint_c;
    public double dewpoint_f;
    public double vis_km;
    public double vis_miles;
    public double uv;
    public double gust_mph;
    public double gust_kph;
    public int short_rad;
    public int diff_rad;
    public int dni;
    public int gti;
}

public class Day{
    public double maxtemp_c;
    public double maxtemp_f;
    public double mintemp_c;
    public double mintemp_f;
    public double avgtemp_c;
    public double avgtemp_f;
    public double maxwind_mph;
    public double maxwind_kph;
    public double totalprecip_mm;
    public double totalprecip_in;
    public double totalsnow_cm;
    public double avgvis_km;
    public double avgvis_miles;
    public int avghumidity;
    public int daily_will_it_rain;
    public int daily_chance_of_rain;
    public int daily_will_it_snow;
    public int daily_chance_of_snow;
    public Condition condition;
    public double uv;
}

public class Forecast{
    public ArrayList<Forecastday> forecastday;
}

public class Forecastday{
    public String date;
    public int date_epoch;
    public Day day;
    public Astro astro;
    public ArrayList<Hour> hour;
}

public class Hour{
    public int time_epoch;
    public String time;
    public double temp_c;
    public double temp_f;
    public int is_day;
    public Condition condition;
    public double wind_mph;
    public double wind_kph;
    public int wind_degree;
    public String wind_dir;
    public double pressure_mb;
    public double pressure_in;
    public double precip_mm;
    public double precip_in;
    public double snow_cm;
    public int humidity;
    public int cloud;
    public double feelslike_c;
    public double feelslike_f;
    public double windchill_c;
    public double windchill_f;
    public double heatindex_c;
    public double heatindex_f;
    public double dewpoint_c;
    public double dewpoint_f;
    public int will_it_rain;
    public int chance_of_rain;
    public int will_it_snow;
    public int chance_of_snow;
    public double vis_km;
    public double vis_miles;
    public double gust_mph;
    public double gust_kph;
    public double uv;
    public double short_rad;
    public double diff_rad;
    public double dni;
    public double gti;
}

public class Location{
    public String name;
    public String region;
    public String country;
    public double lat;
    public double lon;
    public String tz_id;
    public int localtime_epoch;
    public String localtime;
}

public class Root{
    public Location location;
    public Current current;
    public Forecast forecast;
}

```

- This will be DTO Make Getter , Setter and Constructor for all Classes

## Service

```java
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

```

## Application.properties

```java
spring.application.name=Weather-App

weather.api.key=ec0fdff73c63461fbc6100915262302
weather.api.url=http://api.weatherapi.com/v1/current.json
weather.api.forcast.url=http://api.weatherapi.com/v1/forecast.json
```

## UI

### HTML

```java
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Weather Dashboard</title>
    <link rel="stylesheet" href="styles.css" />
  </head>
  <body>
    <div class="weather-container">
      <!-- Search Form -->
      <div class="search-section">
        <form id="weather-form" class="search-form">
          <div class="form-group">
            <label for="city-input">City</label>
            <input
              type="text"
              id="city-input"
              placeholder="Enter city name"
              value="Agra"
              required
            />
          </div>
          <div class="form-group">
            <label for="days-input">Forecast Days</label>
            <select id="days-input" required>
              <option value="3">3 Days</option>
              <option value="5">5 Days</option>
              <option value="7" selected>7 Days</option>
              <option value="10">10 Days</option>
            </select>
          </div>
          <button type="submit" class="search-btn">Get Weather</button>
        </form>
      </div>

      <div id="weather-content" class="welcome">
        <div class="welcome-message">
          <h2>🌤️ Weather Dashboard</h2>
          <p>Enter a city name and select forecast days to get started!</p>
        </div>
      </div>
    </div>

    <script src="script.js"></script>
  </body>
</html>

```

### CSS

```java
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  background: linear-gradient(135deg, #2c3e50 0%, #34495e 100%);
  min-height: 100vh;
  color: #ecf0f1;
  padding: 20px;
}

.weather-container {
  max-width: 900px;
  margin: 0 auto;
  background: rgba(0, 0, 0, 0.3);
  backdrop-filter: blur(10px);
  border-radius: 20px;
  padding: 30px;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.3);
  border: 1px solid rgba(255, 255, 255, 0.1);
  position: relative;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 30px;
}

.location-info h1 {
  font-size: 1.2rem;
  font-weight: 400;
  margin-bottom: 5px;
  opacity: 0.8;
}

.location-info .city {
  font-size: 1.5rem;
  font-weight: 600;
}

.weather-title {
  text-align: right;
}

.weather-title h2 {
  font-size: 2rem;
  font-weight: 300;
  margin-bottom: 5px;
}

.weather-title .time {
  font-size: 1rem;
  opacity: 0.7;
}

.main-weather {
  display: flex;
  align-items: center;
  gap: 30px;
  margin-bottom: 40px;
}

.weather-icon {
  width: 120px;
  height: 120px;
  filter: drop-shadow(0 4px 8px rgba(0, 0, 0, 0.3));
}

.temperature-info {
  flex: 1;
}

.temperature {
  font-size: 4rem;
  font-weight: 300;
  line-height: 1;
  margin-bottom: 10px;
}

.weather-details {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 20px;
  margin-bottom: 30px;
}

.detail-item {
  background: rgba(255, 255, 255, 0.1);
  padding: 15px;
  border-radius: 10px;
  border: 1px solid rgba(255, 255, 255, 0.1);
}

.detail-item .label {
  font-size: 0.9rem;
  opacity: 0.7;
  margin-bottom: 5px;
}

.detail-item .value {
  font-size: 1.1rem;
  font-weight: 500;
}

.forecast-section {
  margin-top: 40px;
}

.section-title {
  font-size: 1.1rem;
  margin-bottom: 20px;
  opacity: 0.8;
  font-weight: 500;
}

.forecast-container {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(120px, 1fr));
  gap: 15px;
}

.forecast-day {
  background: rgba(255, 255, 255, 0.1);
  border-radius: 15px;
  padding: 20px 15px;
  text-align: center;
  border: 1px solid rgba(255, 255, 255, 0.1);
  transition:
    transform 0.2s ease,
    background 0.2s ease;
}

.forecast-day:hover {
  transform: translateY(-5px);
  background: rgba(255, 255, 255, 0.15);
}

.forecast-day .day {
  font-size: 0.9rem;
  font-weight: 500;
  margin-bottom: 10px;
}

.forecast-day .forecast-icon {
  width: 40px;
  height: 40px;
  margin: 0 auto 10px;
  filter: drop-shadow(0 2px 4px rgba(0, 0, 0, 0.3));
}

.forecast-day .temp-range {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 0.9rem;
}

.temp-max {
  font-weight: 600;
}

.temp-min {
  opacity: 0.7;
}

.loading {
  text-align: center;
  padding: 40px;
  font-size: 1.1rem;
  opacity: 0.7;
}

.error {
  text-align: center;
  padding: 40px;
  color: #e74c3c;
  font-size: 1.1rem;
}

.error button {
  margin-top: 20px;
  padding: 10px 20px;
  background: rgba(255, 255, 255, 0.1);
  border: 1px solid rgba(255, 255, 255, 0.2);
  color: #ecf0f1;
  border-radius: 5px;
  cursor: pointer;
  transition: all 0.2s ease;
}

.error button:hover {
  background: rgba(255, 255, 255, 0.2);
  transform: translateY(-1px);
}

/* Search Form Styles */
.search-section {
  background: rgba(255, 255, 255, 0.1);
  border-radius: 15px;
  padding: 25px;
  margin-bottom: 30px;
  border: 1px solid rgba(255, 255, 255, 0.2);
}

.search-form {
  display: grid;
  grid-template-columns: 1fr 150px auto;
  gap: 20px;
  align-items: end;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.form-group label {
  font-size: 0.9rem;
  font-weight: 500;
  opacity: 0.9;
}

.form-group input,
.form-group select {
  padding: 12px 15px;
  border: 1px solid rgba(255, 255, 255, 0.3);
  background: rgba(255, 255, 255, 0.1);
  border-radius: 8px;
  color: #ecf0f1;
  font-size: 1rem;
  transition: all 0.2s ease;
  backdrop-filter: blur(5px);
}

.form-group input:focus,
.form-group select:focus {
  outline: none;
  border-color: #3498db;
  background: rgba(255, 255, 255, 0.15);
  box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.2);
}

.form-group input::placeholder {
  color: rgba(236, 240, 241, 0.6);
}

.form-group select option {
  background: #34495e;
  color: #ecf0f1;
}

.search-btn {
  padding: 12px 25px;
  background: linear-gradient(135deg, #3498db, #2980b9);
  border: none;
  border-radius: 8px;
  color: white;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s ease;
  height: fit-content;
  box-shadow: 0 4px 15px rgba(52, 152, 219, 0.3);
}

.search-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(52, 152, 219, 0.4);
}

.search-btn:active {
  transform: translateY(0);
}

.search-btn:disabled {
  opacity: 0.6;
  cursor: not-allowed;
  transform: none;
}

/* Welcome Message */
.welcome {
  text-align: center;
  padding: 60px 20px;
}

.welcome-message h2 {
  font-size: 2.5rem;
  margin-bottom: 15px;
  background: linear-gradient(135deg, #3498db, #2ecc71);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.welcome-message p {
  font-size: 1.2rem;
  opacity: 0.8;
  margin-bottom: 20px;
}

/* Refresh Button for Weather Display */
.refresh-btn {
  position: absolute;
  top: 30px;
  right: 30px;
  background: rgba(255, 255, 255, 0.1);
  border: 1px solid rgba(255, 255, 255, 0.2);
  color: #ecf0f1;
  padding: 10px 20px;
  border-radius: 8px;
  cursor: pointer;
  font-size: 0.9rem;
  transition: all 0.2s ease;
  z-index: 10;
}

.refresh-btn:hover {
  background: rgba(255, 255, 255, 0.2);
  transform: translateY(-1px);
}

/* Responsive Design */
@media (max-width: 768px) {
  body {
    padding: 10px;
  }

  .weather-container {
    padding: 20px;
  }

  .search-form {
    grid-template-columns: 1fr;
    gap: 15px;
  }

  .main-weather {
    flex-direction: column;
    text-align: center;
  }

  .header {
    flex-direction: column;
    text-align: center;
    gap: 20px;
  }

  .weather-title {
    text-align: center;
  }

  .temperature {
    font-size: 3rem;
  }

  .forecast-container {
    grid-template-columns: repeat(auto-fit, minmax(100px, 1fr));
  }

  .weather-details {
    grid-template-columns: 1fr;
  }

  .refresh-btn {
    position: relative;
    top: 0;
    right: 0;
    margin-bottom: 20px;
    display: block;
    width: 100%;
  }

  .welcome-message h2 {
    font-size: 2rem;
  }

  .welcome-message p {
    font-size: 1rem;
  }
}

```

### JS

```java
// Weather Icons Configuration
const weatherIcons = {
  sunny: `<svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
        <circle cx="50" cy="50" r="20" fill="#FFD700" stroke="#FFA500" stroke-width="2"/>
        <g stroke="#FFD700" stroke-width="3" stroke-linecap="round">
            <line x1="50" y1="10" x2="50" y2="20"/>
            <line x1="50" y1="80" x2="50" y2="90"/>
            <line x1="10" y1="50" x2="20" y2="50"/>
            <line x1="80" y1="50" x2="90" y2="50"/>
            <line x1="21.21" y1="21.21" x2="28.28" y2="28.28"/>
            <line x1="71.72" y1="71.72" x2="78.79" y2="78.79"/>
            <line x1="78.79" y1="21.21" x2="71.72" y2="28.28"/>
            <line x1="28.28" y1="71.72" x2="21.21" y2="78.79"/>
        </g>
    </svg>`,

  "partly-cloudy": `<svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
        <circle cx="35" cy="35" r="15" fill="#FFD700" stroke="#FFA500" stroke-width="1.5"/>
        <g stroke="#FFD700" stroke-width="2" stroke-linecap="round">
            <line x1="35" y1="8" x2="35" y2="15"/>
            <line x1="8" y1="35" x2="15" y2="35"/>
            <line x1="18.93" y1="18.93" x2="23.64" y2="23.64"/>
            <line x1="55" y1="35" x2="62" y2="35"/>
            <line x1="46.36" y1="23.64" x2="51.07" y2="18.93"/>
        </g>
        <path d="M45 55 Q38 45 28 45 Q18 45 18 55 Q18 65 28 65 L65 65 Q75 65 75 55 Q75 45 65 45 Q58 45 55 50 Q52 45 45 45 Q45 50 45 55 Z" 
              fill="#E0E0E0" stroke="#CCCCCC" stroke-width="1"/>
    </svg>`,

  cloudy: `<svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
        <path d="M25 55 Q18 45 28 45 Q38 45 45 55 Q52 45 55 45 Q65 45 65 55 Q75 45 75 55 Q75 65 65 65 L28 65 Q18 65 18 55 Z" 
              fill="#E0E0E0" stroke="#CCCCCC" stroke-width="2"/>
        <path d="M35 45 Q28 35 38 35 Q48 35 55 45 Q62 35 65 35 Q75 35 75 45 Q75 55 65 55 L38 55 Q28 55 28 45 Z" 
              fill="#F0F0F0" stroke="#DDDDDD" stroke-width="1"/>
    </svg>`,

  rainy: `<svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
        <path d="M25 45 Q18 35 28 35 Q38 35 45 45 Q52 35 55 35 Q65 35 65 45 Q75 35 75 45 Q75 55 65 55 L28 55 Q18 55 18 45 Z" 
              fill="#8E8E93" stroke="#6D6D70" stroke-width="2"/>
        <g stroke="#4A90E2" stroke-width="2" stroke-linecap="round">
            <line x1="30" y1="65" x2="32" y2="75"/>
            <line x1="40" y1="60" x2="42" y2="70"/>
            <line x1="50" y1="65" x2="52" y2="75"/>
            <line x1="60" y1="60" x2="62" y2="70"/>
            <line x1="35" y1="70" x2="37" y2="80"/>
            <line x1="55" y1="70" x2="57" y2="80"/>
        </g>
    </svg>`,

  stormy: `<svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
        <path d="M25 40 Q18 30 28 30 Q38 30 45 40 Q52 30 55 30 Q65 30 65 40 Q75 30 75 40 Q75 50 65 50 L28 50 Q18 50 18 40 Z" 
              fill="#4A4A4A" stroke="#333333" stroke-width="2"/>
        <path d="M48 55 L42 70 L50 70 L45 85 L58 65 L50 65 L55 55 Z" 
              fill="#FFD700" stroke="#FFA500" stroke-width="1"/>
        <g stroke="#4A90E2" stroke-width="2" stroke-linecap="round">
            <line x1="25" y1="60" x2="27" y2="70"/>
            <line x1="35" y1="65" x2="37" y2="75"/>
            <line x1="65" y1="60" x2="67" y2="70"/>
        </g>
    </svg>`,
};

// API Configuration
const API_CONFIG = {
  baseUrl: "http://localhost:8080",
  endpoint: "/weather/forecast",
};

// Current search parameters
let currentSearch = {
  city: "",
  days: 7,
};

/**
 * Get appropriate weather icon based on condition
 * @param {string} condition - Weather condition description
 * @returns {string} SVG icon string
 */
function getWeatherIcon(condition) {
  const conditionLower = condition.toLowerCase();

  if (conditionLower.includes("sunny") || conditionLower.includes("clear")) {
    return weatherIcons.sunny;
  } else if (
    conditionLower.includes("partly") ||
    conditionLower.includes("partial")
  ) {
    return weatherIcons["partly-cloudy"];
  } else if (
    conditionLower.includes("rain") ||
    conditionLower.includes("shower") ||
    conditionLower.includes("drizzle")
  ) {
    return weatherIcons.rainy;
  } else if (
    conditionLower.includes("storm") ||
    conditionLower.includes("thunder")
  ) {
    return weatherIcons.stormy;
  } else if (
    conditionLower.includes("cloud") ||
    conditionLower.includes("overcast")
  ) {
    return weatherIcons.cloudy;
  } else {
    return weatherIcons["partly-cloudy"]; // Default fallback
  }
}

/**
 * Format date string to day abbreviation
 * @param {string} dateString - Date in YYYY-MM-DD format
 * @returns {string} Day abbreviation (Mon, Tue, etc.)
 */
function formatDate(dateString) {
  const date = new Date(dateString);
  const days = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
  return days[date.getDay()];
}

/**
 * Get current time formatted for display
 * @returns {string} Formatted current time
 */
function getCurrentTime() {
  const now = new Date();
  return now.toLocaleString("en-US", {
    weekday: "long",
    hour: "2-digit",
    minute: "2-digit",
    hour12: true,
  });
}

/**
 * Render weather data to the DOM
 * @param {Object} data - Weather data from API
 */
function renderWeatherData(data) {
  const { weatherResponse, dayTemp } = data;

  const content = `
        <button class="refresh-btn" onclick="refreshCurrentSearch()">Refresh</button>
        
        <div class="header">
            <div class="location-info">
                <h1>Results for <span class="city">${weatherResponse.city}, ${weatherResponse.region}</span></h1>
            </div>
            <div class="weather-title">
                <h2>Weather</h2>
                <div class="time">${getCurrentTime()}</div>
                <div class="time">${weatherResponse.condition}</div>
            </div>
        </div>

        <div class="main-weather">
            <div class="weather-icon">${getWeatherIcon(weatherResponse.condition)}</div>
            <div class="temperature-info">
                <div class="temperature">${Math.round(weatherResponse.temperature)}°<span style="font-size: 0.6em;">C</span></div>
            </div>
        </div>

        <div class="weather-details">
            <div class="detail-item">
                <div class="label">Location</div>
                <div class="value">${weatherResponse.city}, ${weatherResponse.country}</div>
            </div>
            <div class="detail-item">
                <div class="label">Condition</div>
                <div class="value">${weatherResponse.condition}</div>
            </div>
            <div class="detail-item">
                <div class="label">Region</div>
                <div class="value">${weatherResponse.region}</div>
            </div>
        </div>

        <div class="forecast-section">
            <div class="section-title">${dayTemp.length}-Day Forecast</div>
            <div class="forecast-container">
                ${dayTemp
                  .map((day, index) => {
                    const dayName =
                      index === 0 ? "Today" : formatDate(day.date);
                    return `
                        <div class="forecast-day">
                            <div class="day">${dayName}</div>
                            <div class="forecast-icon">${getWeatherIcon(weatherResponse.condition)}</div>
                            <div class="temp-range">
                                <span class="temp-max">${Math.round(day.maxTemp)}°</span>
                                <span class="temp-min">${Math.round(day.minTemp)}°</span>
                            </div>
                        </div>
                    `;
                  })
                  .join("")}
            </div>
        </div>
    `;

  document.getElementById("weather-content").innerHTML = content;
}

/**
 * Show error message in the UI
 * @param {string} message - Error message to display
 */
function showError(message) {
  document.getElementById("weather-content").innerHTML = `
        <div class="error">
            <h3>Error loading weather data</h3>
            <p>${message}</p>
            <button onclick="loadWeatherData()">Try Again</button>
        </div>
    `;
}

/**
 * Show loading state in the UI
 */
function showLoading() {
  document.getElementById("weather-content").innerHTML =
    '<div class="loading">Loading weather data...</div>';
}

/**
 * Build API URL with parameters
 * @param {string} city - City name
 * @param {number} days - Number of forecast days
 * @returns {string} Complete API URL
 */
function buildApiUrl(city, days) {
  const params = new URLSearchParams({
    city: city,
    days: days,
  });
  return `${API_CONFIG.baseUrl}${API_CONFIG.endpoint}?${params.toString()}`;
}

/**
 * Validate form inputs
 * @param {string} city - City name
 * @param {number} days - Number of days
 * @returns {Object} Validation result
 */
function validateInputs(city, days) {
  const errors = [];

  if (!city || city.trim().length === 0) {
    errors.push("City name is required");
  }

  if (!days || days < 1 || days > 14) {
    errors.push("Days must be between 1 and 14");
  }

  return {
    isValid: errors.length === 0,
    errors: errors,
  };
}

/**
 * Load weather data from API
 * @param {string} city - City name
 * @param {number} days - Number of forecast days
 */
async function loadWeatherData(city, days) {
  try {
    // Validate inputs
    const validation = validateInputs(city, days);
    if (!validation.isValid) {
      throw new Error(validation.errors.join(", "));
    }

    showLoading();

    // Update current search parameters
    currentSearch = { city: city.trim(), days: parseInt(days) };

    const apiUrl = buildApiUrl(currentSearch.city, currentSearch.days);
    console.log("Fetching weather data from:", apiUrl);

    // Disable form during request
    setFormEnabled(false);

    const response = await fetch(apiUrl);

    if (!response.ok) {
      throw new Error(
        `HTTP error! status: ${response.status} - ${response.statusText}`,
      );
    }

    const data = await response.json();
    console.log("Weather data received:", data);

    // Validate required data structure
    if (!data.weatherResponse || !data.dayTemp) {
      throw new Error("Invalid API response structure");
    }

    renderWeatherData(data);
  } catch (error) {
    console.error("Error fetching weather data:", error);

    let errorMessage = "Unable to fetch weather data. ";

    if (
      error.message.includes("fetch") ||
      error.message.includes("NetworkError")
    ) {
      errorMessage +=
        "Please check if the weather service is running on localhost:8080.";
    } else if (error.message.includes("HTTP error")) {
      errorMessage += `Server responded with error: ${error.message}`;
    } else {
      errorMessage += error.message;
    }

    showError(errorMessage);
  } finally {
    // Re-enable form
    setFormEnabled(true);
  }
}

/**
 * Refresh current search
 */
function refreshCurrentSearch() {
  if (currentSearch.city) {
    loadWeatherData(currentSearch.city, currentSearch.days);
  }
}

/**
 * Handle form submission
 * @param {Event} event - Form submit event
 */
function handleFormSubmit(event) {
  event.preventDefault();

  const cityInput = document.getElementById("city-input");
  const daysInput = document.getElementById("days-input");

  const city = cityInput.value.trim();
  const days = parseInt(daysInput.value);

  loadWeatherData(city, days);
}

/**
 * Enable/disable form inputs
 * @param {boolean} enabled - Whether form should be enabled
 */
function setFormEnabled(enabled) {
  const form = document.getElementById("weather-form");
  const inputs = form.querySelectorAll("input, select, button");

  inputs.forEach((input) => {
    input.disabled = !enabled;
  });
}

/**
 * Initialize the application
 */
function initializeApp() {
  console.log("Weather Dashboard initializing...");

  // Set up form event listener
  const form = document.getElementById("weather-form");
  if (form) {
    form.addEventListener("submit", handleFormSubmit);
  }

  // Focus on city input
  const cityInput = document.getElementById("city-input");
  if (cityInput) {
    cityInput.focus();
  }
}

// Event Listeners
document.addEventListener("DOMContentLoaded", initializeApp);

// Global error handler for unhandled promise rejections
window.addEventListener("unhandledrejection", function (event) {
  console.error("Unhandled promise rejection:", event.reason);
  showError("An unexpected error occurred. Please refresh the page.");
});

// Expose functions to global scope for button onclick events
window.refreshCurrentSearch = refreshCurrentSearch;

```