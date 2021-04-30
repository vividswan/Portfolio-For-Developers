package com.portfolio.backend.portfolio.contoller;

import com.portfolio.backend.config.advice.exception.CustomValidationException;
import com.portfolio.backend.model.response.CommonResponse;
import com.portfolio.backend.portfolio.dto.CareerDataRequest;
import com.portfolio.backend.portfolio.service.CareerService;
import com.portfolio.backend.portfolio.type.DType;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Api(tags = {"Career API"})
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class CareerController {

    private final CareerService careerService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 토큰", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "커리어 생성", notes = "시작 날짜, 이름, 완료 유무 -> 필수 값, 경로에 dType 명시해야함")
    @PostMapping("/career/{dType}")
    public CommonResponse createCareer(@ApiParam(value = "dType", required = true)
                                            @PathVariable DType dType, @ApiParam(value = "커리어 생성 정보 객체", required = true) @RequestBody @Valid CareerDataRequest dto, Errors errors){
        if(errors.hasErrors()) throw new CustomValidationException();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return careerService.createCareer(email, dto, dType);
    }


    @ApiOperation(value = "포트폴리오 커리어 전체 조회", notes = "조회하는 프로젝트의 포트폴리오의 ID 전송, 경로에 dType 명시해야함")
    @GetMapping("/career/portfolio/{portfolioId}/{dType}")
    public CommonResponse getAllCareer(@ApiParam(value = "포트폴리오 ID", required = true) @PathVariable Long portfolioId,
                                        @ApiParam(value = "dType", required = true) @PathVariable DType dType, @PageableDefault(size = 5) Pageable pageable){
        return careerService.getAllCareer(portfolioId, pageable,dType);
    }

    @ApiOperation(value = "커리어 한 건 조회", notes = "조회하는 커리어의 DType, ID 전송")
    @GetMapping("/career/{dType}/{careerId}")
    public CommonResponse getOneCareer(@ApiParam(value = "dType", required = true) @PathVariable DType dType ,
                                       @ApiParam(value = "커리어 ID", required = true) @PathVariable Long careerId){
        return careerService.getOneCareer(dType, careerId);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 토큰", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "커리어 수정", notes = "시작 날짜, 이름, 완료 유무 -> 필수 값, dType 명서 ")
    @PutMapping("/career/{dType}/{careerId}")
    public CommonResponse updateCareer(@ApiParam(value = "dType", required = true) @PathVariable DType dType ,
                                       @PathVariable Long careerId,@ApiParam(value = "커리어 정보 수정 객체", required = true)
    @RequestBody @Valid CareerDataRequest dto ,Errors errors){
        if(errors.hasErrors()) throw new CustomValidationException();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return careerService.updateCareer(email, careerId, dto, dType);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 토큰", required = false, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "커리어 삭제", notes = "삭제 할 커리어 Id 전달")
    @DeleteMapping("/career/{careerId}")
    public CommonResponse deleteCareer(@PathVariable Long careerId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return careerService.deleteCareer(email, careerId);
    }


}
