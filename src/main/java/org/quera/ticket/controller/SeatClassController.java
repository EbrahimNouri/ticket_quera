package org.quera.ticket.controller;

import org.quera.ticket.models.SeatClass;
import org.quera.ticket.service.SeatClassService;
import org.quera.ticket.service.SeatClassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seat_classes")
public class SeatClassController {

    @Autowired
    private SeatClassService seatClassService;
    @GetMapping
    SeatClass[] getSeatClasses(){
        return seatClassService.getAllSeatClasses();
    }
}
