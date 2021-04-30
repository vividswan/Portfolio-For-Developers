package com.portfolio.backend.portfolio.service;

import com.portfolio.backend.account.Account;
import com.portfolio.backend.account.AccountRepository;
import com.portfolio.backend.config.advice.exception.CustomDataNotFoundException;
import com.portfolio.backend.config.advice.exception.CustomNotOwnerException;
import com.portfolio.backend.config.advice.exception.CustomUserNotFoundException;
import com.portfolio.backend.model.response.CommonResponse;
import com.portfolio.backend.model.response.ResponseService;
import com.portfolio.backend.portfolio.dto.TechStackResponse;
import com.portfolio.backend.portfolio.entitiy.Portfolio;
import com.portfolio.backend.portfolio.entitiy.TechStack;
import com.portfolio.backend.portfolio.entitiy.career.Project;
import com.portfolio.backend.portfolio.repository.CareerRepository;
import com.portfolio.backend.portfolio.repository.PortfolioRepository;
import com.portfolio.backend.portfolio.repository.TechStackRepository;
import com.portfolio.backend.portfolio.type.DetailsType;
import com.portfolio.backend.portfolio.type.TechType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Transactional
@RequiredArgsConstructor
@Service
public class TechStackService {

    private final TechStackRepository techStackRepository;
    private final CareerRepository careerRepository;
    private final PortfolioRepository portfolioRepository;
    private final AccountRepository accountRepository;
    private final ResponseService responseService;

    public CommonResponse createTechStack(String email, DetailsType detailsType, Long requestId, TechType techType) {

        TechStack techStack = null;

        Account account = accountRepository.findByEmail(email)
                .orElseThrow(CustomUserNotFoundException::new);

        if (detailsType.equals(DetailsType.portfolio)) {
            Portfolio portfolio = portfolioRepository.findPortfolioById(requestId)
                    .orElseThrow(CustomDataNotFoundException::new);

            if(!portfolio.getAccount().equals(account)) throw new CustomDataNotFoundException();

            if(techStackRepository.findByPortfolioAndTechType(portfolio, techType).isPresent()) return responseService.getFailResponse("중복 된 기술 스택입니다.");
            techStack = new TechStack(portfolio, techType);

            techStackRepository.save(techStack);

        } else if (detailsType.equals(DetailsType.project)) {
            Project project = careerRepository.findProjectById(requestId)
                    .orElseThrow(CustomDataNotFoundException::new);

            if(!project.getAccount().equals(account)) throw new CustomDataNotFoundException();

            if(techStackRepository.findByProjectAndTechType(project, techType).isPresent()) return responseService.getFailResponse("중복 된 기술 스택입니다.");
            techStack = new TechStack(project, techType);

            techStackRepository.save(techStack);
        }

        return responseService.getSingleResponse(new TechStackResponse(techStack));

    }

    @Transactional(readOnly = true)
    public CommonResponse getTechStacks(DetailsType detailsType, Long id) {

        List<TechStack> techStacks = null;

        if (detailsType.equals(DetailsType.portfolio)) {

            Portfolio portfolio = portfolioRepository.findPortfolioById(id)
                    .orElseThrow(CustomDataNotFoundException::new);

            techStacks = techStackRepository.findAllByPortfolio(portfolio);
        } else if (detailsType.equals(DetailsType.project)) {

            Project project = careerRepository.findProjectById(id)
                    .orElseThrow(CustomDataNotFoundException::new);

            techStacks = techStackRepository.findAllByProject(project);
        }

        return responseService.getListResponse(techStacks.stream().map(TechStackResponse::new).collect(Collectors.toList()));

    }

    public CommonResponse deleteTechStack(String email, DetailsType detailsType ,Long id) {
        Account account = accountRepository.findAccountByEmail(email)
                .orElseThrow(CustomUserNotFoundException::new);

        TechStack techStack = techStackRepository.findById(id)
                .orElseThrow(CustomDataNotFoundException::new);

        if(techStack.isAuthorized(detailsType, account)){
            techStackRepository.delete(techStack);
            return responseService.getSuccessResponse();
        }
        else throw new CustomNotOwnerException();

    }
}
