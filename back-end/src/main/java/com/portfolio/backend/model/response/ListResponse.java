package com.portfolio.backend.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResponse<T> extends CommonResponse{

    @ApiModelProperty(value = "Response Data List")
    private List<T> list;

}
