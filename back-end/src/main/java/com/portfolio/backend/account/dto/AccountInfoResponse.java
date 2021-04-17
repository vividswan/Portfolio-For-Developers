package com.portfolio.backend.account.dto;

import com.portfolio.backend.account.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountInfoResponse {
    private Long id;
    private String email;
    private String nickname;
    private boolean isCertify;
    private List<String> roles;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public AccountInfoResponse(Account account){
        this.id = account.getId();
        this.email = account.getEmail();
        this.nickname = account.getNickname();
        this.isCertify = account.isCertify();
        this.roles = account.getRoles();
        this.createdDate = account.getCreatedDate();
        this.modifiedDate = account.getModifiedDate();
    }
}
