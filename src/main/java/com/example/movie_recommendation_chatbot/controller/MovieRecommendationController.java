package com.example.movie_recommendation_chatbot.controller;

import com.example.movie_recommendation_chatbot.service.MovieApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRecommendationController {
 
    private final MovieApiService movieApiService;

    public MovieRecommendationController(MovieApiService movieApiService) {
        this.movieApiService = movieApiService;
    }

    @GetMapping("/chat")
    public String chatWithBot(@RequestParam String userMessage) {
        try {
            String botResponse = generateBotResponse(userMessage);
            return botResponse;
        } catch (Exception e) {
            return "Ocorreu um erro ao processar sua solicitação. Tente novamente mais tarde!";
        }
    }

    private String generateBotResponse(String userMessage) {
        String lowerCaseMessage = userMessage.toLowerCase();
        if (lowerCaseMessage.contains("recomende um filme")) {
            return "Claro! Que tipo de filme você gosta? Ação, comédia, drama, romance, terror, ficção científica, animação?";
        } else if (lowerCaseMessage.contains("ação")) {
            return movieApiService.getMoviesByGenre("action");
        } else if (lowerCaseMessage.contains("comédia")) {
            return movieApiService.getMoviesByGenre("comedy");
        } else if (lowerCaseMessage.contains("drama")) {
            return movieApiService.getMoviesByGenre("drama");
        } else if (lowerCaseMessage.contains("romance")) {
            return movieApiService.getMoviesByGenre("romance");
        } else if (lowerCaseMessage.contains("terror")) {
            return movieApiService.getMoviesByGenre("horror");
        } else if (lowerCaseMessage.contains("ficção científica")) {
            return movieApiService.getMoviesByGenre("sci-fi");
        } else if (lowerCaseMessage.contains("animação")) {
            return movieApiService.getMoviesByGenre("animation");
        } else {
            return "Desculpe, não entendi sua mensagem. Pode tentar de novo?";
        }
    }
}