package com.portfolio.backend.portfolio.service;

import com.portfolio.backend.account.Account;
import com.portfolio.backend.account.AccountRepository;
import com.portfolio.backend.config.advice.exception.CustomDataNotFoundException;
import com.portfolio.backend.config.advice.exception.CustomNotOwnerException;
import com.portfolio.backend.config.advice.exception.CustomUserNotFoundException;
import com.portfolio.backend.model.response.CommonResponse;
import com.portfolio.backend.model.response.ResponseService;
import com.portfolio.backend.portfolio.dto.CareerDataRequest;
import com.portfolio.backend.portfolio.dto.CompanyResponse;
import com.portfolio.backend.portfolio.dto.ProjectResponse;
import com.portfolio.backend.portfolio.dto.SchoolResponse;
import com.portfolio.backend.portfolio.entitiy.Portfolio;
import com.portfolio.backend.portfolio.entitiy.career.Career;
import com.portfolio.backend.portfolio.entitiy.career.Company;
import com.portfolio.backend.portfolio.entitiy.career.Project;
import com.portfolio.backend.portfolio.entitiy.career.School;
import com.portfolio.backend.portfolio.repository.CareerRepository;
import com.portfolio.backend.portfolio.repository.PortfolioRepository;
import com.portfolio.backend.portfolio.type.DType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional
@Service
public class CareerService {

    private final PortfolioRepository portfolioRepository;
    private final AccountRepository accountRepository;
    private final CareerRepository careerRepository;
    private final ResponseService responseService;

    @Transactional(readOnly = true)
    public CommonResponse getAllCareer(Long portfolioId, Pageable pageable, DType dType) {

        Portfolio portfolio = portfolioRepository.findPortfolioById(portfolioId)
                .orElseThrow(CustomDataNotFoundException::new);

        if (dType.equals(DType.P)) {
            Page<Project> projects =careerRepository.findAllProjectByPortfolio(portfolio, pageable);
            Page<ProjectResponse> projectResponses = projects.map(ProjectResponse::new);
            return responseService.getPageResponse(projectResponses);
        }

        else if (dType.equals(DType.C)) {
            Page<Company> companies = careerRepository.findAllCompanyByPortfolio(portfolio, pageable);
            Page<CompanyResponse> commonResponses  = companies.map(CompanyResponse::new);
            return responseService.getPageResponse(commonResponses);
        }

        else {
                    Page<School> schools = careerRepository.findAllSchoolByPortfolio(portfolio, pageable);
                    Page<SchoolResponse> schoolResponses = schools.map(SchoolResponse::new);
                    return responseService.getPageResponse(schoolResponses);
        }
    }




    @Transactional(readOnly = true)
    public CommonResponse getOneCareer(DType dType, Long careerId) {

        if (dType.equals(DType.P)) return responseService
                .getSingleResponse(new ProjectResponse
                        (careerRepository.findProjectById(careerId)
                        .orElseThrow(CustomDataNotFoundException::new)));

        else if (dType.equals(DType.C)) return responseService
                .getSingleResponse(new CompanyResponse
                        (careerRepository.findCompanyById(careerId)
                        .orElseThrow(CustomDataNotFoundException::new)));

        else return responseService
                    .getSingleResponse(new SchoolResponse
                            (careerRepository.findSchoolById(careerId)
                            .orElseThrow(CustomDataNotFoundException::new)));

    }

    public CommonResponse createCareer(String email, CareerDataRequest dto, DType dType) {

        Account account = accountRepository.findAccountByEmail(email)
                .orElseThrow(CustomUserNotFoundException::new);

        Portfolio portfolio = portfolioRepository.findPortfolioByEmail(email)
                .orElseThrow(CustomDataNotFoundException::new);

        if (dType.equals(DType.P)) {
            Project project = dto.getProjectEntity(account, portfolio);
            careerRepository.save(project);
            return responseService.getSingleResponse(new ProjectResponse(project));
        } else if (dType.equals(DType.C)) {
            Company company = dto.getCompanyEntity(account, portfolio);
            careerRepository.save(company);
            return responseService.getSingleResponse(new CompanyResponse(company));
        } else {
            School school = dto.getSchoolEntity(account, portfolio);
            careerRepository.save(school);
            return responseService.getSingleResponse(new SchoolResponse(school));
        }


    }

    public CommonResponse updateCareer(String email, Long careerId, CareerDataRequest dto, DType dType) {

        Account account = accountRepository.findAccountByEmail(email)
                .orElseThrow(CustomUserNotFoundException::new);

        Career career = careerRepository.findCareerById(careerId)
                .orElseThrow(CustomDataNotFoundException::new);

        if (!account.getNickname().equals(career.getAccountNickname())) throw new CustomNotOwnerException();

        if (dType.equals(DType.P)) {
            Project project = careerRepository.findProjectById(careerId)
                    .orElseThrow(CustomDataNotFoundException::new);

            project.updateProject(dto);
        } else if (dType.equals(DType.C)) {
            Company company = careerRepository.findCompanyById(careerId)
                    .orElseThrow(CustomDataNotFoundException::new);

            company.updateProject(dto);
        } else {
            School school = careerRepository.findSchoolById(careerId)
                    .orElseThrow(CustomDataNotFoundException::new);

            school.updateProject(dto);
        }

        return responseService.getSuccessResponse();

    }

    public CommonResponse deleteCareer(String email, Long careerId) {
        Account account = accountRepository.findAccountByEmail(email)
                .orElseThrow(CustomUserNotFoundException::new);

        Career career = careerRepository.findCareerById(careerId)
                .orElseThrow(CustomDataNotFoundException::new);

        if (!account.getNickname().equals(career.getAccountNickname())) throw new CustomNotOwnerException();

        careerRepository.deleteById(careerId);

        return responseService.getSuccessResponse();
    }
}
