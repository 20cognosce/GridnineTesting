package com.gridnine.testing.filter.impl;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.filter.Filter;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FlightFilter implements Filter<Flight> {

    public List<Flight> filter(List<Flight> flights, Predicate<Flight> predicate) {
        return flights.stream().filter(predicate).collect(Collectors.toList());
    }
}
