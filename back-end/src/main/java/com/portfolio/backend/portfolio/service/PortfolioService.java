package com.portfolio.backend.portfolio.service;

import com.portfolio.backend.config.advice.exception.CustomUserNotFoundException;
import com.portfolio.backend.model.response.CommonResponse;
import com.portfolio.backend.model.response.ResponseService;
import com.portfolio.backend.portfolio.dto.PortUpdateRequest;
import com.portfolio.backend.portfolio.dto.PortfolioResponse;
import com.portfolio.backend.portfolio.entitiy.Portfolio;
import com.portfolio.backend.portfolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional
@Service
public class PortfolioService {

    private final ResponseService responseService;
    private final PortfolioRepository portfolioRepository;

    @Transactional(readOnly = true)
    public CommonResponse getOnePort(String nickname) {

        Portfolio portfolio = portfolioRepository.findPortfolioByAccountNickname(nickname)
                .orElseThrow(CustomUserNotFoundException::new);

        return responseService.getSingleResponse(new PortfolioResponse(portfolio));
    }

    @Transactional(readOnly = true)
    public CommonResponse getAllPort(Pageable pageable) {
        Page<Portfolio> portfolios = portfolioRepository.findAll(pageable);
        return responseService.getPageResponse(portfolios.map(PortfolioResponse::new));
    }

    public CommonResponse updatePort(PortUpdateRequest portUpdateRequest, String email) {

        Portfolio portfolio = portfolioRepository.findPortfolioByEmail(email)
                .orElseThrow(CustomUserNotFoundException::new);

        portfolio.updatePortInfo(portUpdateRequest);

        return responseService.getSuccessResponse();
    }

}
