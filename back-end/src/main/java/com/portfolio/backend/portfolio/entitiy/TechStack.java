package com.portfolio.backend.portfolio.entitiy;

import com.portfolio.backend.account.Account;
import com.portfolio.backend.portfolio.type.DetailsType;
import com.portfolio.backend.portfolio.type.TechType;
import com.portfolio.backend.portfolio.entitiy.career.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@Getter
@Entity
public class TechStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tech_type")
    private TechType techType;

    protected TechStack(){}

    public TechStack(Portfolio portfolio, TechType techType){
        this.portfolio = portfolio;
        this.techType = techType;
    }

    public TechStack(Project project, TechType techType){
        this.project = project;
        this.techType = techType;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    protected Portfolio getPortfolio(){
        return this.portfolio;
    }

    protected Project getProject(){
        return this.project;
    }

}
