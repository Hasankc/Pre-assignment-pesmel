package com.example.Preassignmentpesmel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Preassignmentpesmel.entities.Weather;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    /**
     * Custom native query to extract minimum temperature in all days that were recorded
     * The query is native to PostgresSQL!
     *
     * @return list of min temperatures recorded per day
     */
    @Query(value = "select MIN(w.temperature), date(w.timestamp) " +
            "from weather w " +
            "group by date(w.timestamp) " +
            "order by date(w.timestamp)", nativeQuery = true)
    List<Object[]> minPerDayInAllDays();

    @Query(value = "select min(w.temperature)  " +
            "from weather w \n" +
            "where date(w.timestamp) = ?1", nativeQuery = true)
    List<Double> minPerGivenDay(LocalDate localDate);

    @Query(value = "select MAX(w.temperature), date(w.timestamp) " +
            "from weather w " +
            "group by date(w.timestamp) " +
            "order by date(w.timestamp)", nativeQuery = true)
    List<Object[]> maxPerDayInAllDays();

    @Query(value = "select max(w.temperature)  " +
            "from weather w \n" +
            "where date(w.timestamp) = ?1", nativeQuery = true)
    List<Double> maxPerGivenDay(LocalDate localDate);


    @Query(value = "select AVG(w.temperature), date(w.timestamp) " +
            "from weather w " +
            "group by date(w.timestamp) " +
            "order by date(w.timestamp)", nativeQuery = true)
    List<Object[]> avgPerDayInAllDays();

    @Query(value = "select avg(w.temperature)  " +
            "from weather w \n" +
            "where date(w.timestamp) = ?1", nativeQuery = true)
    List<Double> avgPerGivenDay(LocalDate localDate);

    @Query(value = "select MIN(w.temperature) from weather w " +
            "where extract(week from w.timestamp) = ?1 and extract(YEAR from w.timestamp) = ?2", nativeQuery = true)
    List<Double> minPerGivenWeekOfYear(Integer weeknumber, Integer year);

    @Query(value = "select MIN(w.temperature) from weather w " +
            "where extract(YEAR from w.timestamp) = ?1 " +
            "group by extract(week  from w.timestamp), extract(YEAR from w.timestamp) " +
            "order by extract(YEAR from w.timestamp) asc, extract(week from w.timestamp) asc", nativeQuery = true)
    List<Double> minForAllWeeksOfYear(Integer year);


    @Query(value = "select MIN(w.temperature) from weather w " +
            "where extract(week from w.timestamp) = ?1 and extract(YEAR from w.timestamp) = ?2", nativeQuery = true)
    List<Double> maxPerGivenWeekOfYear(Integer weeknumber, Integer year);

    @Query(value = "select MIN(w.temperature) from weather w " +
            "where extract(YEAR from w.timestamp) = ?1 " +
            "group by extract(week  from w.timestamp), extract(YEAR from w.timestamp) " +
            "order by extract(YEAR from w.timestamp) asc, extract(week from w.timestamp) asc", nativeQuery = true)
    List<Double> maxForAllWeeksOfYear(Integer year);

}

