package org.quera.ticket.service;

import java.math.BigDecimal;

public interface UserService {
    void updateBalance(Long userId, BigDecimal balance);
    boolean existByUsername(String username);
}
