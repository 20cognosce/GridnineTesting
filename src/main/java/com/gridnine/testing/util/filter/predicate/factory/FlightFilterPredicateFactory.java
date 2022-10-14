package com.gridnine.testing.util.filter.predicate.factory;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Dmitry Vert
 * @since 13.10.2022
 * <p>
 * The utility class that hold predefined predicates for filtering flights.
 * Can always be extended with new predicates if there are frequently used.
 * </p>
 * <b>NOTE:</b> If adding a predicate seems excessive, it can be simply replaced with lambda.
 */
public class FlightFilterPredicateFactory {

    public static Predicate<Flight> getPredicateForDepartureIsBefore(LocalDateTime timeStamp) {
        return flight -> flight.getSegments().get(0).getDepartureDate().isBefore(timeStamp);
    }

    //TODO: the case when arrival equals to departure is not handled in this predicate (but should be)
    public static Predicate<Flight> getPredicateForContainingASegmentWithArrivalIsBeforeDeparture() {
        return flight -> flight.getSegments().stream()
                .anyMatch(s -> s.getArrivalDate().isBefore(s.getDepartureDate()));
    }

    public static Predicate<Flight> getPredicateForTotalTimeOnLandMoreThan(Duration maximumTimeOnLand) {
        return flight -> {
            Duration summarizedTimeOnLand = Duration.ZERO;
            List<Segment> segments = flight.getSegments();

            for (int i = 0; i < segments.size() - 1; i++) {
                Duration intermediateTimeOnLand = Duration.between(
                        segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate());
                summarizedTimeOnLand = summarizedTimeOnLand.plus(intermediateTimeOnLand);
            }

            return summarizedTimeOnLand.compareTo(maximumTimeOnLand) > 0;
        };
    }
}
