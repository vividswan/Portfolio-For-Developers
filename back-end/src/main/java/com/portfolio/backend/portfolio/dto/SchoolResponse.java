package com.portfolio.backend.portfolio.dto;

import com.portfolio.backend.portfolio.entitiy.career.School;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SchoolResponse {

    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private String contents;

    private String accountNickname;

    private String major;

    private boolean graduate;

    public SchoolResponse(School school){
        this.id = school.getId();
        this.name = school.getName();
        this.startDate = school.getStartDate();
        this.endDate = school.getEndDate();
        this.contents = school.getContents();
        this.accountNickname = school.getAccountNickname();
        this.major = school.getMajor();
        this.graduate = school.isGraduate();
    }

}
