package com.portfolio.backend.account;

import com.portfolio.backend.account.dto.AccountInfoResponse;
import com.portfolio.backend.account.dto.PasswordUpdateRequest;
import com.portfolio.backend.account.dto.SignInRequest;
import com.portfolio.backend.account.dto.SignUpRequest;
import com.portfolio.backend.config.advice.exception.CustomValidationException;
import com.portfolio.backend.model.response.CommonResponse;
import com.portfolio.backend.model.response.SingleResponse;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"Account API"})
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class AccountController {

    private final  AccountService accountService;

    @ApiOperation(value = "회원 가입 API", notes = "이메일, 닉네임, 비밀번호 전송")
    @PostMapping(value = "/sign-up")
    public CommonResponse signUp(@ApiParam(value = "회원가입 요청 객체", required = true) @RequestBody @Valid SignUpRequest dto, Errors errors){
        if(errors.hasErrors()) throw new CustomValidationException();
        return accountService.signUp(dto);
    }

    @ApiOperation(value = "로그인 API",notes = "학번, 비밀번호 전송")
    @PostMapping(value =  "/sign-in")
    public SingleResponse<String> signIn(@ApiParam(value = "로그인 요청 객체",required = true) @RequestBody @Valid SignInRequest dto, Errors errors){
        if(errors.hasErrors()) throw new CustomValidationException();
        return accountService.signIn(dto);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 토큰", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "로그인한 회원 조회", notes = "로그인 후 받은 토큰으로 인증한다.")
    @GetMapping(value = "/account")
    public SingleResponse<AccountInfoResponse> getAccountInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return accountService.getAccountInfo(email);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 토큰", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "비밀번호 변경 API", notes ="이전 비밀번호, 새로운 비밀번호 전송")
    @PutMapping(value = "/account/password")
    public SingleResponse<String> updatePassword(@ApiParam(value = "비밀번호 변경 객체", required = true) @RequestBody @Valid PasswordUpdateRequest dto, Errors errors){
        if(errors.hasErrors()) throw new CustomValidationException();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return accountService.updatePassword(dto, email);
    }



}
