package org.example.bookmyshow.service;

import org.example.bookmyshow.dto.MovieDto;
import org.example.bookmyshow.exception.ResourceNotFoundException;
import org.example.bookmyshow.model.Movie;
import org.example.bookmyshow.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public MovieDto createMovie(MovieDto movieDto){

        Movie movie = mapToEntity(movieDto);
        Movie saveMovie = movieRepository.save(movie);
        return mapToDto(saveMovie);
    }

    public MovieDto getMovieById(Long id){
       Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with this: "+id));
        return mapToDto(movie);
    }

    public List<MovieDto> getAllMovies(){
        List<Movie> movies = movieRepository.findAll();

        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<MovieDto> getMovieByLanguage(String language){
        List<Movie> movies = movieRepository.findByLanguage(language);
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<MovieDto> getMovieByTitle(String title){
        List<Movie> movies = movieRepository.findByTitle(title);
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<MovieDto> getMovieByGenre(String genre){
        List<Movie> movies = movieRepository.findByGenre(genre);
        return movies.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public MovieDto updateMovie(Long id, MovieDto movieDto){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Movie not found with this id: "+id));

        movie.setId(movieDto.getMovieId());
        movie.setDescription(movieDto.getDescription());
        movie.setDurationMins(movieDto.getDurationMins());
        movie.setGenre(movieDto.getGenre());
        movie.setLanguage(movieDto.getLanguage());
        movie.setTitle(movieDto.getTitle());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setPosterUrl(movieDto.getPosterUrl());

        Movie updatedMovie = movieRepository.save(movie);

        return mapToDto(updatedMovie);
    }

    public void deleteMovie(Long id){
        Movie movie = movieRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Movie not found with this id: "+id));
        movieRepository.delete(movie);
    }

    private MovieDto mapToDto(Movie movie){
        MovieDto movieDto = new MovieDto();
        movieDto.setMovieId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setReleaseDate(movie.getReleaseDate());
        movieDto.setPosterUrl(movie.getPosterUrl());
        movieDto.setDurationMins(movie.getDurationMins());
        movieDto.setGenre(movie.getGenre());
        movieDto.setLanguage(movie.getLanguage());
        movieDto.setDescription(movie.getDescription());

        return movieDto;
    }

    private Movie mapToEntity(MovieDto movieDto){
        Movie movie = new Movie();
        movie.setId(movieDto.getMovieId());
        movie.setDescription(movieDto.getDescription());
        movie.setDurationMins(movieDto.getDurationMins());
        movie.setGenre(movieDto.getGenre());
        movie.setLanguage(movieDto.getLanguage());
        movie.setTitle(movieDto.getTitle());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setPosterUrl(movieDto.getPosterUrl());

        return movie;
    }
}