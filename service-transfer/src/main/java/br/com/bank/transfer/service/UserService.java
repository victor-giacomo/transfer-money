package br.com.bank.transfer.service;

import br.com.bank.transfer.domain.User;

import java.math.BigDecimal;

public interface UserService {
    User find(Long id);
    User find(String document);
    User save(User user);
    void transferAmount(User sender, User receiver, BigDecimal transferAmount);
}
