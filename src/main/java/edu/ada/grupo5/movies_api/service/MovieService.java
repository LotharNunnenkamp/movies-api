package edu.ada.grupo5.movies_api.service;

import edu.ada.grupo5.movies_api.client.api.TMDBClientFeign;
import edu.ada.grupo5.movies_api.dto.tmdb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private final TMDBClientFeign tmdbClientFeign;

    @Autowired
    public MovieService(TMDBClientFeign tmdbClientFeign) {
        this.tmdbClientFeign = tmdbClientFeign;
    }

    public ModelResponseGET<TrendingMovieDTO> getTrendingMovies(String timeWindow, String language) {
        return tmdbClientFeign.getTrendingMovies(timeWindow, language);
    }

    public GenresResponseDTO getGenres(String language) {
        return tmdbClientFeign.getGenres(language);
    }

    public List<MovieTitleIdDTO> getMovie(String movieName, String includeAdult, String language, String page) {
        ModelResponseGET<MovieDTO> movieDTOModelResponseGET = tmdbClientFeign.getMovie(movieName, includeAdult, language, page);
        return movieDTOModelResponseGET.getResults().stream().map(movie -> new MovieTitleIdDTO(movie.getTitle(), movie.getId())).collect(Collectors.toList());
    }
}