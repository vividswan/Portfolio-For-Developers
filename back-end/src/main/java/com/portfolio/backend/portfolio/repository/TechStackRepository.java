package com.portfolio.backend.portfolio.repository;

import com.portfolio.backend.portfolio.entitiy.Portfolio;
import com.portfolio.backend.portfolio.entitiy.TechStack;
import com.portfolio.backend.portfolio.entitiy.career.Project;
import com.portfolio.backend.portfolio.type.TechType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TechStackRepository extends JpaRepository<TechStack, Long> {

    List<TechStack> findAllByPortfolio(Portfolio portfolio);
    List<TechStack> findAllByProject(Project project);
    Optional<TechStack> findByPortfolioAndTechType(Portfolio portfolio,TechType techType);
    Optional<TechStack> findByProjectAndTechType(Project project, TechType techType);
}
