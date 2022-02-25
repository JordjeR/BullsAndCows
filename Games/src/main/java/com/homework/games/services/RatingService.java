package com.homework.games.services;

import com.homework.games.entities.Rating;

import java.util.HashMap;
import java.util.List;

public interface RatingService {
    List<Rating> getAll();

    Integer randomFourDigitalNumber();

    HashMap<Integer, List<Integer>> sepDigital(Integer input);

    List<Integer> randomUntilDifferentNumber();

    String untilYouGuess(List<Integer> randomNumber, List<Integer> userInput);

    boolean isSymmetric(List<Integer> list1, List<Integer> list2);

    void add(Rating rating);

    void deleteAll();
}
