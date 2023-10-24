package org.quera.ticket.service;

import org.quera.ticket.customException.NotFoundException;
import org.quera.ticket.customException.SeatClassException;
import org.quera.ticket.models.Match;
import org.quera.ticket.models.SeatClass;
import org.quera.ticket.repository.SeatClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SeatClassServiceImpl implements SeatClassService {

    @Autowired
    private SeatClassRepository seatClassRepository;

    @Autowired
    private MatchService matchService;

    @Override
    public SeatClass[] getAllSeatClasses() {
        return seatClassRepository.findAll().toArray(new SeatClass[0]);
    }

    @Override
    public void addSeatClass(SeatClass seatClass) {
        Match match = null;

        if (seatClassRepository.existsByMatch_Id(seatClass.getMatch().getId()))
            throw  new SeatClassException("Error: overlapping seat class exists");

        try {
            match = matchService.getMatch(seatClass.getMatch().getId());
        } catch (NotFoundException e) {
            throw new NotFoundException("Error: invalid match id");
        }

        if (seatClass.getMinNumber() > 1)
            throw new SeatClassException("Error: invalid min number");

        if (seatClass.getMaxNumber() < seatClass.getMinNumber())
            throw new SeatClassException("Error: invalid max number");

        if (seatClass.getPrice().compareTo(new BigDecimal(0)) < 0) {
            throw new SeatClassException("Error: invalid price");
        }

        seatClassRepository.save(seatClass);

    }

    @Override
    public SeatClass getSeatClass(Long id) {
        return seatClassRepository.findById(id).orElseThrow(NotFoundException::new);
    }

}
