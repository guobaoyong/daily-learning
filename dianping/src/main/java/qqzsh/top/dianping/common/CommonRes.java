package qqzsh.top.dianping.common;

import lombok.Data;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-12-14 11:32
 * @Description 通用响应对象
 */
@Data
public class CommonRes {

    //表明读经请求的返回处理结果，"success"或"fail"
    private String status;

    //若status=success时，表明对应的返回的json类数据
    //若status=fail时，则data内将使用通用的错误码对应的格式
    private Object data;

    //定义一个通用的创建返回对象的方法
    public static CommonRes create(Object result){
        return CommonRes.create(result,"success");
    }

    public static CommonRes create(Object result,String status){
        CommonRes commonRes = new CommonRes();
        commonRes.setStatus(status);
        commonRes.setData(result);
        return commonRes;
    }
}

