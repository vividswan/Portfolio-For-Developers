package com.portfolio.backend.portfolio.entitiy.career;

import com.portfolio.backend.account.Account;
import com.portfolio.backend.config.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Career extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    private String contents;

    @Column(nullable = false)
    private String accountNickname;

    public Career(
                  String name,
                  Account account,
                  LocalDate startDate,
                  LocalDate endDate,
                  String contents){

        this.name = name;
        this.accountNickname = account.getNickname();
        this.startDate = startDate;
        this.endDate = endDate;
        this.contents = contents;
    }

    public void updateCareer(String name, String contents,LocalDate startDate, LocalDate endDate){
        this.name = name;
        this.contents = contents;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
