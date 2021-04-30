package com.portfolio.backend.portfolio.dto;

import com.portfolio.backend.portfolio.type.DetailsType;
import com.portfolio.backend.portfolio.type.TechType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechStackCreateRequest {

    @NotNull
    private TechType techType;

    @NotNull
    private Long requestId;

    private DetailsType detailsType;


}
