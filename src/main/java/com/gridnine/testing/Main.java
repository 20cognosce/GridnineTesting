package com.gridnine.testing;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.filter.impl.FlightFilter;
import com.gridnine.testing.util.FlightBuilder;
import com.gridnine.testing.util.filter.predicate.factory.FlightFilterPredicateFactory;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Dmitry Vert
 * @since 13.10.2022
 */

public class Main {

    //TODO: do not forget to answer theoretical questions
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        flights.forEach(System.out::println);

        Filter<Flight> flightFilter = new FlightFilter();
        /*
        В ТЗ написано в выводе исключить то, что подпадает под фильтр. Соответственно если наоборот нужно вывести то,
        что под фильтр-таки попадает, то просто убираем Predicate.not()

        "Правила могут выбираться и задаваться динамически в зависимости от контекста выполнения операции фильтрации" -
        выбрать можно из заранее прописанных предикатов в FilterPredicateFactory, а задать правило динамически можно
        передав в аргумент лямбду (тот же предикат).
        */

        System.out.println("\n#####################################################################################\n" +
                "Вылет до текущего момента времени\n");
        Predicate<Flight> predicate1 = FlightFilterPredicateFactory.getPredicateForDepartureIsBefore(LocalDateTime.now());
        flightFilter.filter(flights, Predicate.not(predicate1)).forEach(System.out::println);
        //flightFilter.filter(flights, Predicate.not(flight -> flight.getSegments().get(0).getDepartureDate().isBefore(LocalDateTime.now())));

        System.out.println("\n#####################################################################################\n" +
                "Имеются сегменты с датой прилёта раньше даты вылета\n");
        Predicate<Flight> predicate2 = FlightFilterPredicateFactory.getPredicateForContainingASegmentWithArrivalIsBeforeDeparture();
        flightFilter.filter(flights, Predicate.not(predicate2)).forEach(System.out::println);

        System.out.println("\n#####################################################################################\n" +
                "Общее время, проведённое на земле превышает два часа\n");
        Predicate<Flight> predicate3 = FlightFilterPredicateFactory.getPredicateForTotalTimeOnLandMoreThan(Duration.ofHours(2));
        flightFilter.filter(flights, Predicate.not(predicate3)).forEach(System.out::println);
    }
}
