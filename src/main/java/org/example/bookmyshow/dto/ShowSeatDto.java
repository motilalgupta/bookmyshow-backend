package org.example.bookmyshow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bookmyshow.model.Show;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSeatDto {
    private Long id;
    private SeatDto seat;
    private String status;
    private Double price;

}
