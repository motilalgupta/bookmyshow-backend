package org.example.bookmyshow.service;

import org.apache.el.lang.ELArithmetic;
import org.example.bookmyshow.dto.TheaterDto;
import org.example.bookmyshow.exception.ResourceNotFoundException;
import org.example.bookmyshow.model.ShowSeat;
import org.example.bookmyshow.model.Theater;
import org.example.bookmyshow.repository.ShowSeatRepository;
import org.example.bookmyshow.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;
    @Autowired
    private ShowSeatRepository showSeatRepository;


    public TheaterDto createTheater(TheaterDto theaterDto){

        Theater theater = mapToEntity(theaterDto);

        Theater savedTheater = theaterRepository.save(theater);
        return mapToDto(savedTheater);
    }

    public TheaterDto getTheaterById(Long id){
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Theater is not found with this id: "+id));
        return mapToDto(theater);
    }

    public List<TheaterDto> getAllTheaters(){
        List<Theater> theaters = theaterRepository.findAll();
        return theaters.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<TheaterDto> getTheaterByCity(String city){

        List<Theater> theaters = theaterRepository.findByCity(city);
        return theaters.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public TheaterDto updateTheater(Long id,TheaterDto theaterDto){
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Theater not found with this id: "+id));

        theater.setId(theaterDto.getId());
        theater.setName(theaterDto.getName());
        theater.setCity(theaterDto.getCity());
        theater.setAddress(theaterDto.getAddress());
        theater.setTotalScreens(theaterDto.getTotalScreens());

        Theater savedTheater = theaterRepository.save(theater);
        return mapToDto(savedTheater);
    }

    public void cancelTheater(Long id){
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Theater not found with this id: "+id));
        theaterRepository.delete(theater);
    }


    private TheaterDto mapToDto(Theater theater){
        TheaterDto theaterDto = new TheaterDto();
        theaterDto.setId(theater.getId());
        theaterDto.setCity(theater.getCity());
        theaterDto.setTotalScreens(theater.getTotalScreens());
        theaterDto.setAddress(theater.getAddress());
        theaterDto.setName(theater.getName());

        return theaterDto;
    }

    private Theater mapToEntity(TheaterDto theaterDto) {
        Theater theater = new Theater();
        theater.setName(theaterDto.getName());
        theater.setAddress(theaterDto.getAddress());
        theater.setCity(theaterDto.getCity());
        theater.setTotalScreens(theaterDto.getTotalScreens());

        return theater;
    }
}


