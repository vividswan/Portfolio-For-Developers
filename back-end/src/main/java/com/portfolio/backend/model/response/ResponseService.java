package com.portfolio.backend.model.response;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    public <T> SingleResponse<T> getSingleResponse(T data){
        SingleResponse<T> res = new SingleResponse<>();
        res.setData(data);
        res.setCode(0);
        res.setMessage("Success");
        res.setSuccess(true);
        return res;
    }

    public <T> ListResponse<T> getListResponse(List<T> list){
        ListResponse<T> res = new ListResponse<>();
        res.setList(list);
        res.setCode(0);
        res.setMessage("Success");
        res.setSuccess(true);
        return res;
    }

    public <T> PageResponse<T> getPageResponse(Page<T> page){
        PageResponse<T> res = new PageResponse<>();
        res.setPage(page);
        res.setCode(0);
        res.setMessage("Success");
        res.setSuccess(true);
        return res;
    }

    public CommonResponse getSuccessResponse(){
        CommonResponse res = new CommonResponse();
        res.setSuccess(true);
        res.setCode(0);
        res.setMessage("Success");
        return res;
    }

    public CommonResponse getFailResponse(String msg){
        CommonResponse res = new CommonResponse(msg);
        res.setSuccess(false);
        res.setCode(-1);
        res.setMessage(msg);
        return res;
    }

}
