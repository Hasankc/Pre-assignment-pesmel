package com.example.Preassignmentpesmel.entities;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Weather {
    @Id
    @GeneratedValue
    private long id;

    private ZonedDateTime timestamp;

    private double temperature;

}
