package com.portfolio.backend.portfolio.dto;

import com.portfolio.backend.portfolio.entitiy.TechStack;
import com.portfolio.backend.portfolio.type.TechType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TechStackResponse {

    private Long id;

    private TechType techType;

    public TechStackResponse(TechStack techStack){
        this.id = techStack.getId();
        this.techType = techStack.getTechType();
    }
}
