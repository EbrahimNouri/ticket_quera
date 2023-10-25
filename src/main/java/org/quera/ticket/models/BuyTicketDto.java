package org.quera.ticket.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuyTicketDto {
    Long match_id;
    Long seat_number;
}
