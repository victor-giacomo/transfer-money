package br.com.bank.transfer.repository;

import br.com.bank.transfer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByDocument(String document);
    boolean existsByDocument(String document);
    boolean existsByEmail(String email);
}