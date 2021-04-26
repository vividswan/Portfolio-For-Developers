package com.portfolio.backend.portfolio.entitiy.career;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.portfolio.backend.account.Account;
import com.portfolio.backend.config.BaseTimeEntity;
import com.portfolio.backend.portfolio.entitiy.Portfolio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Career extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    private String contents;

    public Career(Portfolio portfolio,
                  Account account,
                  String name,
                  LocalDate startDate,
                  LocalDate endDate,
                  String contents){

        this.name = name;
        this.portfolio = portfolio;
        this.account = account;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contents = contents;
    }

    public void updateCareer(String name, String contents,LocalDate startDate, LocalDate endDate){
        this.name = name;
        this.contents = contents;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
