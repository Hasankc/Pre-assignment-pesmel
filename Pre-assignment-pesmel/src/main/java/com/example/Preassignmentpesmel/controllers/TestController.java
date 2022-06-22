package com.example.Preassignmentpesmel.controllers;

import com.example.Preassignmentpesmel.entities.Weather;
import com.example.Preassignmentpesmel.repositories.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:9090")
public class TestController {

    @Autowired
    WeatherRepository weatherRepository;
    
    @GetMapping("/")
    public Integer helloWorldGet() {
        return 10;
    }

    @PostMapping("/")
    public String helloWorldPost() {
        return "Hello, world post!";
    }

    @GetMapping("/rawdata")
    public List<Weather> getRawData() {
        return weatherRepository.findAll();
    }

    /**
     * Example request: http://localhost:8080/min/day?localDate=2021-01-02
     * @return
     */
    @GetMapping("/min/day")
    public List<Double> getMinTemperaturePerGivenDay(
            @RequestParam("localDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate
            ) {
        return weatherRepository.minPerGivenDay(localDate);
    }


    @GetMapping("/min/day/all")
    public List<Object[]> getMinTemperaturePerAllDays() {
        return weatherRepository.minPerDayInAllDays();
    }

    /**
     * Example request: http://localhost:8080/max/day?localDate=2021-01-02
     * @return
     */
    @GetMapping("/max/day")
    public List<Double> getMaxTemperaturePerGivenDay(
            @RequestParam("localDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate
    ) {
        return weatherRepository.maxPerGivenDay(localDate);
    }
    @GetMapping("/max/day/all")
    public List<Object[]> getMaxTemperaturePerAllDays() {
        return weatherRepository.maxPerDayInAllDays();
    }

    /**
     * Example request: http://localhost:8080/avg/day?localDate=2021-01-02
     * @return
     */

    @GetMapping("/avg/day")
    public List<Double> getAvgTemperaturePerGivenDay(
            @RequestParam("localDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate
    ) {
        return weatherRepository.avgPerGivenDay(localDate);
    }
    @GetMapping("/avg/day/all")
    public List<Object[]> getAvgTemperaturePerAllDays() {
        return weatherRepository.avgPerDayInAllDays();
    }

    /**
     * Example request: http://localhost:8080/min/week?weeknumber=1&year=2021
     * @return
     */
    @GetMapping("/min/week")
    public List<Double> getMinPerGivenWeekOfYear(
            @RequestParam("weeknumber") Integer weeknumber,
            @RequestParam("year") Integer year
    ) {
        return weatherRepository.minPerGivenWeekOfYear(weeknumber, year);
    }

    @GetMapping("/min/week/all")
    public List<Double> getMinPerGivenWeekOfYear(
            @RequestParam("year") Integer year
    ) {
        return weatherRepository.minForAllWeeksOfYear(year);
    }

    /**
     * Example request: http://localhost:8080/max/week?weeknumber=1&year=2021
     * @return
     */
    @GetMapping("/max/week")
    public List<Double> getMaxPerGivenWeekOfYear(
            @RequestParam("weeknumber") Integer weeknumber,
            @RequestParam("year") Integer year
    ) {
        return weatherRepository.minPerGivenWeekOfYear(weeknumber, year);
    }

    @GetMapping("/max/week/all")
    public List<Double> getMaxPerGivenWeekOfYear(
            @RequestParam("year") Integer year
    ) {
        return weatherRepository.minForAllWeeksOfYear(year);
    }
}
