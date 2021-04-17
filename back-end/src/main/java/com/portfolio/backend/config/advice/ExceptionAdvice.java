package com.portfolio.backend.config.advice;

import com.portfolio.backend.config.advice.exception.CustomUserNotFoundException;
import com.portfolio.backend.config.advice.exception.CustomValidationException;
import com.portfolio.backend.model.response.CommonResponse;
import com.portfolio.backend.model.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse defaultException(HttpServletRequest request, Exception e) {
        return responseService.getFailResponse("Default Error");
    }

    @ExceptionHandler(CustomUserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse userNotfoundException(HttpServletRequest req, CustomUserNotFoundException e){
        return responseService.getFailResponse("잘 못 된 User 정보 입니다.");
    }

    @ExceptionHandler(CustomValidationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResponse validationException(HttpServletRequest req, CustomValidationException e){
        if (e.getMessage() != null) {
            if (e.getMessage().equals("email-duplication")) return responseService.getFailResponse("중복되는 이메일입니다.");
        }
        return responseService.getFailResponse("잘 못 된 입력 값입니다.");
    }
}