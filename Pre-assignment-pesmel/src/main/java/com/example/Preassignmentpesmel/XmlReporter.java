package com.example.Preassignmentpesmel;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.Preassignmentpesmel.repositories.WeatherRepository;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import lombok.val;

@Component
public class XmlReporter {
    @Autowired
    private WeatherRepository weatherRepository;
    
    @Scheduled(fixedDelay = 1L, timeUnit = TimeUnit.MINUTES)
    public void createReport() throws StreamWriteException, DatabindException, IOException {
        val data = weatherRepository.avgPerDayInAllDays();
        XmlMapper mapper = new XmlMapper();
        mapper.writeValue(new File("avg_per_day.xml"), data);
    }

}
