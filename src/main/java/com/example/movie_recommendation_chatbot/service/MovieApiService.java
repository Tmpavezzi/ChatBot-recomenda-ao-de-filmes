package com.example.movie_recommendation_chatbot.service;

import com.example.movie_recommendation_chatbot.model.Movie;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieApiService {

    private final String apikey = "d1edfdff";
    private final String apiURL = "http://www.omdbapi.com/";

    public Movie getMovieByTitle(String title){
        RestTemplate restTemplate = new RestTemplate();
        String url = apiURL +"?t=" + title +"&apikey=" + apikey;
        String response = restTemplate.getForObject(url,String.class);

        JSONObject json = new JSONObject(response);

        String moviTitle = json.optString("Title");
        String year = json.optString("Year");
        String genre = json.optString("Genre");
        String plot = json.optString("Plot");

        return new Movie(moviTitle,year,genre,plot);
    }
    public String getMoviesByGenre(String genre){
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = apiURL + "?s=" + genre + "&apikey=" + apikey;  // Faz a busca
            String response = restTemplate.getForObject(url, String.class);

            JSONObject json = new JSONObject(response);

            if (!json.has("Search")) {
                return "Não encontrei filmes para o gênero " + genre + ". Tente outro gênero!";
            }

            JSONArray searchResults = json.getJSONArray("Search");

            StringBuilder recommendations = new StringBuilder("Aqui estão alguns filmes de " + genre + ":\n");

            // Loop pelos resultados (mostrando até 3 filmes)
            for (int i = 0; i < Math.min(3, searchResults.length()); i++) {
                JSONObject movie = searchResults.getJSONObject(i);
                String movieTitle = movie.optString("Title", "Título não disponível");
                String movieYear = movie.optString("Year", "Ano não disponível");

                // Adiciona título e ano ao resultado
                recommendations.append("\n").append(movieTitle).append(" (").append(movieYear).append(")");
            }

            return recommendations.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Ocorreu um erro ao tentar buscar filmes. Tente novamente mais tarde.";
        }
    }

}
