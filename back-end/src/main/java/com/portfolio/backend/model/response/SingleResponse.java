package com.portfolio.backend.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SingleResponse<T> extends CommonResponse{

    @ApiModelProperty(value = "Single Response Data")
    private T data;

}
