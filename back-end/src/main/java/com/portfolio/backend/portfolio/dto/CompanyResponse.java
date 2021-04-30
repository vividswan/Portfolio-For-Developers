package com.portfolio.backend.portfolio.dto;

import com.portfolio.backend.portfolio.entitiy.career.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {

    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private String contents;

    private String accountNickname;

    private String department;

    public CompanyResponse(Company company){
        this.id = company.getId();
        this.name = company.getName();
        this.startDate = company.getStartDate();
        this.endDate = company.getEndDate();
        this.contents = company.getContents();
        this.accountNickname = company.getAccountNickname();
        this.department = company.getDepartment();
    }

}
