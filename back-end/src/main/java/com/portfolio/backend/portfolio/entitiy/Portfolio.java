package com.portfolio.backend.portfolio.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.portfolio.backend.account.Account;
import com.portfolio.backend.config.BaseTimeEntity;
import com.portfolio.backend.portfolio.dto.PortUpdateRequest;
import com.portfolio.backend.portfolio.entitiy.career.Company;
import com.portfolio.backend.portfolio.entitiy.career.Project;
import com.portfolio.backend.portfolio.entitiy.career.School;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Portfolio extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(nullable = false, unique = true)
    private String accountNickname;

    private String name;

    private String introduction;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String gitId;

    @Column(unique = true)
    private String bojId;

    @Column(unique = true)
    private String blogUrl;

    private String occupation;

    private String location;

    @Lob
    private String profileImage;

    @OneToMany(mappedBy = "portfolio", cascade =  CascadeType.REMOVE)
    @JsonIgnoreProperties({"portfolio"})
    private Set<TechStack> techStacks = new HashSet<>();

    @OneToMany(mappedBy = "portfolio", cascade =  CascadeType.REMOVE)
    @JsonIgnoreProperties({"portfolio"})
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "portfolio", cascade =  CascadeType.REMOVE)
    @JsonIgnoreProperties({"portfolio"})
    private List<School> schools = new ArrayList<>();

    @OneToMany(mappedBy = "portfolio", cascade =  CascadeType.REMOVE)
    @JsonIgnoreProperties({"portfolio"})
    private List<Company> companies = new ArrayList<>();

    public void updatePortInfo(PortUpdateRequest dto){
        this.name = dto.getName();
        this.introduction = dto.getIntroduction();
        this.gitId = dto.getGitId();
        this.blogUrl = dto.getBlogUrl();
        this.occupation = dto.getOccupation();
        this.location = dto.getLocation();
        this.profileImage = dto.getProfileImage();
    }

}
