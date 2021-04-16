package com.portfolio.backend.config.security;

import com.portfolio.backend.account.Account;
import com.portfolio.backend.account.AccountRepository;
import com.portfolio.backend.config.advice.exception.CustomUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByEmail(email)
                .orElseThrow(CustomUserNotFoundException::new);
        return new CustomUserDetails(account);
    }
}
