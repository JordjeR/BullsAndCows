package com.homework.games.services.impl;

import com.homework.games.entities.Rating;
import com.homework.games.repo.RatingRepository;
import com.homework.games.services.RatingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Integer randomFourDigitalNumber() {
        final int min = 1000;
        final int max = 9999;

        return (int) (Math.random() * (max - min) + min);
    }

    @Override
    public HashMap<Integer, List<Integer>> sepDigital(Integer input) {
        List<Integer> list = new ArrayList<>();

        int a, b = 0, t;

        while (input > 0) {
            a = input % 10;
            b = b * 10 + a;
            input = input / 10;
        }

        while (b > 0) {
            t = b % 10;
            list.add(t);
            b = b / 10;
        }

        HashMap<Integer, List<Integer>> map = new HashMap<>();

        List<Integer> newList = list.stream().distinct().collect(Collectors.toList());

        if (!isSymmetric(newList, list)) {
            map.put(-1, newList);
        } else {
            map.put(1, list);
        }

        return map;
    }

    @Override
    public List<Integer> randomUntilDifferentNumber() {
        int random = 0;

        while (true) {
            random = randomFourDigitalNumber();
            if (!sepDigital(random).containsKey(-1) && sepDigital(random).get(1).size() == 4) {
                return sepDigital(random).get(1);
            }
        }
    }

    @Override
    public String untilYouGuess(List<Integer> randomNumber, List<Integer> userInput) {
        int bull = 0;
        int cow = 0;

        for (int i = 0; i < randomNumber.size(); ++i) {
            for (int j = 0; j < userInput.size(); ++j) {
                if (randomNumber.get(i).equals(userInput.get(j)) && i == j) {
                    bull++;
                } else if (randomNumber.get(i).equals(userInput.get(j))) {
                    cow++;
                }
            }
        }

        if (bull == 4) {
            return "Вы угадали число!";
        }

        return " -- " + bull + "Б" + cow + "К";
    }

    @Override
    public boolean isSymmetric(List<Integer> list1, List<Integer> list2) {
        for (int i = 0; i < list1.size(); i++) {
            if (list1.equals(list2)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void add(Rating rating) {
        ratingRepository.save(rating);
    }

    @Override
    public void deleteAll() {
        ratingRepository.deleteAll();
    }
}
