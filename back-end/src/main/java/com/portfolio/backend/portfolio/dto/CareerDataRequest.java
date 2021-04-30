package com.portfolio.backend.portfolio.dto;

import com.portfolio.backend.account.Account;
import com.portfolio.backend.portfolio.entitiy.Portfolio;
import com.portfolio.backend.portfolio.entitiy.career.Company;
import com.portfolio.backend.portfolio.entitiy.career.Project;
import com.portfolio.backend.portfolio.entitiy.career.School;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareerDataRequest {

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull
    private String name;

    private String contents;

    private String url;

    private String department;

    private String major;

    private boolean graduate;

    public Project getProjectEntity(Account account, Portfolio portfolio) {
        Project project = Project.builder()
                .account(account)
                .portfolio(portfolio)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .name(this.name)
                .contents(this.contents)
                .url(this.url)
                .build();
      return project;
    }

    public Company getCompanyEntity(Account account, Portfolio portfolio){
        Company company = Company.builder()
                .account(account)
                .portfolio(portfolio)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .name(this.name)
                .contents(this.contents)
                .department(this.url)
                .build();
        return company;
        }

        public School getSchoolEntity(Account account, Portfolio portfolio){
            School school = School.builder()
                    .account(account)
                    .portfolio(portfolio)
                    .startDate(this.startDate)
                    .endDate(this.endDate)
                    .name(this.name)
                    .contents(this.contents)
                    .major(this.major)
                    .graduate(this.graduate)
                    .build();
            return school;
        }
}