package com.portfolio.backend.portfolio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortUpdateRequest {

    @NotBlank
    private String name;

    private String introduction;

    private String gitId;

    private String bojId;

    private String blogUrl;

    private String occupation;

    private String location;

    private String profileImage;

}

