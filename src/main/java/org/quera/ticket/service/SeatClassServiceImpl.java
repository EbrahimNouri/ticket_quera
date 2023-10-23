package org.quera.ticket.service;

import org.quera.ticket.models.SeatClass;
import org.quera.ticket.repository.SeatClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatClassServiceImpl implements SeatClassService{

    @Autowired
    private SeatClassRepository seatClassRepository;

    @Override
    public SeatClass[] getAllSeatClasses() {
        return seatClassRepository.findAll().toArray(new SeatClass[0]);
    }
}
