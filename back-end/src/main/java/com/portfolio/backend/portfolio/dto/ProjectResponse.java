package com.portfolio.backend.portfolio.dto;

import com.portfolio.backend.portfolio.entitiy.TechStack;
import com.portfolio.backend.portfolio.entitiy.career.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {

    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private String contents;

    private String accountNickname;

    private String url;

    private Set<TechStack> techStacks;

    public ProjectResponse(Project project){
        this.id = project.getId();
        this.name = project.getName();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
        this.contents = project.getContents();
        this.accountNickname = project.getAccountNickname();
        this.url = project.getUrl();
        this.techStacks =project.getTechStacks();
    }


}
