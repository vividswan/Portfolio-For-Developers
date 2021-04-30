package com.portfolio.backend.portfolio.entitiy.career;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.portfolio.backend.account.Account;
import com.portfolio.backend.portfolio.dto.CareerDataRequest;
import com.portfolio.backend.portfolio.entitiy.Portfolio;
import com.portfolio.backend.portfolio.entitiy.TechStack;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("P")
@Entity
public class Project extends Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @OneToMany(mappedBy = "project",cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"project"})
    private Set<TechStack>  techStacks = new HashSet<>();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Builder
    private Project(
            Portfolio portfolio,
            Account account,
            String name,
            LocalDate startDate,
            LocalDate endDate,
            String contents,
            String url
    ){
                    super( name, account, startDate, endDate, contents);
                    this.account = account;
                    this.portfolio = portfolio;
                    this.url = url;
    }

    public void updateProject(CareerDataRequest dto){
        super.updateCareer(dto.getName(),dto.getContents(),dto.getStartDate(), dto.getEndDate());
        this.url = dto.getUrl();
    }

}
