package com.portfolio.backend.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> extends CommonResponse {
    @ApiModelProperty(value = "Response Data Page")
    private Page<T> page;
}
