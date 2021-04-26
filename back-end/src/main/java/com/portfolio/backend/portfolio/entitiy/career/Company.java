package com.portfolio.backend.portfolio.entitiy.career;

import com.portfolio.backend.account.Account;
import com.portfolio.backend.portfolio.dto.CompanyDataRequest;
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
                    super(portfolio, account, name, startDate, endDate, contents);
                    this.department = department;
    }


    public void updateProject(CompanyDataRequest dto){
        super.updateCareer(dto.getName(),dto.getContents(),dto.getStartDate(), dto.getEndDate());
        this.department = dto.getDepartment();
    }
}
