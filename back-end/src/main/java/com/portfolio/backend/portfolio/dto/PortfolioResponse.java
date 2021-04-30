package com.portfolio.backend.portfolio.dto;

import com.portfolio.backend.account.Account;
import com.portfolio.backend.portfolio.entitiy.Portfolio;
import com.portfolio.backend.portfolio.entitiy.TechStack;
import com.portfolio.backend.portfolio.entitiy.career.Company;
import com.portfolio.backend.portfolio.entitiy.career.Project;
import com.portfolio.backend.portfolio.entitiy.career.School;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioResponse {

    private Long id;

    private Account account;

    private String accountNickname;

    private String name;

    private String introduction;

    private String email;

    private String gitId;

    private String bojId;

    private String blogUrl;

    private String occupation;

    private String location;

    private String profileImage;

    private Set<TechStack> techStacks;

    private List<Project> projects;

    private List<School> schools;

    private List<Company> companies;

    public PortfolioResponse(Portfolio portfolio){
        this.name = portfolio.getName();
        this.introduction = portfolio.getIntroduction();
        this.gitId = portfolio.getGitId();
        this.blogUrl = portfolio.getBlogUrl();
        this.occupation = portfolio.getOccupation();
        this.location = portfolio.getLocation();
        this.profileImage = portfolio.getProfileImage();
        this.techStacks = portfolio.getTechStacks();
        this.projects = portfolio.getProjects();
        this.schools = portfolio.getSchools();
        this.companies = portfolio.getCompanies();
    }

}



