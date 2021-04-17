package com.portfolio.backend.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.backend.account.dto.SignInRequest;
import com.portfolio.backend.account.dto.SignUpRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @DisplayName("회원가입 테스트")
    @Test
    public void signupTest() throws Exception{

        // given
        SignUpRequest signupDto = SignUpRequest.builder()
                .email("SignUp@test.com")
                .nickname("signUp")
                .password("signUp1234")
                .build();

        // when
        final ResultActions perform = mockMvc.perform(post("/api/sign-up")
                .content(objectMapper.writeValueAsString(signupDto))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.success").value(true));

    }

    @DisplayName("로그인 테스트")
    @Test
    public void signInTest() throws Exception{
        
        //given
        String email = "test@test.com";
        String nickname = "testUser";
        String password = "test1234";

        Account accountEntity = Account.builder()
                .email(email)
                .nickname(nickname)
                .password(passwordEncoder.encode(password))
                .isCertify(false)
                .roles(Collections.singletonList("ROLE_USER"))
                .emailToken(UUID.randomUUID().toString())
                .build();
        accountRepository.save(accountEntity);

        SignInRequest signInRequest = SignInRequest.builder()
                .email(email)
                .password(password)
                .build();

        //when
        final ResultActions perform = mockMvc.perform(post("/api/sign-in")
                .content(objectMapper.writeValueAsString(signInRequest))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").exists());

    }

}