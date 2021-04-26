package com.portfolio.backend.portfolio.entitiy.career;

import com.portfolio.backend.account.Account;
import com.portfolio.backend.portfolio.dto.ProjectDataRequest;
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

    @OneToMany(mappedBy = "project")
    private Set<TechStack>  techStacks = new HashSet<>();

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
                    super(portfolio, account, name, startDate, endDate, contents);
                    this.url = url;
    }


    public void updateProject(ProjectDataRequest dto){
        super.updateCareer(dto.getName(),dto.getContents(),dto.getStartDate(), dto.getEndDate());
        this.url = dto.getUrl();
    }
}
