package com.portfolio.backend.portfolio.contoller;

import com.portfolio.backend.config.advice.exception.CustomValidationException;
import com.portfolio.backend.model.response.CommonResponse;
import com.portfolio.backend.portfolio.dto.PortUpdateRequest;
import com.portfolio.backend.portfolio.service.PortfolioService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = {"Portfolio API"})
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @ApiOperation(value = "포트폴리오 한 건 조회 API", notes = "조회하는 계정의 닉네임 전송")
    @GetMapping(value = "/portfolio/{nickname}")
    public CommonResponse getOnePort(@ApiParam(value = "포트폴리오를 조회할 사용자 nickname", required = true) @PathVariable String nickname){
        return portfolioService.getOnePort(nickname);
    }

    @ApiOperation(value = "포트폴리오 다 건 조회 API")
    @GetMapping(value = "/portfolios")
    public CommonResponse getAllPort(@PageableDefault Pageable pageable){
        return portfolioService.getAllPort(pageable);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 토큰", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "포트폴리오 정보 변경", notes = "기술스택, 경력, 학교, 프로젝트를 제외한 나머지 정보 수정")
    @PutMapping(value = "/portfolio")
    public CommonResponse updatePort(@ApiParam(value = "포트폴리오 정보 변경 객체", required = true) @RequestBody @Valid PortUpdateRequest portUpdateRequest, Errors errors){
        if(errors.hasErrors()) throw new CustomValidationException();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return portfolioService.updatePort(portUpdateRequest, email);
    }




}
