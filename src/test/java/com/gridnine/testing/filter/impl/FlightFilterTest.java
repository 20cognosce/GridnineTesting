package com.gridnine.testing.filter.impl;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.util.FlightBuilder;
import com.gridnine.testing.util.filter.predicate.factory.FlightFilterPredicateFactory;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Assert;

public class FlightFilterTest {

    private final Filter<Flight> flightFilter = new FlightFilter();
    private final List<Flight> flights = FlightBuilder.createFlights();

    @Test
    public void filter_PredicateForDepartureIsBeforeNow_3rdFlightReturned() {
        Predicate<Flight> predicate = FlightFilterPredicateFactory.getPredicateForDepartureIsBefore(LocalDateTime.now());
        List<Flight> result = flightFilter.filter(flights, predicate);

        Assert.assertEquals(result.get(0), flights.get(2));
    }

    @Test
    public void filter_PredicateForContainingASegmentWithDepartureIsBeforeArrival_4thFlightReturned() {
        Predicate<Flight> predicate = FlightFilterPredicateFactory.getPredicateForContainingASegmentWithDepartureIsBeforeArrival();
        List<Flight> result = flightFilter.filter(flights, predicate);

        Assert.assertEquals(result.get(0), flights.get(3));
    }

    @Test
    public void filter_PredicateForTotalTimeOnLandMoreThan2Hours_5thAnd6thFlightsReturned() {
        Predicate<Flight> predicate = FlightFilterPredicateFactory.getPredicateForTotalTimeOnLandMoreThan(Duration.ofHours(2));
        List<Flight> result = flightFilter.filter(flights, predicate);

        Assert.assertEquals(result.get(0), flights.get(4));
        Assert.assertEquals(result.get(1), flights.get(5));
    }
}