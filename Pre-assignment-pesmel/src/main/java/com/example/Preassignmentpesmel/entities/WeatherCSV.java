package com.example.Preassignmentpesmel.entities;

import com.opencsv.bean.CsvBindByPosition;

import lombok.Data;

@Data
public class WeatherCSV {

    @CsvBindByPosition(position = 0)
    private String year;

    @CsvBindByPosition(position = 1)
    private String month;

    @CsvBindByPosition(position = 2)
    private String day;

    @CsvBindByPosition(position = 3)
    private String time;

    @CsvBindByPosition(position = 4)
    private String timeZone;

    @CsvBindByPosition(position = 5)
    private String cloud;

    @CsvBindByPosition(position = 6)
    private String pressure;

    // @CsvBindByPosition(position = 7)
    // private String precipitation;

    // @CsvBindByPosition(position = 8)
    // private String relative;

    // @CsvBindByPosition(position = 9)
    // private String PrecipitationIntensity;

    // @CsvBindByPosition(position = 10)
    // private double Snow;

    @CsvBindByPosition(position = 11)
    private double airTemperature;

    // @CsvBindByPosition(position = 12)
    // private String Dew;

    // @CsvBindByPosition(position = 13)
    // private String Horizontal;

    // @CsvBindByPosition(position = 14)
    // private String Wind;

    // @CsvBindByPosition(position = 15)
    // private String Gust;

    // @CsvBindByPosition(position = 16)
    // private String WindSpeed;

}
