package com.example.Preassignmentpesmel;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.Preassignmentpesmel.entities.Weather;
import com.example.Preassignmentpesmel.entities.WeatherCSV;
import com.example.Preassignmentpesmel.repositories.WeatherRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.val;

@Component
public class CsvProcessRunner implements CommandLineRunner {

    @Autowired
    private WeatherRepository weatherRepository;

    private static Logger logger = LoggerFactory.getLogger(CsvProcessRunner.class);

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello I will start reading CSV files");
        try {
            // load csv data
            Reader reader = Files
                    .newBufferedReader(Paths.get(ClassLoader.getSystemResource("weather_dataset.csv").toURI()));

            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(1)
                    .withCSVParser(parser)
                    .build();

            // map csv to java object
            List<WeatherCSV> weatherData = new CsvToBeanBuilder(csvReader)
                    .withType(WeatherCSV.class).build().parse();

            // map csv java object to java entities
            val weatherRecords = new ArrayList<Weather>();
            for (val weatherCSV : weatherData) {
                val timestamp = ZonedDateTime.of(
                        Integer.parseInt(weatherCSV.getYear()),
                        Integer.parseInt(weatherCSV.getMonth()),
                        Integer.parseInt(weatherCSV.getDay()),
                        Integer.parseInt(weatherCSV.getTime().split(":")[0]),
                        Integer.parseInt(weatherCSV.getTime().split(":")[1]),
                        0,
                        0,
                        ZoneId.of(weatherCSV.getTimeZone()));
                val temperature = weatherCSV.getAirTemperature();
                val weatherRecord = new Weather();
                weatherRecord.setTimestamp(timestamp);
                weatherRecord.setTemperature(temperature);
                weatherRecords.add(weatherRecord);
            }

            // save entities to database
            weatherRepository.saveAll(weatherRecords);

            logger.info("Weather data record count: {}", weatherData.size());

            logger.info("Saved to database: {} records", weatherRepository.count());
        } catch (Exception e) {
            logger.error("Sorry there was a problem reading the CSV file", e);
        }
    }

}
