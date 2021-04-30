package com.portfolio.backend.portfolio.repository;

import com.portfolio.backend.portfolio.entitiy.Portfolio;
import com.portfolio.backend.portfolio.entitiy.career.Career;
import com.portfolio.backend.portfolio.entitiy.career.Company;
import com.portfolio.backend.portfolio.entitiy.career.Project;
import com.portfolio.backend.portfolio.entitiy.career.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CareerRepository extends JpaRepository<Career, Long> {

    Optional<Career> findCareerById(Long id);

    Page<Project> findAllProjectByPortfolio(Portfolio portfolio, Pageable pageable);
    Optional<Project> findProjectById(Long id);

    Page<School> findAllSchoolByPortfolio(Portfolio portfolio, Pageable pageable);
    Optional<School> findSchoolById(Long id);

    Page<Company> findAllCompanyByPortfolio(Portfolio portfolio, Pageable pageable);
    Optional<Company> findCompanyById(Long id);

}
