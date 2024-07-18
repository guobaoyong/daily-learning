package qqzsh.top.dianping.common;

import lombok.Data;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-14 11:37
 * @Description 通用错误返回
 */
@Data
public class CommonError {

    //错误码
    private Integer errCode;

    //错误描述
    private String errMsg;

    public CommonError(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public CommonError(EmBusinessError emBusinessError){
        this.errCode = emBusinessError.getErrCode();
        this.errMsg = emBusinessError.getErrMsg();
    }
}
