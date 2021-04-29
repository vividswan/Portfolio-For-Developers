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
@DiscriminatorValue("S")
@Entity
public class School extends Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String major;
    private boolean graduate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Builder
    private School(
            Portfolio portfolio,
            Account account,
            String name,
            LocalDate startDate,
            LocalDate endDate,
            String contents,
            String major,
            boolean graduate
    ){
                    super( name, account, startDate, endDate, contents);
                    this.account = account;
                    this.portfolio = portfolio;
                    this.major = major;
                    this.graduate = graduate;
    }


    public void updateProject(CareerDataRequest dto){
        super.updateCareer(dto.getName(),dto.getContents(),dto.getStartDate(), dto.getEndDate());
        this.major = dto.getMajor();
        this.graduate = dto.isGraduate();
    }
}
