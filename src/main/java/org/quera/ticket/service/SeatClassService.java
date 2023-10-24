package org.quera.ticket.service;

import org.quera.ticket.models.SeatClass;

public interface SeatClassService {
    SeatClass[] getAllSeatClasses();

    void addSeatClass(SeatClass seatClass);

    SeatClass getSeatClass(Long id);
}
