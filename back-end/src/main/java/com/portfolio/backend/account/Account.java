package com.portfolio.backend.account;

import com.portfolio.backend.config.BaseTimeEntity;
import com.portfolio.backend.portfolio.entitiy.Portfolio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Account extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    private boolean isCertify;

    @Column(nullable = false)
    private String emailToken;

    @Column(nullable =false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public void acquireCertification(){
        this.isCertify = true;
    }

    public void changePassword(String password){
        this.password = password;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

}
