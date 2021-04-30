package com.portfolio.backend.portfolio.contoller;

import com.portfolio.backend.config.advice.exception.CustomValidationException;
import com.portfolio.backend.model.response.CommonResponse;
import com.portfolio.backend.portfolio.dto.TechStackCreateRequest;
import com.portfolio.backend.portfolio.service.TechStackService;
import com.portfolio.backend.portfolio.type.DType;
import com.portfolio.backend.portfolio.type.DetailsType;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@Api(tags = {"TechStack API"})
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class TechStackController {

    private final TechStackService techStackService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 토큰", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "기술 스택 생성", notes = "Techstack Create Request로 전달")
    @PostMapping("/techStack")
    public CommonResponse createCareer(
            @ApiParam(value = "기술 스택 생성 request", required = true) @RequestBody TechStackCreateRequest dto, Errors errors){

        if(errors.hasErrors()) throw new CustomValidationException();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return techStackService.createTechStack(email, dto.getDetailsType(), dto.getRequestId(), dto.getTechType());
    }

    @ApiOperation(value = "기술 스택 다건 조회", notes = "포트폴리오 or 프로젝트 기술 스택 다건 조회")
    @GetMapping("techStack/{detailsType}/{id}")
    public CommonResponse getTechStacks(@ApiParam(value = "포트폴리오 or 프로젝트", required = true) @PathVariable DetailsType detailsType,
                                        @ApiParam(value = "포트폴리오 or 프로젝트 id", required = true) @PathVariable Long id){
        return techStackService.getTechStacks(detailsType, id);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 토큰", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "기술 스택 삭제", notes = "포트폴리오 or 프로젝트 기술 스택 삭제")
    @DeleteMapping("techStack/{detailsType}/{id}")
    public CommonResponse deleteTechStack(@ApiParam(value = "포트폴리오 or 프로젝트", required = true) @PathVariable DetailsType detailsType,
            @ApiParam(value = "기술 스택 id", required = true) @PathVariable Long id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return techStackService.deleteTechStack(email, detailsType ,id);
    }



}
