package com.portfolio.backend.portfolio.entitiy.career;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.portfolio.backend.account.Account;
import com.portfolio.backend.portfolio.dto.CareerDataRequest;
import com.portfolio.backend.portfolio.entitiy.Portfolio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("C")
@Entity
public class Company extends Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String department;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Builder
    private Company(
            Portfolio portfolio,
            Account account,
            String name,
            LocalDate startDate,
            LocalDate endDate,
            String contents,
            String department
    ){
                    super( name, account, startDate, endDate, contents);
                    this.account = account;
                    this.portfolio = portfolio;
                    this.department = department;
    }


    public void updateProject(CareerDataRequest dto){
        super.updateCareer(dto.getName(),dto.getContents(),dto.getStartDate(), dto.getEndDate());
        this.department = dto.getDepartment();
    }
}
