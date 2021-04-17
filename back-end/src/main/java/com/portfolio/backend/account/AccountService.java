package com.portfolio.backend.account;

import com.portfolio.backend.account.dto.AccountInfoResponse;
import com.portfolio.backend.account.dto.PasswordUpdateRequest;
import com.portfolio.backend.account.dto.SignInRequest;
import com.portfolio.backend.account.dto.SignUpRequest;
import com.portfolio.backend.config.advice.exception.CustomUserNotFoundException;
import com.portfolio.backend.config.advice.exception.CustomValidationException;
import com.portfolio.backend.config.security.JwtTokenProvider;
import com.portfolio.backend.model.response.CommonResponse;
import com.portfolio.backend.model.response.ResponseService;
import com.portfolio.backend.model.response.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final ResponseService responseService;
    private final JwtTokenProvider jwtTokenProvider;

    public CommonResponse signUp(SignUpRequest dto) {
        if(accountRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new CustomValidationException("email-duplication");
        }

        Account account = Account.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .password(passwordEncoder.encode(dto.getPassword())) // 암호화
                .email(dto.getEmail())
                .emailToken(UUID.randomUUID().toString()) // 이메일 인증 문자열
                .isCertify(false) // 이메일 인증 여부
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        accountRepository.save(account);

        return responseService.getSuccessResponse();
    }

    public SingleResponse<String> signIn(SignInRequest dto) {
        Account account = accountRepository.findByEmail(dto.getEmail())
                .orElseThrow(CustomUserNotFoundException::new);
        if(!passwordEncoder.matches(dto.getPassword(),account.getPassword())){
            throw new CustomUserNotFoundException();
        }
        return responseService.getSingleResponse(jwtTokenProvider.createToken(account.getEmail(), account.getRoles()));
    }

    public SingleResponse<AccountInfoResponse> getAccountInfo(String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(CustomUserNotFoundException::new);

        return responseService.getSingleResponse(new AccountInfoResponse(account));
    }

    public SingleResponse<String> updatePassword(PasswordUpdateRequest dto, String email) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(CustomUserNotFoundException::new);
        if(!passwordEncoder.matches(dto.getBeforePassword(),account.getPassword())){
            throw new CustomUserNotFoundException();
        }
        account.changePassword(passwordEncoder.encode(dto.getUpdatePassword()));
        return responseService.getSingleResponse("비밀번호 변경 완료");
    }
}
