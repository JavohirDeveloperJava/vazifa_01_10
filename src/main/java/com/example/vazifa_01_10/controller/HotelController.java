package com.example.vazifa_01_10.controller;

import com.example.vazifa_01_10.entitiy.Hotel;
import com.example.vazifa_01_10.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;


    @GetMapping("/hot")
    public List<Hotel> getHotel(){
        return hotelRepository.findAll();
    }

    @PostMapping("/hotPost")
    public String addHotel(@RequestBody Hotel hotel){
        Hotel hotel1=new Hotel();
        hotel1.setName(hotel.getName());
        hotelRepository.save(hotel1);
        return "Hotel saqlandi";
    }

    @PutMapping("/hotPut/{id}")
    public String put(@PathVariable Integer id, @RequestBody Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()){
            Hotel hotel1 = optionalHotel.get();
            hotel1.setName(hotel1.getName());
            hotelRepository.save(hotel1);
            return "Hotel ozgardi";
        }
        return "Bunday hotel yoq";
    }

    @DeleteMapping("/hotDelet/{id}")
    public String delet(@PathVariable Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()){
            hotelRepository.deleteById(id);
            return "Hotel ochdi";
        }
        return "Bunday hotel yoq";
    }
}
