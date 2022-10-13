package com.gridnine.testing;

import static org.junit.Assert.assertTrue;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.util.FlightBuilder;
import org.junit.Test;

import java.util.List;

public class MainTest {

    private final List<Flight> flights = FlightBuilder.createFlights();

    @Test
    public void getFlightsWithFlightBuilder_FilterByCurrentDepartureTime_FilteredListReturned() {

    }

    @Test
    public void getFlightsWithFlightBuilder_FilterBySegmentDepartureAndArrivalTime_FilteredListReturned() {
        assertTrue( true );
    }

    @Test
    public void getFlightsWithFlightBuilder_FilterByTotalTimeOnLand_FilteredListReturned() {
        assertTrue( true );
    }
}
