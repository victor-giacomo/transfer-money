package br.com.bank.transfer.service.impl;

import br.com.bank.transfer.domain.User;
import br.com.bank.transfer.domain.UserType;
import br.com.bank.transfer.exception.BusinessException;
import br.com.bank.transfer.exception.NotFoundException;
import br.com.bank.transfer.repository.UserRepository;
import br.com.bank.transfer.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User find(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public User find(String document) {
        return repository.findByDocument(document).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    @Transactional
    public User save(User user) {

        if(repository.existsByDocument(user.getDocument())) {
            throw new BusinessException("The user document has already been used");
        }

        if(repository.existsByEmail(user.getEmail())) {
            throw new BusinessException("This email has already been used");
        }

        return repository.save(user);
    }

    @Override
    @Transactional
    public void transferAmount(User sender, User receiver, BigDecimal transferAmount) {

        validateTransfer(sender, transferAmount);

        sender.subtract(transferAmount);
        receiver.add(transferAmount);

        repository.save(sender);
        repository.save(receiver);
    }

    private void validateTransfer(User sender, BigDecimal transferAmount) {
        if(UserType.STORE.equals(sender.getUserType())) {
            throw new BusinessException("As a store you cannot transfer money");
        }
        if(sender.getAmount().compareTo(transferAmount) < 0) {
            throw new BusinessException("The sender doesn't have this amount to transfer");
        }
    }
}
