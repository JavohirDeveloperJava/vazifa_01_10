package com.example.vazifa_01_10.payload;

import com.example.vazifa_01_10.entitiy.Hotel;
import lombok.Data;

import java.util.List;

@Data
public class RoomDto {

    private Integer number;

    private Integer floor; //etaj

    private Integer size; //hona hajmi

    private Integer hotelId;
}
