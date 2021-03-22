package com.portfolio.backend.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResponse {

    @ApiModelProperty(value = "Response 성공 여부")
    private boolean isSuccess;

    @ApiModelProperty(value = "Response 코드 번호")
    private int code;

    @ApiModelProperty(value = "Response Message")
    private String message;

    public CommonResponse(){}
    public CommonResponse(String msg){
        this.message = msg;
    };

}