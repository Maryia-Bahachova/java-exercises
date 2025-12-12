package com.epam.autotasks;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CatContestAnalyzer {

    public static final Integer DEFAULT_VALUE = -1;

    public Integer getMaxResult(List<Cat> cats) {
        if (cats == null || cats.isEmpty()) return DEFAULT_VALUE;

        return cats.stream()
                .filter(Objects::nonNull)
                .map(Cat::getContestResult)
                .filter(Objects::nonNull)
                .map(ContestResult::getSum)
                .filter(Objects::nonNull)
                .max(Integer::compareTo)
                .orElse(DEFAULT_VALUE);
    }

    public Integer getMinResult(List<Cat> cats) {
        if (cats == null || cats.isEmpty()) return DEFAULT_VALUE;

        return cats.stream()
                .filter(Objects::nonNull)
                .map(Cat::getContestResult)
                .filter(Objects::nonNull)
                .map(ContestResult::getSum)
                .filter(sum -> sum != null && sum > 0)
                .min(Integer::compareTo)
                .orElse(DEFAULT_VALUE);
    }

    public OptionalDouble getAverageResultByBreed(List<Cat> cats, Cat.Breed breed) {
        if (cats == null || cats.isEmpty() || breed == null) return OptionalDouble.empty();

        return cats.stream()
                .filter(Objects::nonNull)
                .filter(cat -> breed.equals(cat.getBreed()))
                .map(Cat::getContestResult)
                .filter(Objects::nonNull)
                .map(ContestResult::getSum)
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .average();
    }

    public Optional<Cat> getWinner(List<Cat> cats) {
        if (cats == null || cats.isEmpty()) return Optional.empty();

        return cats.stream()
                .filter(Objects::nonNull)
                .filter(c -> {
                    ContestResult r = c.getContestResult();
                    return r != null &&
                            r.getRunning() != null && r.getRunning() > 0 &&
                            r.getJumping() != null && r.getJumping() > 0 &&
                            r.getPurring() != null && r.getPurring() > 0;
                })
                .max(Comparator.comparingInt(c -> c.getContestResult().getSum() == null ? 0 : c.getContestResult().getSum()));
    }

    public List<Cat> getThreeLeaders(List<Cat> cats) {
        if (cats == null || cats.isEmpty()) return new ArrayList<>();

        return cats.stream()
                .filter(Objects::nonNull)
                .filter(c -> {
                    ContestResult r = c.getContestResult();
                    return r != null
                            && r.getRunning() != null && r.getRunning() > 0
                            && r.getJumping() != null && r.getJumping() > 0
                            && r.getPurring() != null && r.getPurring() > 0
                            && r.getSum() != null && r.getSum() > 0;
                })
                .sorted(Comparator.comparingInt((Cat c) -> c.getContestResult().getSum()).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public boolean validateResultSumNotNull(List<Cat> cats) {
        if (cats == null) return false;

        return cats.stream()
                .filter(Objects::nonNull)
                .map(Cat::getContestResult)
                .allMatch(r -> r != null && r.getSum() != null && r.getSum() > 0);
    }

    public boolean validateAllResultsSet(List<Cat> cats) {
        if (cats == null) return false;

        return cats.stream()
                .filter(Objects::nonNull)
                .map(Cat::getContestResult)
                .allMatch(r -> r != null &&
                        r.getRunning() != null && r.getRunning() > 0 &&
                        r.getJumping() != null && r.getJumping() > 0 &&
                        r.getPurring() != null && r.getPurring() > 0);
    }

    public Optional<Cat> findAnyWithAboveAverageResultByBreed(List<Cat> cats, Cat.Breed breed) {
        if (cats == null || cats.isEmpty() || breed == null) return Optional.empty();

        OptionalDouble averageOpt = getAverageResultByBreed(cats, breed);
        if (averageOpt.isEmpty()) return Optional.empty();

        double average = averageOpt.getAsDouble();

        return cats.stream()
                .filter(Objects::nonNull)
                .filter(c -> breed.equals(c.getBreed()))
                .filter(c -> {
                    ContestResult r = c.getContestResult();
                    return r != null && r.getSum() != null && r.getSum() > average;
                })
                .findAny();
    }
}