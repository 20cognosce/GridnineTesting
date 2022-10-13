package com.gridnine.testing.filter;

import java.util.List;
import java.util.function.Predicate;

public interface Filter<T> {

    /**
     * @param listToFilter The list of entities we want to filter
     * @param predicate The predicate for filtration of the list
     * @return The list filtered according to the predicate
     */
    List<T> filter(List<T> listToFilter, Predicate<T> predicate);
}
