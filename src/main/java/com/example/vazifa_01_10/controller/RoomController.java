package com.example.vazifa_01_10.controller;

import com.example.vazifa_01_10.entitiy.Hotel;
import com.example.vazifa_01_10.entitiy.Room;
import com.example.vazifa_01_10.payload.RoomDto;
import com.example.vazifa_01_10.repository.HotelRepository;
import com.example.vazifa_01_10.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hona")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;


    @GetMapping("/honaGet")
    public Page<Room> get(@RequestParam int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Room> roompage = roomRepository.findAll(pageable);
        return roompage;
    }

    @GetMapping("/honaGetIdHotel{hotelId}")
    public Page<Room> get(@PathVariable Integer hotelId, @RequestParam int page){
        Pageable pageable= PageRequest.of(page,10);
        Page<Room> allByHotelId = roomRepository.findAllByHotelId(hotelId, pageable);
        return allByHotelId;
    }

    @PostMapping("/honaPost")
    public String add(RoomDto dto){
        Room room=new Room();
        room.setNumber(dto.getNumber());
        room.setFloor(dto.getFloor());
        room.setSize(dto.getSize());
        Optional<Hotel> optionalHotel = hotelRepository.findById(dto.getHotelId());
        if (!optionalHotel.isPresent())
            return "Bunday hotel topiladi";
        room.setHotel(optionalHotel.get());
        roomRepository.save(room);
        return "Room saqlandi";
    }

    @PutMapping("/honaPut/{id}")
    public String put(@PathVariable Integer id, RoomDto dto){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()){
            Room room = optionalRoom.get();
            room.setNumber(dto.getNumber());
            room.setFloor(dto.getFloor());
            room.setSize(dto.getSize());
            Optional<Hotel> optionalHotel = hotelRepository.findById(dto.getHotelId());
            if (!optionalHotel.isPresent())
                return "Bunday hotel topilmadi";
            room.setHotel(optionalHotel.get());
            roomRepository.save(room);
            return "Room ozgardi";
        }
        return "Bunday room topilmadi";
    }

    @DeleteMapping("/honaDelet/{id}")
    public String delet(@PathVariable Integer id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()){
            roomRepository.deleteById(id);
            return "Room ochdi";
        }
        return "Room topilmadi";
    }


}
