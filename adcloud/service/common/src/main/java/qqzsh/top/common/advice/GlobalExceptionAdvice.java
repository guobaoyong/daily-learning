package qqzsh.top.common.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import qqzsh.top.common.exception.AdException;
import qqzsh.top.common.vo.CommonResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zsh
 * @site https://qqzsh.top
 * @create 2019-10-19 20:57
 * @Description 异常拦截
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    //指定处理AdException异常，主动抛出，该方法被调用
    @ExceptionHandler(value = AdException.class)
    public CommonResponse<String> handlerAdException(HttpServletRequest req,
                                                     AdException ex) {
        CommonResponse<String> response = new CommonResponse<>(-1,
                "business error");
        response.setData(ex.getMessage());
        return response;
    }
}
