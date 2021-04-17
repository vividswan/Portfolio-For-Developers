package com.portfolio.backend.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findAccountByEmail(String email);

    Optional<Account> findByEmail(String email);

    Optional<Account> findByNickname(String email);
}
