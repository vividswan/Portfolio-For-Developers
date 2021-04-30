package com.portfolio.backend.portfolio.repository;

import com.portfolio.backend.portfolio.entitiy.Portfolio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    Optional<Portfolio> findPortfolioByEmail(String email);

    Optional<Portfolio> findPortfolioByAccountNickname(String accountNickname);

    Optional<Portfolio> findPortfolioById(Long id);

    Page<Portfolio> findAll(Pageable pageable);

}
