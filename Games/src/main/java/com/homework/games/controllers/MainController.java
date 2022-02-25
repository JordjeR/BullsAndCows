package com.homework.games.controllers;

import com.homework.games.entities.Rating;
import com.homework.games.entities.User;
import com.homework.games.services.RatingService;
import com.homework.games.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/")
public class MainController {

    private final UserService userService;
    private final RatingService ratingService;

    private List<Integer> rand;
    private int step;
    private int numberOfGamesPlayed;

    public MainController(UserService userService, RatingService ratingService) {
        this.userService = userService;
        this.ratingService = ratingService;
    }

    @PostConstruct
    private void init() {
        rand = ratingService.randomUntilDifferentNumber();
        numberOfGamesPlayed = 0;
        step = 0;
    }

    @GetMapping(value = "/auth/login")
    public ModelAndView getLoginPage(ModelAndView modelAndView) {
        updateResult();

        modelAndView.setViewName("login");

        return modelAndView;
    }

    @GetMapping(value = "/game/start")
    public ModelAndView gamePage(ModelAndView modelAndView, Principal principal) {
        User user = getCurrentUsername(principal);

        List<User> users = userService.findAll();

        modelAndView.addObject("user", user.getUsername());
        modelAndView.addObject("random", rand);
        modelAndView.addObject("all", ratingService.getAll());
        modelAndView.addObject("usersInfo", users);


        modelAndView.setViewName("games");

        return modelAndView;
    }

    @GetMapping(value = "/game/new-game")
    public ModelAndView startNewGame(ModelAndView modelAndView, Principal principal) {
        User user = getCurrentUsername(principal);

        modelAndView.addObject("congratulations", principal.getName());
        modelAndView.addObject("step", user.getStep());
        modelAndView.addObject("avg", user.getAverage());
        modelAndView.addObject("numberOfGames", user.getNumberOfGamesPlayed());

        updateResult();

        modelAndView.setViewName("newGame");

        return modelAndView;
    }

    @ResponseBody
    @PostMapping(value = "/game/add-rating")
    public ModelAndView addRating(@ModelAttribute("rating") Rating rating,
                                  ModelAndView modelAndView,
                                  Principal principal
    ) {

        User user = userService.findUserByUsername(getCurrentUsername(principal).getUsername()).get();

        if (ratingService.sepDigital(rating.getAttempts()).containsKey(-1)) {
            rating.setNotice("Введите не повторяющиеся числа!");
        } else {
            ratingService.sepDigital(rating.getAttempts()).get(1);

            rating.setNotice(
                    !ratingService.untilYouGuess(rand, ratingService.sepDigital(rating.getAttempts()).get(1))
                            .equalsIgnoreCase("Вы угадали число!")
                            ?
                            rating.getAttempts() + " " + ratingService.untilYouGuess(rand, ratingService.sepDigital(rating.getAttempts())
                                    .get(1))
                                    + " " + "хороший ход!"

                            : "Поздравляю вы угадали число " + rating.getAttempts() + "!!!"
            );

        }

        step++;

        ratingService.add(rating);

        if (rating.getNotice().contains("!!!")) {
            numberOfGamesPlayed++;

            int currentStep = user.getStep() + step;

            user.setStep(currentStep);

            int currentNumberOfGamesPlayed =
                    user.getNumberOfGamesPlayed() + numberOfGamesPlayed;

            user.setNumberOfGamesPlayed(currentNumberOfGamesPlayed);

            double currentAverage = user.getAverage() +
                    ((user.getNumberOfGamesPlayed().doubleValue()) / (user.getStep().doubleValue()));

            user.setAverage(currentAverage);


            userService.update(user, user.getId());

            modelAndView.setViewName("redirect:/game/new-game");

            return modelAndView;
        }

        modelAndView.setViewName("redirect:/game/start");

        return modelAndView;
    }

    private User getCurrentUsername(Principal principal) {
        Optional<User> optionalUser = userService.findUserByUsername(principal.getName());
        return optionalUser.orElseGet(User::new);
    }

    private void updateResult() {
        rand = ratingService.randomUntilDifferentNumber();
        ratingService.deleteAll();
        numberOfGamesPlayed = 0;
        step = 0;
    }
}